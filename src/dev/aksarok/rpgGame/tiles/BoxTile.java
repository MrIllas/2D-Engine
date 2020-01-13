package dev.aksarok.rpgGame.tiles;

import dev.aksarok.rpgGame.gfx.Assets;

public class BoxTile extends Tile{

	public BoxTile(int id) {
		super(Assets.box, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
