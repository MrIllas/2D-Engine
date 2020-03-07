/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states;

import dev.aksarok.rpgGame.Handler;
import static dev.aksarok.rpgGame.Launcher.SCREEN_HEIGHT;
import static dev.aksarok.rpgGame.Launcher.SCREEN_WIDTH;
import dev.aksarok.rpgGame.entities.EntityManager;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.input.MouseWheelManager;
import dev.aksarok.rpgGame.states.MapEditor.MapVariablesMenu;
import dev.aksarok.rpgGame.states.MapEditor.TileMenu;
import dev.aksarok.rpgGame.utils.Colors;
import dev.aksarok.rpgGame.worlds.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Robert
 */
public class MapEditorState extends State {
    private UIManager uiManager;
    private TileMenu tileMenu;
    private MapVariablesMenu mapVariablesMenu;
    
    //private ArrayList<Integer> tiles = new ArrayList<Integer>();
    
    //Map related
    private World world;
    private EntityManager eManager;
    
    //Tools Related
    private boolean grid = true;
    
    //Variables
    int overworldX = 0, overworldY = 0, lOverworldX = 0, lOverworldY = 0;
    int zoom = 32;
    
    public MapEditorState(Handler handler) {
        super(handler);
        this.tileMenu = new TileMenu(handler);
        this.mapVariablesMenu = new MapVariablesMenu(handler);
        
        uiManager = new UIManager(handler);
        this.eManager = new EntityManager(handler, null);
        this.world = new World(handler, "New World", null, eManager);
        this.world.setNewTileSize(mapVariablesMenu.getMapX(), mapVariablesMenu.getMapY());
    }

    @Override
    public void tick() {
        //EDITOR
        overworldMovement(handler.getMouseManager());
        overworldZoom(handler.getMouseWheelManager());
        tileDrawer(handler.getMouseManager());
        
        world.tick();
        if(mapVariablesMenu.getMapX() != world.getTileLengthX()|| mapVariablesMenu.getMapY() != world.getTileLengthY()) {
            this.world.setNewTileSize(mapVariablesMenu.getMapX(), mapVariablesMenu.getMapY());
        }
        
        
        
        
        //MENUS
        detectHidded();
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F10) && !tileMenu.isOpen()) {
            tileMenu.turnOpen();
            if(tileMenu.isOpen() && tileMenu.getDisplay() == null) {
                tileMenu.setDisplay();
            }else if(tileMenu.isOpen()) {
                tileMenu.getDisplay().getFrame().setVisible(true);
            }
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F9) && !mapVariablesMenu.isOpen()) {
            mapVariablesMenu.turnOpen();
            if(mapVariablesMenu.isOpen()) {
                mapVariablesMenu.setDisplay();
            }
        }
        
        if(tileMenu.isOpen()) {
            tileMenu.tick();
        }
        if(mapVariablesMenu.isOpen()) {
            mapVariablesMenu.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        //Background
        g.setColor(Colors.background);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        //EDITOR
        
        //world.render(g);
        world.renderEditor(g, overworldX, overworldY, zoom);
        
        grid(g);
        
        
        
        //MENUS
        if(tileMenu.isOpen()) {
            tileMenu.render();
        }
        if(mapVariablesMenu.isOpen()) {
            mapVariablesMenu.render();
        }
        
        Text.drawString(g, "oX: "+overworldX+" | oY: "+overworldY, SCREEN_WIDTH - 170, SCREEN_HEIGHT - 7, true, Color.green, Assets.font15);
    }
    
    /**Creators**/
    
    private void detectHidded() {
        if(tileMenu.getDisplay() != null){
            if(!tileMenu.getDisplay().getFrame().isVisible()) {
                tileMenu.turnOpen();
            }
        }
        if(mapVariablesMenu.getDisplay() != null){
            if(!mapVariablesMenu.getDisplay().getFrame().isVisible()) {
                mapVariablesMenu.turnOpen();
            }
        }
    }
    
    //EDITOR
    
    private void grid(Graphics g){
        if(grid) {
            for(int x = 0; x < mapVariablesMenu.getMapX();x++){
                for(int y = 0; y < mapVariablesMenu.getMapY(); y++){
                    g.setColor(Colors.surface);
                    g.drawRect((x*zoom) + overworldX, (y*zoom) + overworldY, zoom, zoom);
                }
            }
        }
    }
    
    
    private void overworldZoom(MouseWheelManager mwm) {
        if(mwm.isScrollingDown()) {
            if(zoom != 96) {
                zoom += 2;
            }
            
        } else if(mwm.isScrollingUp()) {
            if(zoom != 16) {
                zoom -= 2;
            }
        }
    }
    
    private void overworldMovement(MouseManager mm) {  
        if(mm.isScrollPressed()){
            if(mm.getInitXDragg() > mm.getMouseX() && mm.getInitYDragg() > mm.getMouseY() ){
                overworldX = lOverworldX - Math.abs(mm.getInitXDragg() - mm.getMouseX());
                overworldY = lOverworldY - Math.abs(mm.getInitYDragg() - mm.getMouseY());
            }else if(mm.getInitXDragg() < mm.getMouseX() && mm.getInitYDragg() > mm.getMouseY()){
                overworldX = lOverworldX + Math.abs(mm.getInitXDragg() - mm.getMouseX());
                overworldY = lOverworldY - Math.abs(mm.getInitYDragg() - mm.getMouseY());
            }else if(mm.getInitXDragg() > mm.getMouseX() && mm.getInitYDragg() < mm.getMouseY()){
                overworldX = lOverworldX - Math.abs(mm.getInitXDragg() - mm.getMouseX());
                overworldY = lOverworldY + Math.abs(mm.getInitYDragg() - mm.getMouseY());
            }else {
                overworldX = lOverworldX + Math.abs(mm.getInitXDragg() - mm.getMouseX());
                overworldY = lOverworldY + Math.abs(mm.getInitYDragg() - mm.getMouseY());
            }
        }else {
            lOverworldX = overworldX;
            lOverworldY = overworldY;
        }
    }
    
    private void tileDrawer(MouseManager mm) {
        int tOwX= 0; //Tile Overworld X
        int tOwY = 0; //Tile Overwolrd Y
        int tAX = 0; //Tile Array X
        int tAY = 0; //Tile Array Y 
        
        int mouseX = 0;
        int mouseY = 0;
        if(mm.isLeftPressed()) {
            mouseX = mm.getMouseX();
            mouseY = mm.getMouseY();
            
            tOwX = overworldX;
            tOwY = overworldY;
            
            while(tOwX <= mouseX) {
                tOwX += zoom;
                tAX += 1;
            }
            while(tOwY <= mouseY) {
                tOwY += zoom;
                tAY += 1;
            }
            
            tOwX -= zoom;
            tOwY -= zoom;
            tAX -= 1;
            tAY -= 1;
            
            //Evita error al clicar fora de grid
            if(tAY < 0 || tAX < 0) {
                return;
            }
            if(tAX <= mapVariablesMenu.getMapX()-1 && tAY <= mapVariablesMenu.getMapY()-1){
                world.setTile(tAX, tAY, tileMenu.getSelectedTileID());
            }
        }
    }
    
    private void checkVariables() {
        
    }
}
