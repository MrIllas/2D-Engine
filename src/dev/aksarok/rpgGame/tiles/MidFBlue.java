package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class MidFBlue extends Tile{

	public MidFBlue(int id) {
		super(Assets.midFBlue, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
