package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import entities.Character;
import main.Scene.SceneKey;
import processing.core.PApplet;
import processing.core.PImage;
import terrain.Location;

public class Game extends PApplet {

	private static Game canvas;

	private Character character;

	private int prevMillis;

	public static void main(String[] args) {
		try {
			FileHandler handler = new FileHandler("logs/log%g.txt");
			SimpleFormatter formatter = new SimpleFormatter();
			handler.setFormatter(formatter);
			Logger.getGlobal().addHandler(handler);
			Logger.getGlobal().setLevel(Level.FINEST);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PApplet.main("main.Game");
	}

	@Override
	public void settings() {
		size(1280, 720, P3D);
		canvas = this;
	}

	List<SceneKey> scenes;

	@Override
	public void setup() {
		// surface.setResizable(true);

		character = new Character();

		InputManager.init();
		SceneManager.init();

		scenes = new ArrayList<>(SceneManager.getSceneKeys());
	}

	int i = 0;

	@Override
	public void draw() {
//		if (i < scenes.size()) {
//			SceneManager.setActiveScene(Location.left(scenes.get(i)));
//			i++;
//		} else if (i == scenes.size()) {
//			SceneManager.setActiveScene(Location.left(SceneKey.HAYMARKET));
//			i++;
//		}

		long start = System.nanoTime();
		int millis = millis();
		float dt = (millis - prevMillis) / 1000f;
		if (dt > 0.04) {
			System.out.println((long) (dt * 1000));

		}
		if (prevMillis != 0) {
			background(255, 255, 255);
			SceneManager.update(dt);
			SceneManager.draw(getGraphics(), character.getPos());
		}


		prevMillis = millis;

		long diff = (System.nanoTime() - start) / 1000000;
		if (dt > 0.04) {
			System.out.println("draw loop took " + diff + "ms");
		}
		// System.out.println(diff);
		// if (diff > 20) {
		// System.out.println(diff);
		// }
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

	@Override
	public PImage loadImage(String filename) {
		PImage image = super.loadImage(filename);
		
//		 image.loadPixels();
//		// image.updatePixels();
//image.setLoaded(true);
		System.out.println(filename);
		// TODO Auto-generated method stub
		return image;
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
