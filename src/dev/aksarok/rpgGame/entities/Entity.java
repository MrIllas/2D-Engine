package dev.aksarok.rpgGame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.aksarok.rpgGame.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int health;
	protected boolean active = true;
	protected Rectangle bounds;
	protected String name;
	
	public Entity(Handler handler, String name, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = DEFAULT_HEALTH;
		this.name = name;
		
		bounds = new Rectangle(0,0, width, height);
	}
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	protected abstract void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId);
	
	public void hurt(int amt) {
		health -= amt;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) continue; //Salta 1 volta del for si es s'esta comparant amb si mateix
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle ((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	
	
	//GETTES AND SETTERS
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isActive() {
		return active;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
