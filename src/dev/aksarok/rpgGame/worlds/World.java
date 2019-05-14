package dev.aksarok.rpgGame.worlds;

import java.awt.Graphics;

import dev.aksarok.rpgGame.Game;
import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.EntityManager;
import dev.aksarok.rpgGame.entities.creatures.Ghost01;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.entities.statics.Box;
import dev.aksarok.rpgGame.entities.statics.indestructible.Chest01;
import dev.aksarok.rpgGame.items.ItemManager;
import dev.aksarok.rpgGame.tiles.Tile;
import dev.aksarok.rpgGame.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;//x Tile
	private int spawnX, spawnY;
	private int[][] tiles;
	
        
        //ITEM
	private ItemManager itemManager;
        
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler,"Player", 300, 400));
		itemManager = new ItemManager(handler);
		
		entityManager.addEntity(new Box(handler, 450, 400));
		entityManager.addEntity(new Box(handler, 482, 400));
		entityManager.addEntity(new Box(handler, 514, 400));
		entityManager.addEntity(new Box(handler, 450, 452));
		entityManager.addEntity(new Box(handler, 482, 452));
		entityManager.addEntity(new Box(handler, 514, 452));
                
                entityManager.addEntity(new Ghost01(handler, 400, 300));
                
                entityManager.addEntity(new Chest01(handler, 600, 600));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	

	public void tick() {
		entityManager.tick();
		itemManager.tick();
	}
	
	public void render(Graphics g) {
		//TILES
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g,(int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//ITEMS
		itemManager.render(g);
		//Entities
		entityManager.render(g);	
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.darkZoneTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) return Tile.dungeonFloorTile;
		
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX= Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}



	public ItemManager getItemManager() {
		return itemManager;
	}



	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	
}
