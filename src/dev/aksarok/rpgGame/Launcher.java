package dev.aksarok.rpgGame;

import dev.aksarok.rpgGame.display.Display;

/**
 * Aquesta classe es la main de la nostre finestra
 *
 * @author Robert
 *
 */
public class Launcher {

    public static final int SCREEN_WIDTH = 1280, SCREEN_HEIGHT = 720;
    
    private static Game game = new Game("Joc Rpg!", SCREEN_WIDTH, SCREEN_HEIGHT); 
    
    public static void main(String[] args) {
        game.start();
    }
    
    public static void gameStop() {
        game.stop();
    }
    
    public static void gameStart() {
        game.start();
    }

}
