package dev.aksarok.rpgGame.tiles;

import dev.aksarok.rpgGame.gfx.Assets;

public class Wall03Top extends Tile {

    public Wall03Top(int id) {
        super(Assets.topWall3, id);
        isSolid();
        setBackground(Assets.darkZone);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
