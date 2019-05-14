package dev.aksarok.rpgGame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 16, height = 16, unitHeight = 32;

    public static Font font28, font20, font48;

    public static BufferedImage dungeonFloor, box, darkZone, wall01, wall02, wall03;
    public static BufferedImage itm_redPotion, itm_bluePotion;
    public static BufferedImage[] player_down, player_up, player_right, player_left;
    public static BufferedImage[] ghost01_down, ghost01_up, ghost01_right, ghost01_left;
    public static BufferedImage[] btn_start, bg_start_menu, theTitle;
    
    public static BufferedImage[] ent_box;
    public static BufferedImage inventoryScreen;
    
    //
    
    public static BufferedImage floorStone1, floorStone2, floorStone3, floorStone4, floorStone5, floorStone6, floorStone7, floorStone8, floorStone9;
    public static BufferedImage topColumn, midColumn, botColumn;
    
    public static void init() {
        font48 = FontLoader.loadFont("res/fonts/slkscr.ttf", 48);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/0x72_16x16DungeonTileset.v4.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/reaper_1.png"));
        SpriteSheet ghost01Sheet = new SpriteSheet(ImageLoader.loadImage("/sprites/ghost1.png"));
        SpriteSheet potionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/witchtiles_3.png"));
        SpriteSheet inventoryScreenSheet = new SpriteSheet(ImageLoader.loadImage("/gui/inventoryScreen.png"));
        
        //GUI SHEET
        SpriteSheet bgSheet = new SpriteSheet(ImageLoader.loadImage("/gui/bg_start_menu.jpg"));
        SpriteSheet btn_longBlue = new SpriteSheet(ImageLoader.loadImage("/gui/buttonLong_blue.png"));
        SpriteSheet btn_longBlue2 = new SpriteSheet(ImageLoader.loadImage("/gui/buttonLong_blue_pressed.png"));
        
        //BG Sheet
        SpriteSheet title = new SpriteSheet(ImageLoader.loadImage("/gui/Aksarok-Rpg.png"));
        SpriteSheet bgSheet1 = new SpriteSheet(ImageLoader.loadImage("/gui/bg/bg01.png"));
        SpriteSheet bgSheet2 = new SpriteSheet(ImageLoader.loadImage("/gui/bg/bg02.png"));
        SpriteSheet bgSheet3 = new SpriteSheet(ImageLoader.loadImage("/gui/bg/bg03.png"));
        SpriteSheet bgSheet4 = new SpriteSheet(ImageLoader.loadImage("/gui/bg/bg04.png"));
        SpriteSheet bgSheet5 = new SpriteSheet(ImageLoader.loadImage("/gui/bg/bg05.png"));
        
        //ITEMS
        itm_redPotion = potionSheet.crop(297, 102, 26, 35);
        itm_bluePotion = potionSheet.crop(345, 102, 26, 35);

        //GUI
        inventoryScreen = inventoryScreenSheet.crop(0, 0, inventoryScreenSheet.getImgWidth(), inventoryScreenSheet.getImgHeight());
        
        theTitle = new BufferedImage[1];
        theTitle[0] = title.crop(0,0,title.getImgWidth(), title.getImgHeight());
        
        bg_start_menu = new BufferedImage[5];
        bg_start_menu[0] = bgSheet1.crop(0, 0, bgSheet1.getImgWidth(), bgSheet1.getImgHeight());
        bg_start_menu[1] = bgSheet2.crop(0, 0, bgSheet2.getImgWidth(), bgSheet2.getImgHeight());
        bg_start_menu[2] = bgSheet3.crop(0, 0, bgSheet3.getImgWidth(), bgSheet3.getImgHeight());
        bg_start_menu[3] = bgSheet4.crop(0, 0, bgSheet4.getImgWidth(), bgSheet4.getImgHeight());
        bg_start_menu[4] = bgSheet5.crop(0, 0, bgSheet5.getImgWidth(), bgSheet5.getImgHeight());
        
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
        
        ghost01_down = new BufferedImage[3];
        ghost01_up = new BufferedImage[3];
        ghost01_right = new BufferedImage[3];
        ghost01_left = new BufferedImage[3];
        
        ghost01_down[0] = ghost01Sheet.crop(5, 6, width, unitHeight);
        ghost01_down[1] = ghost01Sheet.crop(56, 6, width, unitHeight);
        ghost01_down[2] = ghost01Sheet.crop(30, 6, width, unitHeight);//Stand
        ghost01_up[0] = ghost01Sheet.crop(5, 112, width, unitHeight);
        ghost01_up[1] = ghost01Sheet.crop(56, 112, width, unitHeight);
        ghost01_up[2] = ghost01Sheet.crop(30, 112, width, unitHeight);//Stand
        ghost01_left[0] = ghost01Sheet.crop(5, 42, width, unitHeight);
        ghost01_left[1] = ghost01Sheet.crop(56, 42, width, unitHeight);
        ghost01_left[2] = ghost01Sheet.crop(30, 42, width, unitHeight);//Stand
        ghost01_right[0] = ghost01Sheet.crop(5, 78, width, unitHeight);
        ghost01_right[1] = ghost01Sheet.crop(56, 78, width, unitHeight);
        ghost01_right[2] = ghost01Sheet.crop(30, 78, width, unitHeight);//Stand
        
        
        ent_box = new BufferedImage[7]; //3 hit 4Destroy

        box = sheet.crop(80, 107, width, 21);
        //ent_box[0] = destructObjSheet.crop(x, y, width, height);
        
        //TILES
        dungeonFloor = sheet.crop(64, 112, width, height);

        darkZone = sheet.crop(64, 96, width, height);
        wall01 = sheet.crop(0, 16, width, height);
        wall02 = sheet.crop(16, 16, width, height);
        wall03 = sheet.crop(32, 16, width, height);
        
        //
        floorStone1 = sheet.crop(0, 96, width, height);
        floorStone2 = sheet.crop(16, 96, width, height);
        floorStone3 = sheet.crop(32, 96, width, height);
        floorStone4 = sheet.crop(0, 112, width, height);
        floorStone5 = sheet.crop(16, 112, width, height);
        floorStone6 = sheet.crop(32, 112, width, height);
        floorStone7 = sheet.crop(0, 128, width, height);
        floorStone8 = sheet.crop(16, 128, width, height);
        floorStone9 = sheet.crop(32, 128, width, height);
        
        topColumn = sheet.crop(96, 48, width, height);
        midColumn = sheet.crop(96, 64, width, height);
        botColumn = sheet.crop(96, 80, width, height);
    }
}
