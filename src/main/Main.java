package main;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

	public static Main P;

	private Camera camera;

	private int prevMillis;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	@Override
	public void settings() {
		size(1600, 900, P3D);
	}

	@Override
	public void setup() {
		P = this;
		ResourceManager.init();
		InputManager.init();
		SceneManager.init();

		camera = new Camera(new PVector(1500, 0));
	}

	private void handleInputs(float dt) {
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
			camera.moveRight(dt);
		}
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
			camera.moveLeft(dt);
		}
	}

	@Override
	public void draw() {
		int millis = millis();
		float dt = (millis - prevMillis) / 1000f;
		prevMillis = millis;

		handleInputs(dt);

		background(255, 255, 255);
		SceneManager.update(dt);
		SceneManager.draw(camera);

	}

	@Override
	public void keyPressed(processing.event.KeyEvent event) {
		InputManager.onKeyPress(event);
	}

	@Override
	public void keyReleased(processing.event.KeyEvent event) {
		InputManager.onKeyRelease(event);
	}

	@Override
	public void dispose() {
		// do not get rid of this line otherwise the window won't close!
		super.dispose();
	}

}
