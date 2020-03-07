package dev.aksarok.rpgGame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.aksarok.rpgGame.Handler;
import static dev.aksarok.rpgGame.Launcher.*;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.SoundEffect;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.UIImageButton;
import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.gui.ClickListener;
import dev.aksarok.rpgGame.gui.UIAnimated;

public class MenuState extends State {

    private UIManager uiManager;
    
    protected static SoundEffect snd_music;
    protected static boolean musicONOFF = false;
    
    protected static String version = "vAlpha 0.0.3 Snapshot 3v";
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        
        snd_music = new SoundEffect("res/music/Celestial.wav", 0.1f);
        snd_music.loopSound();
        if(!musicONOFF) snd_music.setVolume(0f);
        
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
        
        //Play button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 300, 300, 60, Assets.btn_start, "Play", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                //handler.getMouseManager().setUIManager(null);
                //if(handler.getGame().gameState.getWorld().getEntityManager().getPlayer().getHealth() == 0) {
                    handler.getGame().gameState = new GameState(handler);
                //}
                State.setState(handler.getGame().gameState);
            }
        }));
        
        //Credit button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 400, 300, 60, Assets.btn_start, "Credits", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().creditState);
            }
        }));
        
        //Exit button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 500, 300, 60, Assets.btn_start, "Exit", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
        
        //Options button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -250, 500, 60, 60, Assets.btn_options, "", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().optionsState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        if(handler.getMouseManager().getUIManager() != this.uiManager) {
            handler.getMouseManager().setUIManager(uiManager);
        }
        
        //Feedback mouse
        //System.out.println("X: " + handler.getMouseManager().getMouseX() + "||Y: " + handler.getMouseManager().getMouseY());
    }

    @Override
    public void render(Graphics g) {
        uiManager.getObject(2).setCurrentImage(musicONOFF ? 0 : 1); //Updates mute icon
        uiManager.render(g);
        
        //Version
        Text.drawString(g, version, SCREEN_WIDTH - 170, SCREEN_HEIGHT - 7, true, Color.green, Assets.font15);
        /*g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseX() - 4, handler.getMouseManager().getMouseY() - 4, 8, 8);*/
    }

}
