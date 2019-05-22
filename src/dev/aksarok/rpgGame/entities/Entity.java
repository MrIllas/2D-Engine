package dev.aksarok.rpgGame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.items.Item;
import static dev.aksarok.rpgGame.utils.Utils.randomInt;

public abstract class Entity {

    public static final int DEFAULT_HEALTH = 10;

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected String name;
    
    protected boolean isDestructible = true;
    protected boolean isInteractable = false;
    protected static Boolean printFeed = false;

    public Entity(Handler handler, String name, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.name = name;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        int dropNumber = randomInt(minDropNumber, maxDropNumber);

        System.out.println("Drops: " + dropNumber);
        for (int t = 0; t <= dropNumber; t++) {
            int toDrop = randomInt(0, (itemId.length - 1));
            System.out.println("U: " + t);
            int rY = randomInt(-30, 30);
            int rX = randomInt(-30, 30);
            handler.getWorld().getItemManager().addItem(Item.items[toDrop].createNew((int) x + rX, (int) y + rY));
        }
    }

    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue; //Salta 1 volta del for si s'esta comparant amb si mateix
            }
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
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

    public boolean isIsDestructible() {
        return isDestructible;
    }

    public void setIsDestructible(boolean isDestructible) {
        this.isDestructible = isDestructible;
    }

    public boolean isIsInteractable() {
        return isInteractable;
    }

    public void setIsInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    public static Boolean getPrintFeed() {
        return printFeed;
    }

    public static void setPrintFeed(Boolean printFeed) {
        Entity.printFeed = printFeed;
    }
}
