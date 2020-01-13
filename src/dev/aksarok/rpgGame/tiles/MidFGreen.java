package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class MidFGreen extends Tile{

	public MidFGreen(int id) {
		super(Assets.midFGreen, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
