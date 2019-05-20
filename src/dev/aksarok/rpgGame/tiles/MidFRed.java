package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class MidFRed extends Tile{

	public MidFRed(int id) {
		super(Assets.midFRed, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
