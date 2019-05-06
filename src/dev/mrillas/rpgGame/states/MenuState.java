package dev.mrillas.rpgGame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.mrillas.rpgGame.Game;
import dev.mrillas.rpgGame.Handler;
import dev.mrillas.rpgGame.gfx.Assets;
import dev.mrillas.rpgGame.gui.UIImageButton;
import dev.mrillas.rpgGame.gui.UIManager;
import dev.mrillas.rpgGame.gui.ClickListener;

public class MenuState extends State{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 117, 27, Assets.btn_start, new ClickListener () {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		
		System.out.println("X: "+handler.getMouseManager().getMouseX()+"||Y: "+handler.getMouseManager().getMouseY());
		/*if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
			State.setState(handler.getGame().gameState);
		}*/
		
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		/*g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseX() - 4, handler.getMouseManager().getMouseY() - 4, 8, 8);*/
		
		
		
	}

}
