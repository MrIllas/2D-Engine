package dev.aksarok.rpgGame;

import dev.aksarok.rpgGame.gfx.GameCamera;
import dev.aksarok.rpgGame.input.KeyManager;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.input.MouseWheelManager;
import dev.aksarok.rpgGame.worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public MouseWheelManager getMouseWheelManager(){
        return game.getMouseWheelManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public void turnPause() {
        Game.pause = !Game.pause;
    }
}
