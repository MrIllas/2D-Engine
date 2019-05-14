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
        
        public static Tile floorStone1 = new FloorStone1(21);
	public static Tile floorStone2 = new FloorStone2(22);
        public static Tile floorStone3 = new FloorStone3(23);
        public static Tile floorStone4 = new FloorStone4(24);
        public static Tile floorStone5 = new FloorStone5(25);
        public static Tile floorStone6 = new FloorStone6(26);
        public static Tile floorStone7 = new FloorStone7(27);
        public static Tile floorStone8 = new FloorStone8(28);
        public static Tile floorStone9 = new FloorStone9(29);
        
        public static Tile topColumn = new TopColumn(31);
        public static Tile midColumn = new MidColumn(32);
        public static Tile botColumn = new BotColumn(33);
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
