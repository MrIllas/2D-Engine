/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.items.effects;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Animation;
import java.awt.Graphics;

/**
 *
 * @author Robert
 */
public class ItemEffect {
    
    //Handler
    public static ItemEffect[] itemEffects = new ItemEffect[256];
    public static ItemEffect healPlus2 = new ItemEffect("heal", 1000, 2, null, 0),
                             healPlus4 = new ItemEffect("heal", 1000, 4, null, 1);
    
    //Class
    protected Handler handler;
    protected Animation animation;
    
    protected final int id;
    
    protected String type;
    protected long time , lastTime , cooldown = 1000;
    protected int value;
    
    public ItemEffect(String type, long time, int value, Animation animation, int id) {
        this.type = type;
        this.time = time;
        this.value = value;
        this.animation = animation;
        this.id = id;
        
        itemEffects[id] = this;
    }
    
    public void tick() {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(time < cooldown) {
            return;
        }
        
        switch(type.toLowerCase()) {
            case "heal":
                System.out.println("Healed!");
                handler.getWorld().getEntityManager().getPlayer().heal(value);
                break;
            case "damage":
                System.out.println("Damaged!");
                break;
        }
    }
    
    public void render(Graphics g, int x, int y) {
        if(animation == null) { return; }
        
        
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
