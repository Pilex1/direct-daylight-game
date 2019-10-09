package entities;

import processing.core.PVector;

public class Bus extends SimpleSpriteEntity {

	private PVector speed;

	public Bus(PVector pos, PVector speed) {
		super(pos, "bus");
		this.speed = speed;
	}

	@Override
	public void update(float dt) {
		this.pos.x += dt * speed.x;
		this.pos.y += dt * speed.y;
	}

}
