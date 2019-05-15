package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class MidColumnFalse extends Tile{

	public MidColumnFalse(int id) {
		super(Assets.midColumn, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
}
