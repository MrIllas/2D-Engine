package dev.aksarok.rpgGame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.items.effects.ItemEffect;
import static dev.aksarok.rpgGame.items.effects.ItemEffect.itemEffects;

public class Item {

    //HANDLER
    public static Item[] items = new Item[256];
    public static Item redPotionItem = new Item(Assets.itm_redPotion, "Red Potion", itemEffects[0], "Heals 2.", 0),
                       bluePotionItem = new Item(Assets.itm_bluePotion, "Blue Potion", itemEffects[1], "Heals 4.", 1);

    //Class
    public static final int ITEMWIDTH = 16, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected String description;
    protected ItemEffect effect;
    protected final int id;

    protected int x, y, count;
    protected boolean pickedUp = false;

    protected Rectangle bounds;

    public Item(BufferedImage texture, String name, ItemEffect effect, String description, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.effect = effect;
        this.description = description;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void tick() {
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g) {//World
        if (handler == null) {
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) { //Inventory
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int count) {
        Item i = new Item(texture, name, effect, description, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, effect, description, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    
    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemEffect getEffect() {
        return effect;
    }

    public void setEffect(ItemEffect effect) {
        this.effect = effect;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void setMinusCount(int value) {
        this.count = count - value;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

}
