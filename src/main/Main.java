package main;

import java.util.ArrayList;
import java.util.List;

import main.ResourceManager.ResourceKey;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

	public static Main P;

	private List<BackgroundLayer> background;
	private Camera camera;

	private int prevMillis;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	@Override
	public void settings() {
		size(1600, 900, P2D);
	}

	@Override
	public void setup() {
		P = this;
		ResourceManager.init();
		InputManager.init();

		background = new ArrayList<>();
		background.add(new BackgroundLayer(ResourceKey.Background, new PVector(0, 0), 1));
		background.add(new BackgroundLayer(ResourceKey.Foreground, new PVector(0, 0), 2));
		camera = new Camera(new PVector(0, 0));
	}

	private void handleInputs(float dt) {
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
			camera.moveRight(dt);
		}
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
			camera.moveLeft(dt);
		}
	}

	private void update() {

	}

	@Override
	public void draw() {
		int millis = millis();
		float dt = (millis - prevMillis) / 1000f;
		prevMillis = millis;

		handleInputs(dt);
		update();

		background(255, 255, 255);
		for (BackgroundLayer b : background) {
			b.draw(camera);
		}

		System.out.println(frameRate);
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

	}

}
