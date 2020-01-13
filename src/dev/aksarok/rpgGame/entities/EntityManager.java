package dev.aksarok.rpgGame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.entities.statics.indestructible.Chest01;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private long idCount = 0;
    
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {

        @Override
        public int compare(Entity a, Entity b) {
            if(a.getClass() == Player.class){
                if (a.getY() + 64 > b.getY() + b.getHeight()) {
                    return 1;
                }
            }
            else if (b.getClass() == Player.class) {
                if (a.getY() + a.getHeight() > b.getY() + 64) {
                    return 1;
                }
            }
            else if (a.getY() + a.getHeight() > b.getY() + b.getHeight()) {
                return 1;
            }
            return -1;

        }

    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        //addEntity(player);
    }

    public void tick() {
        Iterator<Entity> it = entities.iterator();
        ArrayList<Entity> postTick = new ArrayList<>();
        while (it.hasNext()) {
            Entity e = it.next();
//            if(e instanceof Chest01) {
//                postTick.add(e);
//                if(it.hasNext()) {
//                    e = it.next();
//                }
//                else {
//                    return;
//                }
//            }
            
            e.tick();
            
            if (!e.isActive()) {
                it.remove();
            }
        }
        
//        Iterator<Entity> postIt = postTick.iterator();
//        while(postIt.hasNext()) {
//            Entity e = postIt.next();
//            
//            e.tick();
//            if (!e.isActive()) {
//                postIt.remove();
//            }
//        }
        
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
        player.postRender(g);
        for (Entity e : entities) {
            e.postRender(g);
        }
    }

    public void addEntity(Entity e) {
        e.setId(idCount);
        entities.add(e);
        idCount++;
    }

    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addEntity(player);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    
    public Entity getEntitieByIndex(int index) {
        return this.entities.get(index);
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

}
