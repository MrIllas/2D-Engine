/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities.statics.indestructible;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.inventory.Chest;
import dev.aksarok.rpgGame.items.Item;
import dev.aksarok.rpgGame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author robert
 */
public class Chest02 extends IndestructibleEntity{
    
    //STATS
    private static String name = "Chest02";
    
    private static Boolean activeMenu = false;
    
    protected Chest chest;
    
    public Chest02(Handler handler, float x, float y) {
        super(handler, name, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        bounds.x = 0;
        bounds.y = (int) (height / 2.2f);
        bounds.width = width;
        bounds.height = (int) (height - height / 2.2f);
        isInteractable = true;
        
        this.chest = new Chest(handler);
        //
        itemPool();
    }
    
    @Override
    public void tick() {
        this.chest.tick();
        doInteraction();
        
        if(this.printFeed == false) {
            this.chest.setOpen(false);
            this.activeMenu = false;
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.activeMenu) {
            g.drawImage(Assets.ent_chest02[2], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        else {
            g.drawImage(Assets.ent_chest02[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        
        
        //Descomentar per veura la collision box
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        
        feedG(g);
        
        interactionToRender(g);
    }

    @Override
    public void die() {

    }

    @Override
    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {

    }
    
    protected void feedG(Graphics g) {
        if(this.printFeed == true) {
            Text.drawString(g, "(F)", (int) (x + (Tile.TILEWIDTH/2) - handler.getGameCamera().getxOffset()), (int) (y - 20 - handler.getGameCamera().getyOffset()), true, Color.white, Assets.font20);
        }
    }
    
    protected void doInteraction() {
        if(this.printFeed != true ) { return; }
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
            this.activeMenu = !activeMenu;
            this.chest.setOpen(true);
        }
    }
    
    protected void interactionToRender(Graphics g) {
        if(!activeMenu) { return; }
            this.chest.render(g);
        
    }

    public Chest getChest() {
        return this.chest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }
    
    public void addItemToChest(Item item) {
        this.chest.addItem(item);
    }
    
    private void itemPool() {
        this.chest.addItem(Item.items[0]);
        this.chest.addItem(Item.items[1]);
        this.chest.getItem(0).setCount(5);
    }
}