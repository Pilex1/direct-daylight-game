package main;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import entities.Vehicle;
import main.ResourceManager.ResourceKey;
import processing.core.PVector;
import static main.Main.P;

public class SceneManager {

	public enum SceneKey {
		Central, Parramatta
	}

	private static Map<SceneKey, Scene> scenes;
	private static Scene activeScene;

	public static void init() {
		scenes = new HashMap<>();

		scenes.put(SceneKey.Central, new Scene(
				new BackgroundLayer(
						ResourceKey.CITY_LIGHTS,
						new PVector(0, 0),
						Float.POSITIVE_INFINITY,
						Color.WHITE,
						1.5f),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_BACKGROUND,
						new PVector(0, 140),
						2f,
						Color.GRAY,
						1f),
				new EntityLayer(
						new Entity[] { new Vehicle(new PVector(-1000, 700), new PVector(600, 0)) },
						1.5f,
						Color.GRAY),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_FOREGROUND,
						new PVector(0, 140),
						1f,
						Color.GRAY,
						1f)));
		
		scenes.put(SceneKey.Parramatta, new Scene(
				new BackgroundLayer(
						ResourceKey.GREAT_WESTERN,
						new PVector(0, 0),
						Float.POSITIVE_INFINITY,
						Color.WHITE,
						1.5f),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_BACKGROUND,
						new PVector(0, 140),
						2f,
						Color.GRAY,
						1f),
				new EntityLayer(
						new Entity[] { new Vehicle(new PVector(-1000, 700), new PVector(600, 0)) },
						1.5f,
						Color.GRAY),
				new BackgroundLayer(
						ResourceKey.SEVEN_HILLS_FOREGROUND,
						new PVector(0, 140),
						1f,
						Color.GRAY,
						1f)));

		setActiveScene(SceneKey.Central, new PVector(1000, 0));

	}

	public static void setActiveScene(SceneKey key, PVector initialPos) {
		activeScene = scenes.get(key);
		P.getCharacter().setPos(initialPos);
	}

	public static void update(float dt) {
		if (activeScene != null) {
			activeScene.update(dt);
		}
	}

	public static void draw(PVector characterPos) {
		if (activeScene != null) {
			activeScene.draw(characterPos);
		}
	}

}
