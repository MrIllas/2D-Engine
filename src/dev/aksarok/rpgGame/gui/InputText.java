/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.gui;

import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import dev.aksarok.rpgGame.input.KeyManager;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.utils.Colors;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Robert
 */
public class InputText {
    /*DATA*/
    private String text = "";
    private char key;
    private int maxChars = 10;
    
    /*GUI*/
    private int x = 0, y = 0;
    private int height = 0, width = 0;
    private Font font;
    private float alpha = 0.5f;
    
    private Color bColor = Colors.surface;
    private Color txtColor = Color.white;
    /**/
    
    private boolean focused = false;
    private boolean hovering = false;
    
    Rectangle bounds;
    public InputText(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle((int) x, (int) y, width, height);
    }
    public InputText(int x, int y, int width, int height, int maxChars) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxChars = maxChars;
        this.bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public void tick(MouseManager mm, KeyManager km) {
        if(bounds.contains(mm.getMouseX(), mm.getMouseY())) {
            hovering = true;
            if(mm.isLeftPressed()) {
                focused = true;
            }
        }else {
            hovering = false;
            if(mm.isLeftPressed()) {
                focused = false;
            }
        }
        if(!focused) {
            return;
        }
        
//        this.x = mm.getMouseX();
//        this.y = mm.getMouseY();
//        this.bounds.x = this.x;
//        this.bounds.y = this.y;
//        mm.printPosition();
        
        //Input
        key = km.getKey();
         
        if((int)key == 8){ //REMOVE
            if(!text.isEmpty()) {
                text = removeLastChar(text);
            }
        }
        else if((int) key == 10) { //ENTER
            focused = false;
        }
        else if (key != 0 && text.length() <= maxChars) {
            text += key;
            key = 0;
        }
    }
    
    
    public void render(Graphics g) {
        background(g);
        Text.drawString(g, text, x+4, y+15, false, txtColor, Assets.font15);
        
        if(focused) {
            g.setColor(Color.red);
            g.drawRect(x-1, y-1, width+1, height+1);
        }
    }
    
    private void background(Graphics g) {
        g.setColor(bColor);
        g.fillRect(x, y, width, height);
    }
    
    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    
    /*Getters and Setters*/
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
