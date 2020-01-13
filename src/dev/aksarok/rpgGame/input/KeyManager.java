package dev.aksarok.rpgGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean w, s, a, d, k_0;
    public boolean f; //Interaction
    public boolean aUp, aDown, aLeft, aRight;
    
    public char key;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }

            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

        w = keys[KeyEvent.VK_W];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
        k_0 = keys[KeyEvent.VK_0];

        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];

        f = keys[KeyEvent.VK_F];

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        key = (char) e.getKeyChar();
        //key = 0;
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode == 0 || keyCode == keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    public char getKey() {
        char toReturn = key;
        key = 0;
        return toReturn;
    }
}
