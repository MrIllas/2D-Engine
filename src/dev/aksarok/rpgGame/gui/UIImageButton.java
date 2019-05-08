package dev.aksarok.rpgGame.gui;

import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;
    
    private String text;
    private int textX;
    private int textY;
    
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, String text, int textX, int textY, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
        this.text = text;
        this.textX = textX;
        this.textY = textY;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (hovering && images.length == 2) {
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
        
        if(text != null) {
            Text.drawString(g, text, (int) x + textX, (int) y + textY, true, Color.WHITE, Assets.font48);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
    
    //GETTERS AND SETTERS
    
    public void setText(String text) {
        this.text = text;
    }

}
