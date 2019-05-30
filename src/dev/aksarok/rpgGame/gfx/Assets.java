package dev.aksarok.rpgGame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 16, height = 16, unitHeight = 32;

    public static Font font28, font20, font48, font15;

    public static BufferedImage dungeonFloor, box, darkZone, wall01, wall02, wall03;
    public static BufferedImage topWall1, topWall2, topWall3;
    public static BufferedImage itm_redPotion, itm_bluePotion;
    public static BufferedImage[] player_down, player_up, player_right, player_left,
                                  player_attDown, player_attUp, player_attRight, player_attLeft;
    public static BufferedImage[] ghost01_down, ghost01_up, ghost01_right, ghost01_left;
    public static BufferedImage[] btn_start, bg_start_menu, theTitle;

    public static BufferedImage[] ent_box;
    public static BufferedImage[] ent_chest01, ent_chest02;
    public static BufferedImage inventoryScreen;
    public static BufferedImage[] healthBar, healthBarFull;
    
    //
    public static BufferedImage floorStone1, floorStone2, floorStone3, floorStone4, floorStone5, floorStone6, floorStone7, floorStone8, floorStone9, floorStone10, floorStone11, floorStone12;
    public static BufferedImage topColumn, midColumn, botColumn;
    public static BufferedImage stairs1, stairs2, stairs3, stairs4;
    public static BufferedImage scull;
    public static BufferedImage topFountain, midFRed, midFGreen, midFBlue, botFRed, botFGreen, botFBlue;
    public static BufferedImage ladder;
    public static void init() {
        font48 = FontLoader.loadFont("res/fonts/slkscr.ttf", 48);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);
        font15 = FontLoader.loadFont("res/fonts/slkscr.ttf", 15);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/0x72_16x16DungeonTileset.v4.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/0x72_16x16DungeonTileset_walls.v2.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/knight.png"));
        SpriteSheet ghost01Sheet = new SpriteSheet(ImageLoader.loadImage("/sprites/ghost1.png"));
        SpriteSheet potionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/witchtiles_3.png"));
        SpriteSheet inventoryScreenSheet = new SpriteSheet(ImageLoader.loadImage("/gui/inventoryScreen.png"));
        SpriteSheet healthBarsSheet = new SpriteSheet(ImageLoader.loadImage("/gui/healthBars.png"));
        //GUI SHEET
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
        healthBar = new BufferedImage[2];
        healthBar[0] = healthBarsSheet.crop(0, 0, 107, 14);//Base x y w h
        healthBar[1] = healthBarsSheet.crop(0, 43, 91, 7);//Vida buida
        healthBarFull = new BufferedImage[2];
        //healthBarFull[0] = healthBarsSheet.crop(0, 34, 91, 7);//Vida full 1
        healthBarFull[0] = healthBarsSheet.crop(0, 25, 91, 7);//Vida full 2
        healthBarFull[1] = healthBarsSheet.crop(0, 16, 91, 7);//Vida full 3
        
        inventoryScreen = inventoryScreenSheet.crop(0, 0, inventoryScreenSheet.getImgWidth(), inventoryScreenSheet.getImgHeight());

        theTitle = new BufferedImage[1];
        theTitle[0] = title.crop(0, 0, title.getImgWidth(), title.getImgHeight());

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
        player_down = new BufferedImage[9];
        player_up = new BufferedImage[9];
        player_right = new BufferedImage[9];
        player_left = new BufferedImage[9];
        player_attDown = new BufferedImage[6];
        player_attUp = new BufferedImage[6];
        player_attRight = new BufferedImage[6];
        player_attLeft = new BufferedImage[6];       
        player_down[8] = playerSheet.crop(16, 640, 32, 64);//Stand
        player_down[7] = playerSheet.crop(16+64, 640, 32, 64);
        player_down[6] = playerSheet.crop(16+64*2, 640, 32, 64);
        player_down[5] = playerSheet.crop(16+64*3, 640, 32, 64);
        player_down[4] = playerSheet.crop(16+64*4, 640, 32, 64);
        player_down[3] = playerSheet.crop(16+64*5, 640, 32, 64);
        player_down[2] = playerSheet.crop(16+64*6, 640, 32, 64);
        player_down[1] = playerSheet.crop(16+64*7, 640, 32, 64);
        player_down[0] = playerSheet.crop(16+64*8, 640, 32, 64);
        player_up[8] = playerSheet.crop(16, 512, 32, 64); //114 y
        player_up[7] = playerSheet.crop(16+64, 512, 32, 64);
        player_up[6] = playerSheet.crop(16+64*2, 512, 32, 64);//Stand
        player_up[5] = playerSheet.crop(16+64*3, 512, 32, 64);
        player_up[4] = playerSheet.crop(16+64*4, 512, 32, 64);
        player_up[3] = playerSheet.crop(16+64*5, 512, 32, 64);
        player_up[2] = playerSheet.crop(16+64*6, 512, 32, 64);
        player_up[1] = playerSheet.crop(16+64*7, 512, 32, 64);
        player_up[0] = playerSheet.crop(16+64*8, 512, 32, 64);
        player_left[8] = playerSheet.crop(16, 576, 32, 64);
        player_left[7] = playerSheet.crop(16+64, 576, 32, 64);
        player_left[6] = playerSheet.crop(16+64*2, 576, 32, 64);//Stand
        player_left[5] = playerSheet.crop(16+64*3, 576, 32, 64);
        player_left[4] = playerSheet.crop(16+64*4, 576, 32, 64);
        player_left[3] = playerSheet.crop(16+64*5, 576, 32, 64);
        player_left[2] = playerSheet.crop(16+64*6, 576, 32, 64);
        player_left[1] = playerSheet.crop(16+64*7, 576, 32, 64);
        player_left[0] = playerSheet.crop(16+64*8, 576, 32, 64);
        player_right[8] = playerSheet.crop(16, 704, 32, 64);
        player_right[7] = playerSheet.crop(16+64, 704, 32, 64);
        player_right[6] = playerSheet.crop(16+64*2, 704, 32, 64);//Stand
        player_right[5] = playerSheet.crop(16+64*3, 704, 32, 64);
        player_right[4] = playerSheet.crop(16+64*4, 704, 32, 64);
        player_right[3] = playerSheet.crop(16+64*5, 704, 32, 64);
        player_right[2] = playerSheet.crop(16+64*6, 704, 32, 64);
        player_right[1] = playerSheet.crop(16+64*7, 704, 32, 64);
        player_right[0] = playerSheet.crop(16+64*8, 704, 32, 64);
        player_attDown[0] = playerSheet.crop(0, 64 * 27, 192, 192);
        player_attDown[1] = playerSheet.crop(192 * 2, 64 * 27, 192, 192);
        player_attDown[2] = playerSheet.crop(192 * 3, 64 * 27, 192, 192);
        player_attDown[3] = playerSheet.crop(192 * 4, 64 * 27, 192, 192);
        player_attDown[4] = playerSheet.crop(192 * 5, 64 * 27, 192, 192);
        player_attDown[5] = playerSheet.crop(192 * 6, 64 * 27, 192, 192);
        player_attUp[0] = playerSheet.crop(0, 64 * 21, 192, 192);
        player_attUp[1] = playerSheet.crop(192 * 2, 64 * 21, 192, 192);
        player_attUp[2] = playerSheet.crop(192 * 3, 64 * 21, 192, 192);
        player_attUp[3] = playerSheet.crop(192 * 4, 64 * 21, 192, 192);
        player_attUp[4] = playerSheet.crop(192 * 5, 64 * 21, 192, 192);
        player_attUp[5] = playerSheet.crop(192 * 6, 64 * 21, 192, 192);
        player_attLeft[0] = playerSheet.crop(0, 64 * 24, 192, 192);
        player_attLeft[1] = playerSheet.crop(192 * 2, 64 * 24, 192, 192);
        player_attLeft[2] = playerSheet.crop(192 * 3, 64 * 24, 192, 192);
        player_attLeft[3] = playerSheet.crop(192 * 4, 64 * 24, 192, 192);
        player_attLeft[4] = playerSheet.crop(192 * 5, 64 * 24, 192, 192);
        player_attLeft[5] = playerSheet.crop(192 * 6, 64 * 24, 192, 192);
        player_attRight[0] = playerSheet.crop(0, 64 * 30, 192, 192);
        player_attRight[1] = playerSheet.crop(192 * 2, 64 * 30, 192, 192);
        player_attRight[2] = playerSheet.crop(192 * 3, 64 * 30, 192, 192);
        player_attRight[3] = playerSheet.crop(192 * 4, 64 * 30, 192, 192);
        player_attRight[4] = playerSheet.crop(192 * 5, 64 * 30, 192, 192);
        player_attRight[5] = playerSheet.crop(192 * 6, 64 * 30, 192, 192);
        
        
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

        ent_chest01 = new BufferedImage[3]; //Normal
        ent_chest01[0] = sheet.crop(224, 178, 16, 14); //Tancat
        ent_chest01[1] = sheet.crop(224, 194, 16, 14); //Obert buit
        ent_chest01[2] = sheet.crop(224, 210, 16, 14); //Obert ple

        ent_chest02 = new BufferedImage[3]; //Daurat
        ent_chest02[0] = sheet.crop(240, 178, 16, 14);
        ent_chest02[1] = sheet.crop(240, 194, 16, 14);
        ent_chest02[2] = sheet.crop(240, 210, 16, 14);

        ent_box = new BufferedImage[7]; //3 hit 4Destroy

        box = sheet.crop(80, 107, width, 21);
        //ent_box[0] = destructObjSheet.crop(x, y, width, height);

        //TILES
        dungeonFloor = sheet.crop(32, 48, width, height);

        darkZone = sheet.crop(64, 96, width, height);
        wall01 = sheet.crop(0, 16, width, height);
        wall02 = sheet.crop(16, 16, width, height);
        wall03 = sheet.crop(32, 16, width, height);
        topWall1 = sheet.crop(0, 0, width, height);
        topWall2 = sheet.crop(16, 0, width, height);
        topWall3 = sheet.crop(32, 0, width, height);

        //
        floorStone1 = sheet.crop(0, 96, width, height);
        floorStone2 = sheet.crop(16, 96, width, height);
        floorStone3 = sheet.crop(32, 96, width, height);
        floorStone4 = sheet.crop(48, 96, width, height);
        floorStone5 = sheet.crop(0, 112, width, height);
        floorStone6 = sheet.crop(16, 112, width, height);
        floorStone7 = sheet.crop(32, 112, width, height);
        floorStone8 = sheet.crop(48, 112, width, height);
        floorStone9 = sheet.crop(0, 128, width, height);
        floorStone10 = sheet.crop(16, 128, width, height);
        floorStone11 = sheet.crop(32, 128, width, height);
        floorStone12 = sheet.crop(48, 128, width, height);

        topColumn = sheet.crop(96, 48, width, height);
        midColumn = sheet.crop(96, 64, width, height);
        botColumn = sheet.crop(96, 80, width, height);

        stairs1 = sheet.crop(80, 15, width, height);
        stairs2 = sheet.crop(96, 15, width, height);
        stairs3 = sheet.crop(80, 31, width, height);
        stairs4 = sheet.crop(96, 31, width, height);

        scull = sheet.crop(16, 48, width, height);

        topFountain = sheet.crop(0, 48, width, height);
        midFRed = sheet.crop(0, 64, width, height);
        midFGreen = sheet.crop(16, 64, width, height);
        midFBlue = sheet.crop(32, 64, width, height);
        botFRed = sheet.crop(0, 80, width, height);
        botFGreen = sheet.crop(16, 80, width, height);
        botFBlue = sheet.crop(32, 80, width, height);
        
        ladder = sheet2.crop(2*16, 8*16, width, height);
    }
}
