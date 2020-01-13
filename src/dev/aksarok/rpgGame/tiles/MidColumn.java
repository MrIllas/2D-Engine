package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class MidColumn extends Tile{

	public MidColumn(int id) {
		super(Assets.midColumn, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
