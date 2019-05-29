package dev.aksarok.rpgGame.states;

import java.awt.Graphics;

import dev.aksarok.rpgGame.Game;
import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.EntityManager;
import dev.aksarok.rpgGame.entities.creatures.Ghost01;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.entities.statics.Box;
import dev.aksarok.rpgGame.entities.statics.indestructible.Chest01;
import dev.aksarok.rpgGame.entities.statics.indestructible.TeleportTile;
//import dev.mrillas.rpgGame.gfx.Assets;
//import dev.mrillas.rpgGame.tiles.Tile;
import dev.aksarok.rpgGame.worlds.World;

public class GameState extends State {

    private World world1;
    private World world2;
    
    private EntityManager baseEM;
    private EntityManager world1EM;
    private EntityManager world2EM;
    
    private String activeWorld = "world1";
    private String lastWorld = "world1";

    public GameState(Handler handler) {
        super(handler);
        baseEM = new EntityManager(handler, new Player(handler, "Player", 50, 50));
        
        //World2
        world2EM = new EntityManager(handler, null);
        world2EM.setPlayer(baseEM.getPlayer());
        world2EM.addEntity(new Box(handler, 514, 400));
        world2EM.addEntity(new Box(handler, 450, 452));
        world2EM.addEntity(new Box(handler, 482, 452));
        world2EM.addEntity(new Box(handler, 514, 452));
        world2EM.addEntity(new Box(handler, 450, 100));
        world2EM.addEntity(new Ghost01(handler, 400, 300));
        world2EM.addEntity(new Chest01(handler, 600, 600));
        world2EM.addEntity(new TeleportTile(handler, 20*32, 22*32, "world1", 42*32, 15*32));
        world2 = new World(handler, "world2", "res/worlds/world2.wlvl", world2EM);
        
        //World1
        world1EM = new EntityManager(handler, null);
        world1EM.setPlayer(baseEM.getPlayer());
        world1EM.addEntity(new Ghost01(handler, 256, 96));  
        world1EM.addEntity(new Box(handler, 288, 768));
        world1EM.addEntity(new Box(handler, 288 + 32, 768));
        world1EM.addEntity(new Box(handler, 384, 1056 - 32));
        world1EM.addEntity(new Ghost01(handler, 384 - 32, 1056));
        world1EM.addEntity(new TeleportTile(handler, 42*32, 14*32, "world2", 20*32, 18*32));
        world1 = new World(handler, "world1", "res/worlds/world1.wlvl", world1EM);
        
        handler.setWorld(world1);
    }

    @Override
    public void tick() {
        
//        System.out.println("world2EM -> "+world2EM);
//        System.out.println("world1EM -> "+world1EM);
//        System.out.println("baseEM -> "+baseEM);
        switch(activeWorld) {
            case "world1":
                world1.tick();
                break;
            case "world2":
                world2.tick();
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        switch(activeWorld) {
            case "world1":
                if(handler.getWorld() != world1){
                    handler.setWorld(world1);
                }
                world1.render(g);
                break;
            case "world2":
                if(handler.getWorld() != world2){
                    handler.setWorld(world2);
                }
                world2.render(g);
                break;
        }

        //Tile.tiles[0].render(g, 0, 0);
        //Tile.tiles[1].render(g, 112, 112);
    }

    public World getWorld() {
        return world1;
    }

    public void setWorld(World world) {
        this.world1 = world;
    }

    public String getActiveWorld() {
        return activeWorld;
    }

    public void setActiveWorld(String activeWorld) {
        this.lastWorld = this.activeWorld;
        this.activeWorld = activeWorld;
    }
}
