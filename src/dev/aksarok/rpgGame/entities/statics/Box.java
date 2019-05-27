package dev.aksarok.rpgGame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import static dev.aksarok.rpgGame.utils.Utils.randomInt;
import dev.aksarok.rpgGame.Handler;
import dev.aksarok.rpgGame.gfx.Assets;
import dev.aksarok.rpgGame.items.Item;
import dev.aksarok.rpgGame.tiles.Tile;

public class Box extends StaticEntity {

    //STATS
    private static String name = "Caja";

    public Box(Handler handler, float x, float y) {
        super(handler, name, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 0;
        bounds.y = (int) (height / 2.2f);
        bounds.width = width;
        bounds.height = (int) (height - height / 2.2f);
        isDestructible = true;
        isInteractable = false;
        health = 5;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {
        int[] item = {0, 1};
        deadthDrop(0, 2, item);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.box, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //Descomentar per veura la collision box
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    //GETTERS AND SETTERS
}
