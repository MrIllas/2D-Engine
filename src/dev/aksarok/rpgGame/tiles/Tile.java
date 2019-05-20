package dev.aksarok.rpgGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF HERE
    public static Tile[] tiles = new Tile[400]; //Conte 1 copia de cada tile del joc
    public static Tile dungeonFloorTile = new DungeonFloorTile(0);
    public static Tile boxTile = new BoxTile(1);
    public static Tile darkZoneTile = new DarkZoneTile(9);
    public static Tile wall01Tile = new Wall01Tile(2);
    public static Tile wall02Tile = new Wall02Tile(3);
    public static Tile wall03Tile = new Wall03Tile(4);

    public static Tile floorStone1 = new FloorStone1(21);
    public static Tile floorStone2 = new FloorStone2(22);
    public static Tile floorStone3 = new FloorStone3(23);
    public static Tile floorStone4 = new FloorStone4(24);
    public static Tile floorStone5 = new FloorStone5(25);
    public static Tile floorStone6 = new FloorStone6(26);
    public static Tile floorStone7 = new FloorStone7(27);
    public static Tile floorStone8 = new FloorStone8(28);
    public static Tile floorStone9 = new FloorStone9(29);
    public static Tile floorStone10 = new FloorStone10(30);
    public static Tile floorStone11 = new FloorStone11(31);
    public static Tile floorStone12 = new FloorStone12(32);

    public static Tile topColumn = new TopColumn(41);
    public static Tile midColumn = new MidColumn(42);
    public static Tile botColumn = new BotColumn(43);

    public static Tile stairs1 = new Stairs1(44);
    public static Tile stairs2 = new Stairs2(45);
    public static Tile stairs3 = new Stairs3(46);
    public static Tile stairs4 = new Stairs4(47);
    
    public static Tile scull = new Scull(48);
    
    public static Tile topFountain = new TopFountain(50);
    public static Tile midFRed = new MidFRed(51);
    public static Tile midFGreen = new MidFGreen(52);
    public static Tile midFBlue = new MidFBlue(53);
    public static Tile botFRed = new BotFRed(54);
    public static Tile botFGreen = new BotFGreen(55);
    public static Tile botFBlue = new BotFBlue(56);
    //CLASS

    
    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected BufferedImage texture;
    protected final int id;
    protected BufferedImage background;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        if (background != null) {
                    g.drawImage(background, x, y, TILEWIDTH, TILEHEIGHT, null);
                }
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);

    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setBackground(BufferedImage bf) {
        this.background = bf;
    }
}
