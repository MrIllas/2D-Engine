/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states.MapEditor;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.gui.InputText;
import dev.aksarok.rpgGame.utils.Colors;
import dev.aksarok.rpgGame.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Robert
 */
public class MapVariablesMenu extends MenuWindow {
    
    /*Map Variables*/
    private String mapName = "New World";
    private int mapX = 10;
    private int mapY = 10;
    
     /*Text variabele*/
    private int marginTop = 20, marginLeft = 10;
    
    
    InputText itMapName;
    InputText itXMap;
    InputText itYMap;
    
    public MapVariablesMenu(Handler handler) {
        super(handler, "Map Variables", 400, 400);
        
        this.itMapName = new InputText(marginLeft, 30, 240, 20, 10);
        this.itXMap = new InputText(marginLeft + 20, 80, 95, 20, 3);
        this.itYMap = new InputText(marginLeft + 140, 80, 100, 20, 3);
        
        this.itMapName.setText(mapName);
        this.itXMap.setText(String.valueOf(mapX));
        this.itYMap.setText(String.valueOf(mapY));
        
    }

    @Override
    public void tick() {
        //MapName
        itMapName.tick(mouseManager, keyManager);
        
        //X and Y
        itXMap.tick(mouseManager, keyManager);
        itYMap.tick(mouseManager, keyManager);
        
        if((int) keyManager.getKey() == 10) { //Enter
            System.out.println("Saving Map Values!");
            saveChanges();
        }
    }
    
    private void saveChanges() {
        if(itMapName.getText() != "") {
            this.mapName = itMapName.getText();
        }
        if(itXMap.getText() != "" && itYMap.getText() != "") {
            this.mapX = Utils.parseInt(itXMap.getText());
            this.mapY = Utils.parseInt(itYMap.getText());
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
        toDraw(g);
        //END DRAWING
        bs.show();
        g.dispose();
    }
    
    private void toDraw(Graphics g) {
        //Background
        g.setColor(Colors.background);
        g.fillRect(0, 0, 400, 400);
        
        //MapName
        Text.drawString(g, "Map name", marginLeft, marginTop, false, Colors.onText, Assets.font15);
        itMapName.render(g);
        
        //X and Y
        Text.drawString(g, "Map Size", marginLeft, 70, false, Colors.onText, Assets.font15);
        Text.drawString(g, "X", marginLeft, 96, false, Colors.onText, Assets.font20);
        Text.drawString(g, "Y", marginLeft + 120, 96, false, Colors.onText, Assets.font20);
        itXMap.render(g);
        itYMap.render(g);
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }
    
    
}
