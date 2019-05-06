package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class Wall01Tile extends Tile{

	public Wall01Tile(int id) {
		super(Assets.wall01, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
