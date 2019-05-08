package dev.aksarok.rpgGame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 16, height = 16, unitHeight = 32;

    public static Font font28, font20, font48;

    public static BufferedImage dungeonFloor, box, darkZone, wall01, wall02, wall03;
    public static BufferedImage itm_redPotion, itm_bluePotion;
    public static BufferedImage[] player_down, player_up, player_right, player_left;
    public static BufferedImage[] btn_start, bg_start_menu;
    
    public static BufferedImage[] ent_box;
    public static BufferedImage inventoryScreen;

    public static void init() {
        font48 = FontLoader.loadFont("res/fonts/slkscr.ttf", 48);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/0x72_16x16DungeonTileset.v4.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/reaper_1.png"));
        SpriteSheet potionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/witchtiles_3.png"));
        SpriteSheet inventoryScreenSheet = new SpriteSheet(ImageLoader.loadImage("/gui/inventoryScreen.png"));
        
        //GUI SHEET
        SpriteSheet bgSheet = new SpriteSheet(ImageLoader.loadImage("/gui/bg_start_menu.jpg"));
        SpriteSheet btn_longBlue = new SpriteSheet(ImageLoader.loadImage("/gui/buttonLong_blue.png"));
        SpriteSheet btn_longBlue2 = new SpriteSheet(ImageLoader.loadImage("/gui/buttonLong_blue_pressed.png"));
        
        
        //ITEMS
        itm_redPotion = potionSheet.crop(297, 102, 26, 35);
        itm_bluePotion = potionSheet.crop(345, 102, 26, 35);

        //GUI
        inventoryScreen = inventoryScreenSheet.crop(0, 0, inventoryScreenSheet.getImgWidth(), inventoryScreenSheet.getImgHeight());
        
        bg_start_menu = new BufferedImage[1];
        bg_start_menu[0] = bgSheet.crop(0, 0, bgSheet.getImgWidth(), bgSheet.getImgHeight());
        btn_start = new BufferedImage[2];
        btn_start[0] = btn_longBlue.crop(0, 0, btn_longBlue.getImgWidth(), btn_longBlue.getImgHeight());
        btn_start[1] = btn_longBlue2.crop(0, 0, btn_longBlue2.getImgWidth(), btn_longBlue2.getImgHeight());

        //ENTITES
        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_right = new BufferedImage[3];
        player_left = new BufferedImage[3];

        player_down[0] = playerSheet.crop(5, 6, width, unitHeight);
        player_down[1] = playerSheet.crop(56, 6, width, unitHeight);
        player_down[2] = playerSheet.crop(30, 6, width, unitHeight);//Stand
        player_up[0] = playerSheet.crop(5, 112, width, unitHeight); //114 y
        player_up[1] = playerSheet.crop(56, 112, width, unitHeight);
        player_up[2] = playerSheet.crop(30, 112, width, unitHeight);//Stand
        player_left[0] = playerSheet.crop(5, 42, width, unitHeight);
        player_left[1] = playerSheet.crop(56, 42, width, unitHeight);
        player_left[2] = playerSheet.crop(30, 42, width, unitHeight);//Stand
        player_right[0] = playerSheet.crop(5, 78, width, unitHeight);
        player_right[1] = playerSheet.crop(56, 78, width, unitHeight);
        player_right[2] = playerSheet.crop(30, 78, width, unitHeight);//Stand

        ent_box = new BufferedImage[7]; //3 hit 4Destroy

        box = sheet.crop(80, 107, width, 21);
        //ent_box[0] = destructObjSheet.crop(x, y, width, height);
        //TILES
        dungeonFloor = sheet.crop(64, 112, width, height);

        darkZone = sheet.crop(64, 96, width, height);
        wall01 = sheet.crop(0, 16, width, height);
        wall02 = sheet.crop(16, 16, width, height);
        wall03 = sheet.crop(32, 16, width, height);
    }
}