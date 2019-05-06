package dev.mrillas.rpgGame.tiles;


import dev.mrillas.rpgGame.gfx.Assets;

public class Wall02Tile extends Tile{

	public Wall02Tile(int id) {
		super(Assets.wall02, id);
		isSolid();
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
