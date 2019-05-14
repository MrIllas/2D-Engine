/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities.statics.indestructible;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import java.awt.Graphics;

/**
 *
 * @author robert
 */
public abstract class IndestructibleEntity extends Entity {
    
    
    
    public IndestructibleEntity(Handler handler, String name, float x, float y, int width, int height) {
        super(handler, name, x, y, width, height);
        isDestructible = false;
        isInteractable = false;
    }

    @Override
    public void tick() {
        interactArea();
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void deadthDrop(int maxDropNumber, int minDropNumber, int[] itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void interactArea() {
        
    }
    
}
