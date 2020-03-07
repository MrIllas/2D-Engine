/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author Robert
 */
public class MouseWheelManager implements MouseWheelListener {
    
    private boolean scrollingDown, scrollingUp;
    
    
    public MouseWheelManager() {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() == 1) {
            scrollingDown = true;
            scrollingUp = false;
        }else if(e.getWheelRotation() == -1) {
            scrollingDown = false;
            scrollingUp = true;
        }
    }
    
  

    public boolean isScrollingDown() {
        if(scrollingDown){
            scrollingDown = false;
            return true;
        }
        return false;
    }

    public boolean isScrollingUp() {
        if(scrollingUp){
            scrollingUp = false;
            return true;
        }
        return false;
    }
    
    
}
