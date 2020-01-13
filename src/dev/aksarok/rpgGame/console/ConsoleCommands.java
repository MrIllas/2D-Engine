/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.aksarok.rpgGame.console;

import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gui.FeedBack;
import dev.aksarok.rpgGame.utils.Utils;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Robert
 */
public class ConsoleCommands {
    private Handler handler;
    private static List<String> register;
    private static List<Color> registerColor;
    
    private final String[] error = {"Error 01: Missing data.",
                                    "Error 02: Invalid data."};
    
    
    protected ConsoleCommands (Handler handler, List register, List registerColor) {
        this.handler = handler;
        this.register = register;
        this.registerColor = registerColor;
    }
    
    protected void enterCommand(String value) {
        
        String[] com = value.split(" ");
        
        switch(com[0].toLowerCase()) {
            case "help":
                if(com.length == 2) {
                    help(com[1]);
                    break;
                }
                help();
                break;
            case "move":
                if(com.length != 3) {
                    Console.addRegister(error[0], Console.red);
                    break;
                }
                float x = Utils.parseFloat(com[1]);
                float y = Utils.parseFloat(com[2]);
                move(x, y);
                break;
            case "debug":
                if(com.length != 2) {
                    Console.addRegister(error[0], Console.red);
                    break;
                }
                
                if(com[1].equalsIgnoreCase("on")){
                    debug(true);
                }else if (com[1].equalsIgnoreCase("off")) {
                    debug(false);
                } else {
                    Console.addRegister(error[1], Console.red);
                }
                break;
            case "clean":
                clean();
                break;
            default:
                none();
        }
    }
    
    private void help() {
        String s0 = "Write help 'command' to get info from that command.";
        String s1 = "List of commands: ";
        String s2 = "   move";
        String s3 = "   clean";
        String s4 = "   debug";
        Console.addRegister(s0, Console.black);
        Console.addRegister(s1, Console.black);
        Console.addRegister(s2, Console.black);
        Console.addRegister(s3, Console.black);
        Console.addRegister(s4, Console.black);
    }
    
    private void help(String value) {
        String s0 = "";
        String s1 = "";
        switch(value.toLowerCase()) {
            case "move":
                s0 = "This command moves you inside a map.";
                s1 = "Ex: move 'x' 'y'";
                break;
            case "clean":
                s0 = "Cleans the command log.";
                break;
            case "debug":
                s0 = "Turns the debug mode on/off";
                s1 = "Ex: debug on";
                break;
            default:
                s0 = "Write help 'command' to get info from that command.";
                s1 = "Sub command invalid.";
                break;
        }
        if(!s0.isEmpty()){
            Console.addRegister(s0, Console.green);
        }
        if(!s1.isEmpty()){
            Console.addRegister(s1, Console.green);
        }
        
    }
    
    private void move(float x, float y) {
        x = (x * 32) - 32;
        y = (y * 32) - 32;
        
        String s0 = "Player moved to the location X: "+(int) x/32+" Y: "+ (int) y/32;
        handler.getWorld().getEntityManager().getPlayer().setX(x);
        handler.getWorld().getEntityManager().getPlayer().setY(y);
        Console.addRegister(s0, Console.green);
    }
    
    private void debug(boolean value) {
        FeedBack.setActive(value);
        if(value) {
            String s0 = "Debug mode on.";
            Console.addRegister(s0, Console.green);  
        }
        else {
            String s0 = "Debug mode off.";
            Console.addRegister(s0, Console.green);
        }
    }
    
    private void clean() {
        register.clear();
        registerColor.clear();
    }
    
    private void none() {
        Console.addRegister("Unrecognized command.", Console.red);
    }
}
