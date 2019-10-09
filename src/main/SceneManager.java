package main;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import entities.Entity;
import entities.Bus;
import main.ResourceManager.ResourceKey;
import main.Scene.SceneKey;
import main.SceneManager.SceneBuilder.BackgroundLayerBuilder;
import processing.core.PGraphics;
import processing.core.PVector;
import terrain.Doorway;
import terrain.Location;

public class SceneManager {

	public static class SceneBuilder {

		private SceneKey scene;
		private List<Layer> layers;

		private BackgroundLayerBuilder background;

		public SceneBuilder(SceneKey scene) {
			this.scene = scene;
			layers = new ArrayList<>();
		}

		public Scene build() {
			return new Scene(scene, layers);
		}

		public BackgroundLayerBuilder newBackgroundLayer(String file, float depth) {
			background = new BackgroundLayerBuilder(file, depth);
			return background;
		}

		public SceneBuilder addEntityLayer(Entity[] entities, float depth, Color color) {
			layers.add(new EntityLayer(entities, depth, color));
			return this;
		}

		public class BackgroundLayerBuilder {

			private String[] extensions = new String[] { ".png", ".jpg", ".gif" };

			private String file;
			private float depth;
			private PVector offset = new PVector();
			private Color tint = Color.WHITE;
			private float scale = 1;

			public BackgroundLayerBuilder(String file, float depth) {
				this.file = file;
				this.depth = depth;
			}

			public SceneBuilder createBackground() {
				layers.add(new BackgroundLayer(ResourceManager.getBackground(scene, file), depth, offset, tint, scale));
				return SceneBuilder.this;
			}

			public BackgroundLayerBuilder offset(PVector offset) {
				this.offset = offset;
				return this;
			}

			public BackgroundLayerBuilder tint(Color tint) {
				this.tint = tint;
				return this;
			}

			public BackgroundLayerBuilder scale(float scale) {
				this.scale = scale;
				return this;
			}

		}

	}

	private static Map<SceneKey, Scene> scenes;
	private static Scene activeScene;
	private static SceneKey activeSceneKey;

	private static void addScene(Scene s) {
		scenes.put(s.getKey(), s);
	}

	public static void init() {
		scenes = new HashMap<>();

		addScene(new SceneBuilder(SceneKey.SEVEN_HILLS).newBackgroundLayer("city-lights", Float.POSITIVE_INFINITY)
				.tint(Color.GRAY).createBackground().newBackgroundLayer("background", 2f).tint(Color.GRAY)
				.createBackground().newBackgroundLayer("foreground", 1f).tint(Color.GRAY).createBackground()
				.addEntityLayer(new Entity[] { new Bus(new PVector(0, 530), new PVector(200, 0)) }, 1.5f,
						Color.GRAY)
				.build());

		// scenes.put(SceneKey.SevenHills,
		// new Scene(new BackgroundLayerBuilder(ResourceKey.CITY_LIGHTS,
		// Float.POSITIVE_INFINITY).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.SEVEN_HILLS_BACKGROUND,
		// 2).tint(Color.GRAY).build(),
		//
		// new EntityLayer(new Entity[] { new Vehicle(new PVector(0, 530), new
		// PVector(200, 0)) }, 1.5f,
		// Color.GRAY),
		//
		// new BackgroundLayerBuilder(ResourceKey.SEVEN_HILLS_FOREGROUND,
		// 1).tint(Color.GRAY).build()));
		//
		// scenes.put(SceneKey.CENTRAL, new Scene(
		//
		// new BackgroundLayerBuilder(ResourceKey.CITY_LIGHTS,
		// Float.POSITIVE_INFINITY).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.CENTRAL_INSIDE_ARCHES, 1.4f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.CENTRAL_INSIDE_LIGHTS, 1.2f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.CENTRAL_INSIDE_GROUND, 1.2f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.CENTRAL_INSIDE_FURNITURE,
		// 1.1f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.CENTRAL_INSIDE_TERMINAL, 1f).build()
		//
		// ));
		//
		// scenes.put(SceneKey.Haymarket,
		// new Scene(new BackgroundLayerBuilder(ResourceKey.HAYMARKET_SKY,
		// Float.POSITIVE_INFINITY).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.HAYMARKET_BACKGROUND1, 1.5f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.HAYMARKET_BACKGROUND2, 1.75f).build(),
		//
		// new BackgroundLayerBuilder(ResourceKey.HAYMARKET_STREET, 1f).build(),
		//
		// new EntityLayer(new Entity[] { new Doorway(new Location(SceneKey.Haymarket,
		// new PVector(0, 0)),
		// new Location(SceneKey.CENTRAL, new PVector(0, 0))) })));

		setActiveScene(new Location(SceneKey.SEVEN_HILLS, new PVector(Game.getWidth() / 2, 0)));

	}

	public static void setActiveScene(Location loc) {
		activeScene = scenes.get(loc.getScene());
		activeSceneKey = loc.getScene();
		Game.getCharacter().setPos(loc.getPos());
	}

	public static SceneKey getActiveScene() {
		return activeSceneKey;
	}

	public static void update(float dt) {
		if (activeScene != null) {
			activeScene.update(dt);
		}
	}

	public static void draw(PGraphics graphics, PVector characterPos) {
		if (activeScene != null) {
			activeScene.draw(graphics, characterPos);
		}
	}

}
