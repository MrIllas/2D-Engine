/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.entities.statics.indestructible.IndestructibleEntity;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.worlds.World;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author xveri
 */
public class TeleportTile extends IndestructibleEntity {

    public TeleportTile(Handler handler, float x, float y) {
        super(handler, "tp", x, y, 32, 32);
    }

    @Override
    public void tick() {
        checkPlayer();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dungeonFloor, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 16, 16, null);
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Rectangle cb;
    private Rectangle tpArea;
    
    private void checkPlayer() {
        cb = getCollisionBounds(0, 0);
        tpArea = new Rectangle();
        tpArea.width = 16;
        tpArea.height = 16;
        tpArea.x = cb.x + cb.width / 2 - tpArea.width / 2;
        tpArea.y = cb.y + cb.width / 2 - tpArea.height / 2;
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.getClass() != Player.class) continue;
            
            if(e.getCollisionBounds(0, 0).intersects(tpArea)) {
                handler.setWorld(new World(handler, "res/worlds/world2.wlvl"));
            }
        }
    }
}
