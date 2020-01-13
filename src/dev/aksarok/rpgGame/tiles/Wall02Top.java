package dev.aksarok.rpgGame.tiles;

import dev.aksarok.rpgGame.gfx.Assets;

public class Wall02Top extends Tile {

    public Wall02Top(int id) {
        super(Assets.topWall2, id);
        isSolid();
        setBackground(Assets.darkZone);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
