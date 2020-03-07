/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.states.MapEditor;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.display.Display;
import dev.aksarok.rpgGame.gui.UIManager;
import dev.aksarok.rpgGame.input.KeyManager;
import dev.aksarok.rpgGame.input.MouseManager;
import dev.aksarok.rpgGame.input.MouseWheelManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author Robert
 */
public abstract class MenuWindow extends Thread implements Runnable{
   
    /*Classes*/
    protected Handler handler;
    private UIManager uiManager;
    
    //Inputs
    protected MouseManager mouseManager;
    protected KeyManager keyManager;
    protected MouseWheelManager mouseWheelManager;
    
    //Render
    protected BufferStrategy bs;
    protected Graphics g;
    
    /*State variables*/
    private boolean open = false;
    private boolean running = false;
    
    /*Window variables*/
    protected Display display;
    protected int width, height;
    protected String title;
    
    public MenuWindow(Handler handler, String title, int width, int height) {
        this.handler = handler;
        this.title = title;
        this.width = width;
        this.height = height;
        
        this.uiManager = new UIManager(handler);
        this.mouseManager = new MouseManager();
        this.keyManager = new KeyManager();
        this.mouseWheelManager = new MouseWheelManager();
    }
    
    public abstract void tick();
    
    public abstract void render();

    public boolean isOpen() {
        return open;
    }

    public void turnOpen() {
        this.open = !open;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay() {
        this.display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        this.display.getFrame().addMouseListener(mouseManager);
        this.display.getFrame().addMouseMotionListener(mouseManager);
        this.display.getCanvas().addMouseListener(mouseManager);
        this.display.getCanvas().addMouseMotionListener(mouseManager);
        this.display.getFrame().addMouseWheelListener(mouseWheelManager);
        
        this.display.getFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    /**Getter and setters**/
    
}
