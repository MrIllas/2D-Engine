package dev.aksarok.rpgGame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import dev.aksarok.rpgGame.entities.creatures.playerThreads.Interaction;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gui.FeedBack;
import dev.aksarok.rpgGame.gui.inGame.Inventory;
import dev.aksarok.rpgGame.states.State;

public class Player extends Creature {

    //ANIMATION
    private Animation animDown, animUp, animRight, animLeft, animStand,
            animAttDown, animAttUp, animAttRight, animAttLeft;
    private BufferedImage lastImg = new Animation(500, Assets.player_down, true).getSpecificFrame(2);

    //ATACK TIMER
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;

    //GUI
    private Inventory inventory;
    private FeedBack feedBack;
    
    //Threads
    private Interaction interaction;
    
    //STATS
    public static int tarjetHealth = 0;
    public static String tarjetName = "";
    public static String interactName = "";
    public static long interactId = 0;
    private boolean interactionSwitch = false;
    public static Boolean aMenuIsOpen = false;

    public Player(Handler handler, String name, float x, float y) {
        super(handler, name, x, y, 32, 64); //32/64/192

        //STATS
        health = 10;
        maxHealth = 10;
        //

        bounds.x = 0;
        bounds.y = 32;
        bounds.width = width;
        bounds.height = height / 2;

        //ANIMATIONS
        animDown = new Animation(50, Assets.player_down, true);
        animUp = new Animation(50, Assets.player_up, true);
        animRight = new Animation(50, Assets.player_right, true);
        animLeft = new Animation(50, Assets.player_left, true);
        animStand = new Animation(50, Assets.player_down, true);
        animAttDown = new Animation(100, Assets.player_attDown, true);
        animAttUp = new Animation(100, Assets.player_attUp, true);
        animAttRight = new Animation(100, Assets.player_attRight, true);
        animAttLeft = new Animation(100, Assets.player_attLeft, true);

        inventory = new Inventory(handler);
        feedBack = new FeedBack(handler);
    }

    @Override
    public void tick() {
        //ANIMATIONS
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        animAttDown.tick();
        animAttUp.tick();
        animAttLeft.tick();
        animAttRight.tick();

        //MOVEMENT
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        //ATACK
        checkAttacks();
        checkInteraction();

        //INVENTORY
        inventory.tick();

        //FeedBack
        feedBack.setPlayerHealth(health);
        feedBack.tick();
    }

    private Rectangle cb; //CollisionBounds
    private Rectangle ar; //Area d'atac

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if (attackTimer < attackCooldown) {
            return;
        }
        if (inventory.isActive()) {
            return;
        }

