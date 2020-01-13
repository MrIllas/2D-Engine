/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.console;

import dev.aksarok.rpgGame.Game;
import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.creatures.Player;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Robert
 */
public class Console {
    
    private Handler handler;
    private ConsoleCommands cc;
    private boolean active = false;
    
    //DATA
    private static List<String> register = new ArrayList<String>();
    private static List<Color> registerColor = new ArrayList<Color>();
    private String sentence = "";
    private char key;
    
    //GUI
    private float alpha = 0.5f;
        
    private int bX = 0, bY = 0;
    private int bWidth = 1280, bHeight = 400;
    private Color bColor = new Color (0.85f, 0.85f, 0.85f, alpha);
    
    private int posLine = 350;
    
    //Text Colors
    protected static Color black = new Color(0, 0, 0);
    protected static Color red = new Color(255, 80, 80);
    protected static Color green = new Color(51, 204, 51);
    
    
    public Console(Handler handler) {
        this.handler = handler;
        this.cc = new ConsoleCommands(handler, register, registerColor);
        //BOX setup
    }
    
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F1)) {
            active = !active;
        }
        if(!active) {
            return;
        }
        
        //Input
        key = handler.getKeyManager().getKey();
        
        if((int)key == 8){
            if(!sentence.isEmpty()) {
                sentence = removeLastChar(sentence);
            }
        }
        else if((int) key == 10) { //ENTER
            if(!sentence.isEmpty()) {
                addRegister(sentence, black);
                cc.enterCommand(sentence);
                sentence = "";
            }
        }
        else if (key != 0) {
            sentence += key;
            key = 0;
        }
        
        //Register
        
    }
    
    public void render(Graphics g) {
        if(!active) {
            return;
        }
        background(g);
        Text.drawString(g, sentence, 10, 390, false, black, Assets.font20);
        
        //Register 
        if(register.isEmpty()){
            return;
        }
        int count = 0;
        for(int c = 0; c < register.size() ; c++) {
            Text.drawString(g, register.get(c), 10, posLine - count, false, registerColor.get(c), Assets.font20);
            count += 20;
        }
    }
    
    private void background(Graphics g) {
        g.setColor(bColor);
        g.fillRect(0, 0, bWidth, bHeight);
    }
    
    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    
    protected static void addRegister(String sentence, Color colorCode) {
        register.add(register.size()-register.size(), sentence);
        registerColor.add(registerColor.size() - registerColor.size(), colorCode);
    }
}
