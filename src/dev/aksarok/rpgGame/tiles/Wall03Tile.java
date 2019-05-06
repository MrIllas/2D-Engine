package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class Wall03Tile extends Tile{

	public Wall03Tile(int id) {
		super(Assets.wall03, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
