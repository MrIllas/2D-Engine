package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class TopFountain extends Tile{

	public TopFountain(int id) {
		super(Assets.topFountain, id);
		isSolid();
                setBackground(Assets.darkZone);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
