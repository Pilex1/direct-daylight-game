package main;

import entities.Character;
import processing.core.PApplet;

public class Canvas extends PApplet {

	private static Canvas canvas;
	
	private Character character;

	private int prevMillis;

	public static void main(String[] args) {
		PApplet.main("main.Canvas");
	}

	@Override
	public void settings() {
		size(1280, 720, P3D);
		canvas = this;
	}

	@Override
	public void setup() {
		// surface.setResizable(true);

		ResourceManager.init();
		character = new Character();

		InputManager.init();
		SceneManager.init();

	}

	@Override
	public void draw() {
		int millis = millis();
		float dt = (millis - prevMillis) / 1000f;
		prevMillis = millis;

		background(255, 255, 255);
		SceneManager.update(dt);
		SceneManager.draw(getGraphics(), character.getPos());
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

	public static Character getCharacter() {
		return canvas.character;
	}
	
	public static int getWidth() {
		return canvas.width;
	}
	
	public static int getHeight() {
		return canvas.height;
	}
	
	public static PApplet getPApplet() {
		return canvas;
	}

}
