/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.gui.inGame;

import dev.aksarok.rpgGame.Handler;
import static dev.aksarok.rpgGame.Launcher.SCREEN_HEIGHT;
import static dev.aksarok.rpgGame.Launcher.SCREEN_WIDTH;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.ClickListener;
import dev.aksarok.rpgGame.gui.UIImageButton;
import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Robert
 */
public class PauseMenu {
    private Handler handler;
    private UIManager uiManager;
    private boolean active = false;
    
    /*GUI*/
    private int startX = 200, startY = 200;
    
     private float alpha = 0.5f;
    private int bWidth = 1280, bHeight = 720;
    private Color bColor = new Color (0.0f, 0.0f, 0.0f, alpha);
    
    
    public PauseMenu(Handler handler, UIManager uiManager) {
        this.handler = handler;
        this.uiManager = uiManager;
        
        /*GUI*/
        //Options button
        
    }
    
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.active ^= true; 
            handler.getGame().gameState.turnPauseGameState();
        }
        if(!active) {
            return;
        }
    }
    
    public void render(Graphics g) { 
        if(!active) {
            return;
        }
       Text.drawString(g, "GAME PAUSED", SCREEN_WIDTH - 720, 200, true, Color.white, Assets.font48);
        background(g);
        
    }
    
    private void background(Graphics g) {
        g.setColor(bColor);
        g.fillRect(0, 0, bWidth, bHeight);
    }
}
