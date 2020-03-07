package dev.aksarok.rpgGame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.states.MapEditor.TileMenu;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed, scrollPressed;
    private boolean scrollDraggin;
    private boolean draggStart;
    private int initXDragg, initYDragg, mouseX, mouseY;
    private UIManager uiManager;
    private TileMenu tileMenu;

    public MouseManager() {

    }

    //GETTERS AND SETTERS
    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
    
    public UIManager getUIManager() {
        return this.uiManager;
    }

    public TileMenu getTileMenu() {
        return tileMenu;
    }

    public void setTileMenu(TileMenu tileMenu) {
        this.tileMenu = tileMenu;
    }
    //IMPLEMENTED METHODS
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if(scrollPressed) {
            scrollDraggin = false;
        }else {
            scrollDraggin = true;
        }
    }

    public boolean isLeftPressed() { 
        return leftPressed;
    }
    
    public boolean isRightPressed() {
        return rightPressed;
    }
    
    public boolean isScrollPressed() {
        return scrollPressed;
    }

    public boolean isScrollDraggin() {
        return scrollDraggin;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (uiManager != null) {
            uiManager.onMouseMove(e);
        }
       // System.out.println("jesus");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }else if(e.getButton() == MouseEvent.BUTTON2) {
            scrollPressed = true;
        }else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
        
        if(!draggStart) {
            draggStart = true;
            initXDragg = getMouseX();
            initYDragg = getMouseY();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }else if( e.getButton() == MouseEvent.BUTTON2) {
            scrollPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
        
        if(draggStart){
            draggStart = false;
            initXDragg = 0;
            initYDragg = 0;
        }

        if (uiManager != null) {
            uiManager.onMouseRelease(e);
        }

        if(tileMenu != null) {
            tileMenu.onMouseRelease(e);
        }
    }
    
    
    public void printPosition() {
        System.out.println("(NORMAL) X: "+getMouseX()+" | Y: "+getMouseY());
        System.out.println("(DRAGGED) X: "+getInitXDragg()+" | Y: "+getInitYDragg());
    }


    public int getInitXDragg() {
        return initXDragg;
    }

    public int getInitYDragg() {
        return initYDragg;
    }
    
    
}
