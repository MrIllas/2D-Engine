/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states.MapEditor;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author Robert
 */
public class TileSelector {
    Handler handler;
    protected int tileId;
    protected int x, y;
    
    Rectangle bounds;
    
    boolean selected = false;
    boolean hovering = false;
    
    public TileSelector(Handler handler, int x, int y, int tileId) {
        this.handler = handler;
        this.tileId = tileId;
        this.x = x;
        this.y = y;
        bounds = new Rectangle((int) x, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }
    
    public void tick(MouseManager mm) {
        if(bounds.contains(mm.getMouseX(), mm.getMouseY())) {
            hovering = true;
        }else {
            hovering = false;
        }
    }
    
    public void onClick(MouseEvent e) {
        select();
    }
    
    public void onMouseRelease(MouseEvent e) {
        if (hovering) {
            onClick(e);
        }else {
            unSelect();
        }
    }
    
    public void render(Graphics g) {
        if(hovering) {
            g.setColor(Color.red);
            g.drawRect( x, y, 32, 32);
        }
        if(selected) {
            g.setColor(Color.green);
            g.drawRect( x, y, 32, 32);
        }
        //g.setColor(Color.red);
        //g.fillRect(x+32, y, 5, 5);
    }
    public boolean isSelected() {
        return selected;
    }

    public boolean isHovering() {
        return hovering;
    }
    protected void select() {
        selected = true;
    }
    
    protected void unSelect() {
        selected = false;
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

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    
    
    
    
}
