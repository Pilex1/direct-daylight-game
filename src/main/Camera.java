package main;

import processing.core.PVector;

public class Camera {

	private PVector pos;

	private float speed = 100;

	public Camera(PVector pos) {
		this.pos = pos;
	}

	public PVector getPos() {
		return pos;
	}

	public void moveLeft(float dt) {
		pos.x -= speed * dt;
	}

	public void moveRight(float dt) {
		pos.x += speed * dt;
	}

}
