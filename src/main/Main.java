package main;

import entities.Character;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

	public static Main P;

	private Character character;

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
		SceneManager.draw(character.getPos());
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

	public Character getCharacter() {
		return character;
	}

}
