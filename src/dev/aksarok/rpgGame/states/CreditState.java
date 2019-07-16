/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states;

import dev.aksarok.rpgGame.Handler;
import static dev.aksarok.rpgGame.Launcher.SCREEN_HEIGHT;
import static dev.aksarok.rpgGame.Launcher.SCREEN_WIDTH;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.ClickListener;
import dev.aksarok.rpgGame.gui.UIAnimated;
import dev.aksarok.rpgGame.gui.UIImageButton;
import dev.aksarok.rpgGame.gui.UIManager;
import static dev.aksarok.rpgGame.states.MenuState.musicONOFF;
import static dev.aksarok.rpgGame.states.MenuState.snd_music;
import static dev.aksarok.rpgGame.states.MenuState.version;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Robert
 */
public class CreditState extends State {
    
    private UIManager uiManager;
    
    public CreditState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        
        //Background
        uiManager.addObject(new UIAnimated(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, Assets.bg_start_menu, 200));
        //Title
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -300, 100, 600, 100, Assets.theTitle, null, 0, 0, new ClickListener() {
            @Override
            public void onClick() {
                
            }
        }));
        
        //Btn mute/unmute music
        uiManager.addObject(new UIImageButton(SCREEN_WIDTH - 40, 10, 25, 25, Assets.btn_sound, null, 0, 0, new ClickListener() {
            @Override
            public void onClick() {
                if (musicONOFF) {
                    snd_music.setVolume(0f);
                    musicONOFF = false;
                }
                else {
                    snd_music.setVolume(0.5f);
                    musicONOFF = true;
                }
            }
        }));
        uiManager.getLastObject().setHasHovering(false);
        uiManager.getLastObject().setDoublePosition(true);
        
        //Back button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 600, 300, 60, Assets.btn_start, "Back", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().menuState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        if(handler.getMouseManager().getUIManager() != this.uiManager) {
            handler.getMouseManager().setUIManager(uiManager);
        }
    }

    @Override
    public void render(Graphics g) {
        uiManager.getObject(2).setCurrentImage(musicONOFF ? 0 : 1); //Updates mute icon
        uiManager.render(g);
        
        //Credits
        
        
        //Version
        Text.drawString(g, version, SCREEN_WIDTH - 170, SCREEN_HEIGHT - 7, true, Color.white, Assets.font15);
    }
}
