package main;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import entities.Vehicle;
import main.ResourceManager.ResourceKey;
import processing.core.PVector;

public class SceneManager {

	public enum SceneKey {
		Central
	}

	private static Map<SceneKey, Scene> scenes;
	private static Scene activeScene;

	public static void init() {
		scenes = new HashMap<>();

		scenes.put(SceneKey.Central, new Scene(
				new BackgroundLayer(
						ResourceKey.CITY_LIGHTS,
						new PVector(0, -2, Float.POSITIVE_INFINITY),
						Color.WHITE,
						1.5f),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_BACKGROUND,
						new PVector(0, 140, 2f),
						Color.GRAY,
						1),
				new EntityLayer(
						new Entity[] { new Vehicle(ResourceKey.BUS, new PVector(-1000, 700), new PVector(600, 0)) },
						1.5f,
						Color.GRAY),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_FOREGROUND,
						new PVector(0, 140, 1),
						Color.GRAY,
						1)));

		setActiveScene(SceneKey.Central);

	}

	public static void setActiveScene(SceneKey key) {
		activeScene = scenes.get(key);
	}

	public static void update(float dt) {
		if (activeScene != null) {
			activeScene.update(dt);
		}
	}

	public static void draw(Camera camera) {
		if (activeScene != null) {
			activeScene.draw(camera);
		}
	}

}
