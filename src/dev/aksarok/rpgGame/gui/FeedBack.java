package dev.aksarok.rpgGame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.aksarok.rpgGame.Game;
import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.Launcher;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import java.awt.image.BufferedImage;

public class FeedBack {

    private Handler handler;
    private boolean active = false;
    //Posicion general
    private int invX = 32 * 0 + 2, invY = 16; //Launcher.SCREEN_WIDTH
    //Health 
    private int playerHealth = 0;
    
    private int hBarBaseX = 0, hBarBaseY = 0, hBarBaseWidth = 107 * 3, hBarBaseHeight = 14 * 3;
    private int hBarVidaX = hBarBaseX + 42, hBarVidaY = hBarBaseY + 6, hBarVidaWidth = 91 * 3, hBarVidaHeight = 7 * 3, hBarBaseVida = 91 * 3;
    
    private Animation healthAnim;
    
    public FeedBack(Handler handler) {
        this.handler = handler;
        
        healthAnim = new Animation(500, Assets.healthBarFull, false);
    }

    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
            active = !active;
        }
        if (!active) {
            return;
        }
        
        healthAnim.tick();
        
    }

    public void render(Graphics g) {
        renderPlayerHealthBar(g);
        if (!active) {
            return;
        }
        int pX = (int) (handler.getGame().gameState.getWorld().getEntityManager().getPlayer().getX() + 32) / 32;
        int pY = (int) (handler.getGame().gameState.getWorld().getEntityManager().getPlayer().getY() + 32) / 32;
        int nEntities = handler.getGame().gameState.getWorld().getEntityManager().getEntities().size();
        String activeWorld = handler.getGame().gameState.getActiveWorld();
        
        Text.drawString(g, "FPS: " + Game.endFps + "", invX, 50 + invY, false, Color.GREEN, Assets.font20);
        Text.drawString(g, "HP: " + playerHealth + "", invX, 50 + invY * 2, false, Color.GREEN, Assets.font20);
        Text.drawString(g, "T.Name: " + Player.tarjetName + "", invX, 50 + invY * 3, false, Color.GREEN, Assets.font20);
        Text.drawString(g, "T.HP: " + Player.tarjetHealth + "", invX, 50 + invY * 4, false, Color.GREEN, Assets.font20);
        Text.drawString(g, "L. Interaction: " + Player.interactName + "", invX, 50 + invY * 5, false, Color.GREEN, Assets.font20);
        Text.drawString(g, "N. of Entities: " + nEntities + "", invX, 50 + invY * 6, false, Color.GREEN, Assets.font20);
        Text.drawString(g, pX+"/"+pY+" "+activeWorld, invX, 50 + invY * 7, false, Color.GREEN, Assets.font20);
        
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
    
    private void renderPlayerHealthBar(Graphics g) {
        //Base
        g.drawImage(Assets.healthBar[0], hBarBaseX, hBarBaseY, hBarBaseWidth, hBarBaseHeight, null);
    
        //vida vuida
        g.drawImage(Assets.healthBar[1], hBarVidaX, hBarVidaY, 91 * 3, hBarVidaHeight, null);
        
        //Vida vida
        g.drawImage(divideHealth(), hBarVidaX, hBarVidaY, hBarVidaWidth, hBarVidaHeight, null);
    }
    
    private BufferedImage divideHealth() {
        BufferedImage toReturn;
        int dif = 0;
        
        if(playerHealth == 1) {
            dif = playerHealth * 10; 
        }
        else {
            dif = playerHealth * 9;
        }
        
        hBarVidaWidth = (hBarBaseVida/10) * playerHealth ;
        
        if (dif == 0) { dif = 90;}
        
        toReturn = healthAnim.getCurrentFrame().getSubimage(0, 0,  dif, healthAnim.getCurrentFrame().getHeight());
        
        return toReturn;
    }
}
