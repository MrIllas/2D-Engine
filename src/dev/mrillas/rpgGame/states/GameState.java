package dev.mrillas.rpgGame.states;

import java.awt.Graphics;

import dev.mrillas.rpgGame.Game;
import dev.mrillas.rpgGame.Handler;
import dev.mrillas.rpgGame.entities.creatures.Player;
import dev.mrillas.rpgGame.entities.statics.Box;
//import dev.mrillas.rpgGame.gfx.Assets;
//import dev.mrillas.rpgGame.tiles.Tile;
import dev.mrillas.rpgGame.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.wlvl");
		handler.setWorld(world);
		
		
	}
	
	@Override
	public void tick() {
		world.tick();
		
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		
		//Tile.tiles[0].render(g, 0, 0);
		//Tile.tiles[1].render(g, 112, 112);
	}
	
}