        cb = getCollisionBounds(0, 0);
        ar = new Rectangle();
        int arWidth = bounds.width;
        int arHeight = bounds.height;
        ar.width = 20 * 3;
        ar.height = 20 * 3;


        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - ar.width / 2; //Agafa el punt mitj
            ar.y = cb.y - ar.height;
        } else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - ar.width / 2; //Agafa el punt mitj
            ar.y = cb.y + arHeight;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - ar.width; //Agafa el punt mitj
            ar.y = cb.y + cb.width / 2 - ar.height / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + arWidth; //Agafa el punt mitj
            ar.y = cb.y + cb.width / 2 - ar.height / 2;
        } else {
            return;
        }
        
        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {

            //Mira si se puede dañar o no
            if (e.isIsDestructible() != true) {
                continue;
            }

            if (e.equals(this)) {
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                e.hurt(1);
                tarjetHealth = e.getHealth();
                tarjetName = e.getName();
                return;
            }
        }
    }

    private Rectangle iArea;

    private void interactArea() {
        cb = getCollisionBounds(0f, 0f);
        iArea = getCollisionBounds(0f, 0f);
        iArea = new Rectangle();
        iArea.width = bounds.width * 3;
        iArea.height = bounds.height * 3;
        iArea.x = cb.x + cb.width / 2 - iArea.width / 2;
        iArea.y = cb.y + cb.width / 2 - iArea.height / 2;
    }

    private void checkInteraction() {
        interactArea();
        //System.out.println(""+handler.getWorld().getEntityManager().getEntities().get(1).getName()+" || " + handler.getWorld().getEntityManager().getEntities().get(1).getId() + " || "+ handler.getWorld().getEntityManager().getEntities().get(1).getPrintFeed());
        //System.out.println(""+handler.getWorld().getEntityManager().getEntities().get(2).getName()+" || " + handler.getWorld().getEntityManager().getEntities().get(2).getId() + " || "+ handler.getWorld().getEntityManager().getEntities().get(2).getPrintFeed());
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.isIsInteractable() == false) {
                continue;
            }
            if (e.equals(this)) {
                continue;
            }
            
            if(!interactionSwitch) {
                interactName = "";
                interactId = 0;
                aMenuIsOpen = false;
                doInteraction(e);
            }else {
                interactionSwitch = interaction.endCheck();
                interactName = interaction.getInteractName();
                interactId = interaction.getInteractId();
                aMenuIsOpen = interaction.isaMenuIsOpen();
            }
        }
    }
    
    private void doInteraction(Entity e){
        if (e.getCollisionBounds(0f, 0f).intersects(iArea)) {
            interactionSwitch = true;
            interaction = new Interaction(handler, e, iArea, this);
            interaction.start();
        }
    }

    @Override
    public void die() {
        System.out.println("You lose!");
        State.setState(handler.getGame().menuState);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;
        
        //System.out.println(""+aMenuIsOpen);
        if (inventory.isActive()) {
            return;
        }
        if (aMenuIsOpen) {
            return;
        }
        if (handler.getKeyManager().w) {
            yMove = -speed; //UP
        }
        if (handler.getKeyManager().s) {
            yMove = speed; //DOWN
        }
        if (handler.getKeyManager().a) {
            xMove = -speed; //LEFT
        }
        if (handler.getKeyManager().d) {
            xMove = speed; //RIGHT
        }
    }

    @Override
    public void render(Graphics g) {
        //Area de interaccio
//        g.setColor(Color.green);
//        g.drawRect( (int) (iArea.x - handler.getGameCamera().getxOffset()),
//                    (int) (iArea.y - handler.getGameCamera().getyOffset()),
//                    iArea.width,
//                    iArea.height);
        //Model del jugador
        if (handler.getKeyManager().aUp || handler.getKeyManager().aDown
                || handler.getKeyManager().aLeft || handler.getKeyManager().aRight) {
            width = 192;
            height = 192;
            g.drawImage(getCurrentAnimationFrame(),
                    (int) (x - width / 3 - handler.getGameCamera().getxOffset()),
                    (int) (y - height / 3 - handler.getGameCamera().getyOffset()),
                    width,
                    height,
                    null);
        } else {
            width = 32;
            height = 64;
            g.drawImage(getCurrentAnimationFrame(),
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width,
                    height,
                    null);
        }

        //Descomentar per veura la collision box
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                   (int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
//                    bounds.width, 
//                    bounds.height);
        //COS COS
//        g.setColor(Color.blue);
//        g.fillRect((int) (ar.x - handler.getGameCamera().getxOffset()), 
//                    (int) (ar.y - handler.getGameCamera().getyOffset()), 
//                    ar.width, 
//                    ar.height);
    }

    public void postRender(Graphics g) {
        //INVENTORY
        inventory.render(g);

        //FEEDBACK
        feedBack.render(g);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (handler.getKeyManager().aUp) {
            return animAttUp.getCurrentFrame();
        } else if (handler.getKeyManager().aDown) {
            return animAttDown.getCurrentFrame();
        } else if (handler.getKeyManager().aLeft) {
            return animAttLeft.getCurrentFrame();
        } else if (handler.getKeyManager().aRight) {
            return animAttRight.getCurrentFrame();
        } else if (xMove < 0) {
            lastImg = animLeft.getSpecificFrame(8);//8
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastImg = animRight.getSpecificFrame(8);
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastImg = animUp.getSpecificFrame(8);
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastImg = animDown.getSpecificFrame(8);
            return animDown.getCurrentFrame();
        }
        return lastImg;
    }

    //GETTERS AND SETTERS
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getiArea() {
        return iArea;
    }

    public void setiArea(Rectangle iArea) {
        this.iArea = iArea;
    }

    @Override
    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        // TODO Auto-generated method stub

    }
}
