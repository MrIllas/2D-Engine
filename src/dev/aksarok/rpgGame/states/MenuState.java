package dev.aksarok.rpgGame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.aksarok.rpgGame.Game;
import dev.aksarok.rpgGame.Handler;
import static dev.aksarok.rpgGame.Launcher.*;
import static dev.aksarok.rpgGame.Game.*;
import dev.aksarok.rpgGame.gfx.Animation;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.UIImageButton;
import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.gui.ClickListener;
import dev.aksarok.rpgGame.gui.UIAnimated;

public class MenuState extends State {

    private UIManager uiManager;
    
    
    public MenuState(Handler handler) {
        
        
        
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        //Background
        uiManager.addObject(new UIAnimated(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, Assets.bg_start_menu, 200));
        
        //Titulo
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -300, 100, 600, 100, Assets.theTitle, null, 0, 0, new ClickListener() {
            @Override
            public void onClick() {
                
            }
        }));
        
        //Play button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 300, 300, 60, Assets.btn_start, "Jugar", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
        
        //Exit button
        uiManager.addObject(new UIImageButton((SCREEN_WIDTH) / 2 -150, 400, 300, 60, Assets.btn_start, "Salir", 145, 29, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();

        System.out.println("X: " + handler.getMouseManager().getMouseX() + "||Y: " + handler.getMouseManager().getMouseY());
        /*if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
			State.setState(handler.getGame().gameState);
		}*/

    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        Text.drawString(g, "Version Alpha 0.0.1 Snapshot 30a", SCREEN_WIDTH - 170, SCREEN_HEIGHT - 7, true, Color.white, Assets.font15);
        /*g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseX() - 4, handler.getMouseManager().getMouseY() - 4, 8, 8);*/
    }

}
