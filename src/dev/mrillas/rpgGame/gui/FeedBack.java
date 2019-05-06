package dev.mrillas.rpgGame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.mrillas.rpgGame.Game;
import dev.mrillas.rpgGame.Handler;
import dev.mrillas.rpgGame.Launcher;
import dev.mrillas.rpgGame.entities.creatures.Player;
import dev.mrillas.rpgGame.gfx.Assets;
import dev.mrillas.rpgGame.gfx.Text;

public class FeedBack {
	
	private Handler handler;
	private boolean active = false;
	
	//Pos
	private int invX = 32*0+2, invY = 16; //Launcher.SCREEN_WIDTH
	
	public FeedBack(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))
			active = !active;
		if(!active) return;
		
	}
	
	public void render(Graphics g) {
		if(!active) return;
		
		//System.out.println("Functiona!");
		Text.drawString(g, "FPS: "+Game.endFps+"", invX, invY, false, Color.GREEN, Assets.font20);
		Text.drawString(g, "HP: "+Player.health+"", invX, invY*2, false, Color.GREEN, Assets.font20);
		Text.drawString(g, "T.Name: "+Player.tarjetName+"", invX, invY*3, false, Color.GREEN, Assets.font20);
		Text.drawString(g, "T.HP: "+Player.tarjetHealth+"", invX, invY*4, false, Color.GREEN, Assets.font20);
	}
}
