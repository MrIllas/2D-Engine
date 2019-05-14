/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities.creatures;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author alu2015324
 */
public class Ghost01 extends Creature {
    
    //Animation
    private Animation animDown, animUp, animRight, animLeft, animStand;
    private BufferedImage lastImg = new Animation(500, Assets.ghost01_down, true).getSpecificFrame(2);
    
    private long lastTime, timer;
    
    //STATS
    public static int health = 4;
    
    public Ghost01(Handler handler, float x, float y) {
        super(handler, "Ghost", x, y, 32, 64);
        
        bounds.x = 0;
        bounds.y = 32;
        bounds.width = width;
        bounds.height = height / 2;
        
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
        
        moveStand();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        int randDir = (int) ((Math.random() * 100) + 1);
        
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed) {
            switch(randDir) {
                case 1:
                    yMove = -speed * 10;
                    break;
                case 2:
                    yMove = speed * 10;
                    break;
                case 3:
                    xMove = -speed * 10;
                    break;
                case 4:
                    xMove = speed * 10;
                    break;
                default:
                    
            }
            timer = 0;
        }
    }
    
    private void checkPlayer() {
        
    }
    
    
}
