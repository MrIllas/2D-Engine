/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author alu2015324
 */
public class UIAnimated extends UIObject {
    
    private BufferedImage[] frames;
    private int speed, index;
    private long lastTime, timer;
    
    
    public UIAnimated(float x, float y, int width, int height, BufferedImage[] frames, int speed) {
        super(x, y, width, height);
        this.frames = frames;
        this.speed = speed;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed) {
            index++;
            timer = 0;
            if(index >= frames.length) {
                index = 0;
            } 
        }
        g.drawImage(frames[index], (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick(MouseEvent e) {
    }
    
}
