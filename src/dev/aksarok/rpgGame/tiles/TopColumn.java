package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class TopColumn extends Tile{

	public TopColumn(int id) {
		super(Assets.topColumn, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
