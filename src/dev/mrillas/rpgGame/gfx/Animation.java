package dev.mrillas.rpgGame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private boolean stand;
	
	public Animation(int speed, BufferedImage[] frames, boolean stand) {
		this.speed = speed;
		this.frames = frames;
		this.stand = stand;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(stand == true) {
				if(index >= (frames.length - 1)) {
					index = 0;
				}
			}else {
				if(index >= frames.length) {
					index = 0;
				}
			}
			
		}
	}
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public BufferedImage getSpecificFrame(int i) {
		return frames[i];
	}
}
