package dev.mrillas.rpgGame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import static dev.mrillas.rpgGame.utils.Utils.randomInt;
import dev.mrillas.rpgGame.Handler;
import dev.mrillas.rpgGame.gfx.Assets;
import dev.mrillas.rpgGame.items.Item;
import dev.mrillas.rpgGame.tiles.Tile;

public class Box extends StaticEntity{
	
	//STATS
	private static String name = "Caja";
	
	public Box(Handler handler, float x, float y) {
		super(handler, name, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = (int) (height / 2.2f);
		bounds.width = width;
		bounds.height = (int) (height - height / 2.2f);
	}

	@Override
	public void tick() {
		
		
	}
	
	@Override
	public void die() {
		
		int[] item = {0,1};
		deadthDrop(0, 2 , item);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.box, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//Descomentar per veura la collision box
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	@Override
	protected void deadthDrop(int minDropNumber, int maxDropNumber, int[] itemId) {
		int dropNumber = randomInt(minDropNumber, maxDropNumber);
		
		System.out.println("Drops: "+dropNumber);
		for(int t = 0 ; t <= dropNumber; t++) {
			int toDrop = randomInt(0, (itemId.length -1));
			System.out.println("U: "+t);
			int rY = randomInt(-30, 30);
			int rX = randomInt(-30, 30);
			handler.getWorld().getItemManager().addItem(Item.items[toDrop].createNew((int) x + rX, (int) y + rY));
		}
	}
	
	//GETTERS AND SETTERS
	
	

}
