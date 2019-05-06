package dev.aksarok.rpgGame.entities.creatures;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import dev.aksarok.rpgGame.tiles.Tile;

public abstract class Creature extends Entity{
	
	
	public static final float DEFAULT_SPEED = 2.5f;
	public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 32;
	
	protected float speed;
	protected float xMove, yMove;
	
	//Stats
	protected String name;
	
	public Creature(Handler handler,String name, float x, float y, int width, int height) {
		super(handler,name, x, y, width, height);
		speed = DEFAULT_SPEED;
		
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) moveX();
		
		if(!checkEntityCollisions(0f, yMove)) moveY();
	}
	
	public void moveX() {
		if(xMove > 0) { //Move Right
			int tx = (int) (x + xMove + bounds.x + bounds.width) /Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) /Tile.TILEWIDTH) && !collisionWithTile(tx, (int) (y + bounds.y +bounds.height) /Tile.TILEWIDTH)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}else if(xMove < 0) {//Move left
			int tx = (int) (x + xMove + bounds.x) /Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) /Tile.TILEWIDTH) && !collisionWithTile(tx, (int) (y + bounds.y +bounds.height) /Tile.TILEWIDTH)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
		
	}
	
	public void moveY() {
		if(yMove < 0) {//UP
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEHEIGHT,ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT,ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}else if(yMove > 0) {//DOWN
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT,ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT,ty)) {
				y += yMove;
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
		
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//GETTERS AND SETTERS
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	
}
