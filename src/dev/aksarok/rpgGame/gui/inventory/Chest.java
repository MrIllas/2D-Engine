/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.gui.inventory;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.items.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author robert
 */
public class Chest {
    
    private Handler handler;
    private ArrayList<Item> inventoryItems;
    
    private int invX = 64, invY = 48, invWidth = 512, invHeight = 384, invListCenterX = invX + 171, invListCenterY = invY + invHeight / 2 + 5, invListSpacing = 30;
    private int invImageX = 460, invImageY = 82, invImageWidth = 48, invImageHeight = 64;
    private int invCountX = 484, invCountY = 172;
    
    private int selectedItem = 0;
    
    private Boolean open = false;
    
    public Chest(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }
    
    public void tick() {
        if (!open) return; 
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem --;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            selectedItem++;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            
        }
        
        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;
        }
        else if (selectedItem >= inventoryItems.size()){
            selectedItem = 0;
        }
    }
    
    public void render(Graphics g) {
        if(!open) return;
        
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
        
        int len = inventoryItems.size();
        if (len == 0) { return; }
        for(int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {
                continue;
            }
            
            if(i == 0) {
                Text.drawString(g, "> " +inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            }
            else {
                Text.drawString(g, "> "+inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
            }
        }
        
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
    }
    
    //Chest metods
    
    public void addItem(Item item) {
        for(Item i : inventoryItems) {
            if(i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
    
    public Item getSelectedItem() {
        Item toReturn = inventoryItems.get(selectedItem);
        if(inventoryItems.get(selectedItem).getCount() != 0) {
            inventoryItems.get(selectedItem).setCount(inventoryItems.get(selectedItem).getCount() - 1);
        }
        
        if(inventoryItems.get(selectedItem).getCount() < 0) {
            inventoryItems.remove(selectedItem);
            if(selectedItem >= inventoryItems.size()) {
                selectedItem = 0;
            }
            else {
                selectedItem--;
            }
        }
        
        return toReturn;
    }

    public Item getItem(int index) {
        return inventoryItems.get(index);
    }
    
    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}