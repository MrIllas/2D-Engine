package dev.aksarok.rpgGame.worlds;

import java.awt.Graphics;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.EntityManager;
import dev.aksarok.rpgGame.items.ItemManager;
import dev.aksarok.rpgGame.tiles.Tile;
import dev.aksarok.rpgGame.utils.Utils;

public class World {

    private Handler handler;
    private int width, height;//x Tile
    private int spawnX, spawnY;
    private int[][] tiles;
    private int tileLengthX = 10, tileLengthY = 10;
    private String worldName;

    //ITEM
    private ItemManager itemManager;

    //Entities
    private EntityManager entityManager;

    public World(Handler handler, String worldName, String path, EntityManager entityManager) {
        this.handler = handler;
        this.worldName = worldName;
        this.entityManager = entityManager;
        itemManager = new ItemManager(handler);
  
        loadWorld(path);
        
        if(entityManager.getPlayer() != null){
            entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        }
    }

    public void tick() {
        if(handler.getGame().gameState.getActiveWorld() != worldName) { return;}
        this.entityManager.tick();
        itemManager.tick();
        
        if(entityManager.getPlayer() == null) {
            System.out.println("??");
        }
    }

    public void render(Graphics g) {
        if(handler.getGame().gameState.getActiveWorld() != worldName) { return;}
        //TILES
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        //ITEMS
        itemManager.render(g);
        //Entities
        this.entityManager.render(g);
    }
    
    public void renderEditor(Graphics g, int overworldX, int overworldY, int zoom) {
        //width = zoom;
        //height = zoom;
        width = zoom*tileLengthX;
        height = zoom*tileLengthY;
        
        for (int y = overworldY; y < overworldY+(tileLengthY * zoom); y += zoom) {
            for (int x = overworldX; x < overworldX+(tileLengthX*zoom); x += zoom) {
                getTile((x-overworldX)/zoom, (y-overworldY)/zoom).render(g, (int) x, (int) y, zoom, zoom);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.darkZoneTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.dungeonFloorTile;
        }

        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        
        if(file == null){
            return;
        }
        
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileLengthX() {
        return tileLengthX;
    }

    public int getTileLengthY() {
        return tileLengthY;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }
    
    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    private void end () {
        if(entityManager.getPlayer() == null) {
            System.out.println("??");
        }
    }
    
    public void setNewTileSize(int width, int height) {
        int[][] tilesOld = tiles;
        int lengthXOld = tileLengthX;
        int lengthYOld = tileLengthY;
        tiles = new int [width] [height];
        tileLengthX = width;
        tileLengthY = height;
        
        
        if(tilesOld == null) {
            return;
        }
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(x < lengthXOld && y < lengthYOld) {
                    tiles[x][y] = tilesOld[x][y];
                    System.out.println("jejej");
                }
            }
        }
    }
    
    public void setTile(int x, int y, int id) {
        this.tiles[x][y] = id;
        //System.out.println(tiles[x][y]);
    }
}
