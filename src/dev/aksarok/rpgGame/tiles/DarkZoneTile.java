package dev.aksarok.rpgGame.tiles;

import dev.aksarok.rpgGame.gfx.Assets;

public class DarkZoneTile extends Tile{

	public DarkZoneTile(int id) {
		super(Assets.darkZone, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
