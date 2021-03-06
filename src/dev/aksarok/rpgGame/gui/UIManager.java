package dev.aksarok.rpgGame.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.aksarok.rpgGame.Handler;

public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    public void tick() {
        for (UIObject o : objects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : objects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }

    public void removeObject(UIObject o) {
        objects.remove(o);
    }
    
    public void removeAll() {
        for(int c = 0; c < objects.size(); c++) {
            objects.remove(objects.get(c));
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public UIObject getLastObject() {
        return objects.get(objects.size() - 1);
    }

    public UIObject getObject(int i) {
        return objects.get(i);
    }
}
