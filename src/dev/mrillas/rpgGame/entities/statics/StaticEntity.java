package dev.mrillas.rpgGame.entities.statics;

import dev.mrillas.rpgGame.Handler;
import dev.mrillas.rpgGame.entities.Entity;

public abstract class StaticEntity extends Entity{

	public StaticEntity(Handler handler, String name, float x, float y, int width, int height) {
		super(handler, name, x, y, width, height);
	}
}
