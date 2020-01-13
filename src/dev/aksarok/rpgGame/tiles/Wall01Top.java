package dev.aksarok.rpgGame.tiles;

import dev.aksarok.rpgGame.gfx.Assets;

public class Wall01Top extends Tile {

    public Wall01Top(int id) {
        super(Assets.topWall1, id);
        isSolid();
        setBackground(Assets.darkZone);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
