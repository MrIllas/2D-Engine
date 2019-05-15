package dev.aksarok.rpgGame.tiles;


import dev.aksarok.rpgGame.gfx.Assets;

public class TopColumn extends Tile{

	public TopColumn(int id) {
		super(Assets.topColumn, id);
		isSolid();
                setBackground(Assets.darkZone);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
