package dev.aksarok.rpgGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256]; //Conte 1 copia de cada tile del joc
	public static Tile dungeonFloorTile = new DungeonFloorTile(0);
	public static Tile boxTile = new BoxTile(1);
	public static Tile darkZoneTile = new DarkZoneTile(9);
	public static Tile wall01Tile = new Wall01Tile(2);
	public static Tile wall02Tile = new Wall02Tile(3);
	public static Tile wall03Tile = new Wall03Tile(4);
	
	//CLASS
	
	
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x , int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
