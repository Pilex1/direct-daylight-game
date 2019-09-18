package entities;

import main.ResourceManager;
import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public class Vehicle extends SpriteEntity {

	private PVector speed;

	public Vehicle(PVector pos, PVector speed) {
		super(pos, ResourceKey.BUS);
		this.speed = speed;
	}

	@Override
	public void update(float dt) {
		this.pos.x += dt * speed.x;
		this.pos.y += dt * speed.y;
	}

}
