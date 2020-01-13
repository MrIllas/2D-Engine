package dev.aksarok.rpgGame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.items.Item;
import static dev.aksarok.rpgGame.utils.Utils.randomInt;

public abstract class Entity {

    public static final int DEFAULT_HEALTH = 10;

    protected Handler handler;
    protected long id;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected int maxHealth;
    protected boolean active = true;
    protected Rectangle bounds;
    protected String name;
    
    protected boolean isDestructible = true;
    protected boolean isInteractable = false;
    protected Boolean printFeed = false;
    protected Boolean activeMenu = false;

    public Entity(Handler handler, String name, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.name = name;

        this.bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    public abstract void postRender(Graphics g);
    
    public abstract void die();

    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        int dropNumber = randomInt(minDropNumber, maxDropNumber);

        for (int t = 0; t <= dropNumber; t++) {
            int toDrop = randomInt(0, (itemId.length - 1));
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
        return new Rectangle((int) (x + this.bounds.x + xOffset), (int) (y + this.bounds.y + yOffset), this.bounds.width, this.bounds.height);
    }
    
    //Others
    public boolean heal(int value) {
        if(health != maxHealth){
            health = health + value;
            
            if(health > maxHealth) {
                health = maxHealth;
                return true; //Efecte cumplert
            }
            return true;
        }
        return false; //No hi ha hagut cap efecte
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

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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

    public Boolean getPrintFeed() {
        return this.printFeed;
    }

    public void setPrintFeed(Boolean printFeed) {
        this.printFeed = printFeed;
    }

    public Boolean getActiveMenu() {
        return this.activeMenu;
    }

    public void setActiveMenu(Boolean activeMenu) {
        this.activeMenu = activeMenu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
