/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities.creatures;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author alu2015324
 */
public class Ghost01 extends Creature {
    
    //Animation
    private Animation animDown, animUp, animRight, animLeft, animStand;
    private BufferedImage lastImg = new Animation(500, Assets.ghost01_down, true).getSpecificFrame(2);
    
    private int direction = 1;
    private long currentTime, nextTime;
    private Boolean move = false;
    private String stand = "moveStand";
    private Entity target;
    
    //STATS
    public static int health = 4;
    
    public Ghost01(Handler handler, float x, float y) {
        super(handler, "Ghost", x, y, 32, 64);
        
        bounds.x = 0;
        bounds.y = 32;
        bounds.width = width;
        bounds.height = height / 2;
        speed = 1.2f;
        
        //Animations
        animDown = new Animation(500, Assets.ghost01_down, true);
        animUp = new Animation(500, Assets.ghost01_up, true);
        animRight = new Animation(500, Assets.ghost01_right, true);
        animLeft = new Animation(500, Assets.ghost01_left, true);
        animStand = new Animation(500, Assets.ghost01_down, true);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        
        //Visio
        checkVision();
        
        //Moviment
        switch(stand) {
            case "moveStand":
//                System.out.println("Move stand");
                moveStand();
                break;
            case "followPlayerStand":
//                System.out.println("Follow Player stand");
                followPlayerStand();
                break;
            default:
                System.out.println("No stand");
        }
        move();
        
    }
    
    private Rectangle vArea;
    private Rectangle cb;
    
    private void visionArea() {
        cb = getCollisionBounds(0, 0);
        vArea = new Rectangle();
        vArea.width = bounds.width * 6;
        vArea.height = bounds.height * 6;
        vArea.x = cb.x + cb.width / 2 - vArea.width / 2;
        vArea.y = cb.y + cb.width / 2 - vArea.height / 2;
    }
    
    private void checkVision() {
        visionArea();
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.getClass() != Player.class) continue;
            
            if(e.getCollisionBounds(0, 0).intersects(vArea)) {
//                System.out.println("Interactuando!");
                stand = "followPlayerStand";
                target = e;
            }
            else {
                stand = "moveStand";
            }
        }
    }
    
    private void followPlayerStand() {
        float targetX = target.getX();
        float targetY = target.getY();
        
        xMove = 0;
        yMove = 0;
        
        currentTime = System.currentTimeMillis();
        
        if (move == false) { 
            nextTime = System.currentTimeMillis() + 500;
            direction = (int) ((Math.random() * 5) + 1);
            move = true;
        }
        
        if (nextTime > currentTime) {
            if(targetX > x && targetY > y) {
                xMove = speed;
                yMove = speed;
            }
            else if(targetX < x && targetY < y) {
                xMove = -speed;
                yMove = -speed;
            }
            else if(targetX > x && targetY < y) {
                xMove = speed;
                yMove = -speed;
            }
            else if(targetX < x && targetY > y) {
                xMove = -speed;
                yMove = speed;
            }
            else if (targetX > x) {
                xMove = speed;
            }
            else if (targetX < x) {
                xMove = -speed;
            }
            else if (targetY > y) {
                yMove = speed;
            }
            else if (targetY < y) {
                yMove = -speed;
            }
        }
        else if (nextTime < currentTime) {
            move = false;
        }
        
    }
    
    @Override
    public void render(Graphics g) {
        //Area de interaccio
        g.setColor(Color.green);
        g.drawRect( (int) (vArea.x - handler.getGameCamera().getxOffset()),
                    (int) (vArea.y - handler.getGameCamera().getyOffset()),
                    vArea.width,
                    vArea.height);
        
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        Text.drawString(g,
                       "< "+ stand + " >", 
                       (int) (x + (Tile.TILEWIDTH/2) - handler.getGameCamera().getxOffset()), 
                       (int) (y - 20 - handler.getGameCamera().getyOffset()), 
                       true,
                       Color.white, 
                       Assets.font15);

    }

    @Override
    public void die() {
        int[] item = {0, 1};
        deadthDrop(2, 1, item);
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
    
    private void moveStand() {
        xMove = 0;
        yMove = 0;
        
        currentTime = System.currentTimeMillis();
        
        if(move == false) { 
            nextTime = System.currentTimeMillis() + 500;
            direction = (int) ((Math.random() * 5) + 1);
            move = true;
        }
        
        if(nextTime > currentTime) {
            switch(direction) {
                case 1:
                    yMove = -speed;
                    //y = -speed;
                    break;
                case 2:
                    yMove = speed;
                    //y = speed;
                    break;
                case 3:
                    xMove = -speed;
                    //x = -speed;
                    break;
                case 4:
                    xMove = speed;
                    //x = speed;
                    break;
                default:

            }
        }
        else if (nextTime < currentTime) {
            move = false;
        }
    }
}
