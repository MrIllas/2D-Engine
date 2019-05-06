package dev.aksarok.rpgGame;

import dev.aksarok.rpgGame.display.Display;

/**
 * Aquesta classe es la main de la nostre finestra
 * @author Robert
 *
 */
public class Launcher {
	
	public static final int SCREEN_WIDTH = 1280, SCREEN_HEIGHT = 720;
	
	public static void main (String[] args) {
		Game game = new Game("Joc Rpg!", SCREEN_WIDTH, SCREEN_HEIGHT); //1280 720
		game.start();
	}
	
}
