/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states.MapEditor;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.tiles.Tile;
import dev.aksarok.rpgGame.utils.Colors;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Robert
 */
public class TileMenu extends MenuWindow {
    
    private int selectedTileID;
    public ArrayList<TileSelector> tsList;
    
    public TileMenu(Handler handler) {
        super(handler, "Tile Menu", Tile.TILEWIDTH*8, Tile.TILEHEIGHT*20);
        this.tsList = new ArrayList<TileSelector>();
        
        this.mouseManager.setTileMenu(this);
    }
    
    @Override
    public void tick() {
        for(TileSelector sel : tsList) {
           sel.tick(mouseManager);
           if(sel.isSelected()){
               selectedTileID = sel.getTileId();
           }
        }
    }
    
    public void onMouseRelease(MouseEvent e) {
        for(TileSelector sel : tsList) {
           sel.onMouseRelease(e);
        }
    }
    
    
    @Override
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //CLEAR SCREEN
        g.clearRect(0,0, width, height);
        //DRAW HERE!
            //Background
        g.setColor(Colors.background);
        g.fillRect(0, 0, Tile.TILEWIDTH*8, Tile.TILEHEIGHT*20);
            //-----
        int tHeight = 0;
        int tWidth = 0; 
        int countHeight = 0;
        
        for(int aux = 0; aux < Tile.tiles.length; aux++){
            if(Tile.tiles[aux] != null) {
                Tile.tiles[aux].render(g, tWidth, tHeight);
                tsList.add(new TileSelector(handler, tWidth, tHeight, Tile.tiles[aux].getId()));
                
                countHeight++;
                tWidth += Tile.TILEWIDTH;
                if(countHeight == 8) {
                    tHeight += Tile.TILEHEIGHT;
                    tWidth = 0;
                    countHeight = 0;
                }
            }
        }
        
        for(TileSelector sel : tsList) {
           sel.render(g);
        }
        //END DRAWING
        bs.show();
        g.dispose(); 
    }

    public int getSelectedTileID() {
        return selectedTileID;
    }

    public void setSelectedTileID(int selectedTileID) {
        this.selectedTileID = selectedTileID;
    }
}
