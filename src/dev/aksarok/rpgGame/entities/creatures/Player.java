package dev.aksarok.rpgGame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gui.FeedBack;
import dev.aksarok.rpgGame.gui.inventory.Inventory;

public class Player extends Creature {

    //ANIMATION
    private Animation animDown, animUp, animRight, animLeft, animStand;
    private BufferedImage lastImg = new Animation(500, Assets.player_down, true).getSpecificFrame(2);
    
    //ATACK TIMER
    private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;

    //GUI
    private Inventory inventory;
    private FeedBack feedBack;
    
    //STATS
    public static int health = 10;
    public static int tarjetHealth = 0;
    public static String tarjetName = "";
    public static String interactName = "";

    public Player(Handler handler, String name, float x, float y) {
        super(handler, name, x, y, 32, 64);

        bounds.x = 0;
        bounds.y = 32;
        bounds.width = width;
        bounds.height = height / 2;

        //ANIMATIONS
        animDown = new Animation(500, Assets.player_down, true);
        animUp = new Animation(500, Assets.player_up, true);
        animRight = new Animation(500, Assets.player_right, true);
        animLeft = new Animation(500, Assets.player_left, true);
        animStand = new Animation(500, Assets.player_down, true);

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
        feedBack.tick();
    }

    private Rectangle cb; //CollisionBounds
    private Rectangle ar; //Area de atac

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
        ar.width = 20;
        ar.height = 20;

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
            
            tarjetHealth = e.getHealth();
            tarjetName = e.getName();
            
            //Mira si se puede dañar o no
            if(e.isIsDestructible() == false) { return; }
            
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
    }
    
    private Rectangle iArea; 
    
    private void interactArea() {
        iArea = getCollisionBounds(0,0);
        iArea = new Rectangle();
        iArea.width = bounds.width * 3;
        iArea.height = bounds.height * 3;
        iArea.x = cb.x + cb.width / 2 - iArea.width / 2;
        iArea.y = cb.y - iArea.height;
    }
    
    private void checkInteraction() {
        
        interactArea();
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            
            if(e.getCollisionBounds(0, 0).intersects(iArea)) {
                if(e.isIsInteractable() == false) { return;}
                if(e.equals(this)) { continue; }
                
                interactName = e.getName();
                
                e.setPrintFeed(true);
            }
            else {
                e.setPrintFeed(false);
            }
        }
    }

    @Override
    public void die() {
        System.out.println("You lose!");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (inventory.isActive()) {
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
        g.setColor(Color.green);
        g.drawRect( (int) (iArea.x - handler.getGameCamera().getxOffset()),
                    (int) (iArea.y - handler.getGameCamera().getyOffset()),
                    iArea.width,
                    iArea.height);
        //Model del jugador
        g.drawImage(getCurrentAnimationFrame(), 
                           (int) (x - handler.getGameCamera().getxOffset()), 
                           (int) (y - handler.getGameCamera().getyOffset()), 
                           width, 
                           height, 
                           null);
        //Descomentar per veura la collision box
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                   (int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
//                    bounds.width, 
//                    bounds.height);
        
        //COS COS
        g.setColor(Color.blue);
        g.fillRect((int) (ar.x - handler.getGameCamera().getxOffset()), 
                    (int) (ar.y - handler.getGameCamera().getyOffset()), 
                    ar.width, 
                    ar.height);
    }

    public void postRender(Graphics g) {
        //INVENTORY
        inventory.render(g);

        //FEEDBACK
        feedBack.render(g);
    }

    private BufferedImage getCurrentAnimationFrame() {

        if (xMove < 0) {
            lastImg = animLeft.getSpecificFrame(2);
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastImg = animRight.getSpecificFrame(2);
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastImg = animUp.getSpecificFrame(2);
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastImg = animDown.getSpecificFrame(2);
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

    @Override
    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        // TODO Auto-generated method stub

    }
}
