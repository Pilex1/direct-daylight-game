package entities;

import static main.Main.P;

import main.ResourceManager;
import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public class Vehicle extends Entity {

	private PImage image;
	private PVector speed;

	public Vehicle(ResourceKey resource, PVector pos, PVector speed) {
		super(pos);
		image = ResourceManager.get(resource);
		this.speed = speed;
	}

	@Override
	public void update(float dt) {
		this.pos.x += dt * speed.x;
		this.pos.y += dt * speed.y;
	}

	@Override
	public void draw(PVector offset) {

		// model matrix
		P.translate(pos.x / offset.z, pos.y, 0);

		// view matrix (camera)
		P.translate(offset.x, offset.y);

		P.image(image, 0, 0);
	}

}
