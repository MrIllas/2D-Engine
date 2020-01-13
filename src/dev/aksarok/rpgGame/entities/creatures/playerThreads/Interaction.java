/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.entities.creatures.playerThreads;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.entities.Entity;
import dev.aksarok.rpgGame.entities.creatures.Player;
import java.awt.Rectangle;

/**
 *
 * @author Robert
 */
public class Interaction extends Thread{
    
    private Handler handler;
    private Player player;
    private Entity e;
    private Rectangle iArea;
    
    private boolean endCheck = true;
    
    
    //Data for the player
    private long interactId = 0;
    private String interactName = "";
    private boolean aMenuIsOpen = false;
    
    public Interaction(Handler handler, Entity e, Rectangle iArea, Player player) {
        this.handler = handler;
        this.e = e;
        this.iArea = iArea;
        this.player = player;
    }
    
    public void run() {
        while(e.getCollisionBounds(0f, 0f).intersects(iArea)) {
            dorm(50);
            interactId = e.getId();
            interactName = ""+e.getName()+" || Id: "+interactId;
            iAreaUpdate();
           
            if(e.getActiveMenu()){
                aMenuIsOpen = true;
            }else {
                aMenuIsOpen = false;
            }
            e.setPrintFeed(true);
        }
        e.setPrintFeed(false);
        endCheck = false;
    }
    
    private void dorm(int value) {
        try {
            sleep(value);
        } catch (InterruptedException ex) {
            System.out.println("Error al dormir");
        }
    }
    
    private void iAreaUpdate() {
        this.iArea = player.getiArea();
    }
    
    public boolean endCheck() {
        return endCheck;
    }

    public long getInteractId() {
        return interactId;
    }

    public String getInteractName() {
        return interactName;
    }

    public boolean isaMenuIsOpen() {
        return aMenuIsOpen;
    }
}
