package dev.mrillas.rpgGame.tiles;

import dev.mrillas.rpgGame.gfx.Assets;

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
