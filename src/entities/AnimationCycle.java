package entities;

import processing.core.PImage;

public class AnimationCycle {
	
	private PImage[] sprites;
	private int index = 0;
	// number of frames between each sprite
	private float delay = 20;
	
	private float cooldown;

	public AnimationCycle(PImage[] sprites) {
		this.sprites = sprites;
		cooldown = delay;
	}
	
	public void update() {
		cooldown--;
		if (cooldown == 0) {
			index = (index+1)%sprites.length;
			cooldown = delay;
		}
	}
	
	public PImage getCurrentSprite() {
		return sprites[index];
	}
	
	public int getNumSprites() {
		return sprites.length;
	}
	
}
