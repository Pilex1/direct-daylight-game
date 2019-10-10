package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Bus;
import entities.Entity;
import main.Scene.SceneKey;
import processing.core.PGraphics;
import processing.core.PVector;
import terrain.Doorway;
import terrain.Doorway.Side;
import terrain.Location;

public class SceneManager {

	public static class SceneBuilder {

		private SceneKey scene;
		private List<Layer> layers;

		private BackgroundLayerBuilder background;
		private EntityLayerBuilder entityLayer;

		public SceneBuilder(SceneKey scene) {
			this.scene = scene;
			layers = new ArrayList<>();
		}

		public Scene build() {
			addOldLayers();
			return new Scene(scene, layers);
		}

		private void addOldLayers() {
			if (background != null) {
				BackgroundLayer layer = new BackgroundLayer(ResourceManager.getBackground(scene, background.file),
						background.depth, background.offset, background.tint, background.scale);
				layers.add(layer);
			}
			if (entityLayer != null) {
				EntityLayer layer = new EntityLayer(entityLayer.entities.toArray(new Entity[0]), entityLayer.depth,
						entityLayer.tint);
				layers.add(layer);
			}
		}

		public BackgroundLayerBuilder background(String file, float depth) {
			addOldLayers();
			background = null;
			entityLayer = null;

			background = new BackgroundLayerBuilder(file, depth);
			return background;
		}

		public EntityLayerBuilder entities() {
			addOldLayers();
			background = null;
			entityLayer = null;

			entityLayer = new EntityLayerBuilder();
			return entityLayer;
		}

		public class EntityLayerBuilder {

			private float depth = 1;
			private Color tint = Color.WHITE;

			private List<Entity> entities = new ArrayList<>();

			public EntityLayerBuilder() {
			}

			public BackgroundLayerBuilder background(String file, float depth) {
				return SceneBuilder.this.background(file, depth);
			}

			public EntityLayerBuilder entities() {
				return SceneBuilder.this.entities();
			}

			public Scene build() {
				return SceneBuilder.this.build();
			}

			public EntityLayerBuilder entity(Entity entity) {
				entities.add(entity);
				return this;
			}

			public EntityLayerBuilder depth(float depth) {
				this.depth = depth;
				return this;
			}

			public EntityLayerBuilder tint(Color tint) {
				this.tint = tint;
				return this;
			}

			public EntityLayerBuilder doorway(Doorway.Side side, SceneKey newScene) {
				Doorway doorway = new Doorway(scene, newScene, side);
				return entity(doorway);
			}

		}

		public class BackgroundLayerBuilder {

			private String file;
			private float depth;
			private PVector offset = new PVector();
			private Color tint = Color.WHITE;
			private float scale = 1;

			public BackgroundLayerBuilder(String file, float depth) {
				this.file = file;
				this.depth = depth;
			}

			public BackgroundLayerBuilder background(String file, float depth) {
				return SceneBuilder.this.background(file, depth);
			}

			public EntityLayerBuilder entities() {
				return SceneBuilder.this.entities();
			}

			public Scene build() {
				return SceneBuilder.this.build();
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

		addScene(new SceneBuilder(SceneKey.SEVEN_HILLS)
				.background("city-lights", Float.POSITIVE_INFINITY).tint(Color.GRAY)
				.background("background", 2f).tint(Color.GRAY)
				.background("foreground", 1f).tint(Color.GRAY)
				.entities().depth(1.5f).tint(Color.GRAY).entity(new Bus(new PVector(0, 530), new PVector(200, 0)))
				.entities().doorway(Side.Left, SceneKey.HAYMARKET)
				.build());

		addScene(new SceneBuilder(SceneKey.HAYMARKET)
				.background("sky", Float.POSITIVE_INFINITY)
				.background("background2", 1.75f)
				.background("background1", 1.5f)
				.background("street", 1f)
				.entities().doorway(Side.Right, SceneKey.SEVEN_HILLS).doorway(Side.Left, SceneKey.CENTRAL)
				.build());

		addScene(new SceneBuilder(SceneKey.CENTRAL)
				.background("city-lights", Float.POSITIVE_INFINITY)
				.background("arches", 1.4f)
				.background("lights", 1.2f)
				.background("ground", 1.2f)
				.background("furniture", 1.1f)
				.background("terminal-board", 1f)
				.entities().doorway(Side.Right, SceneKey.HAYMARKET)
				.build());

		setActiveScene(Location.left(SceneKey.HAYMARKET));

	}

	public static void setActiveScene(Location loc) {
		activeScene = scenes.get(loc.getScene());
		activeSceneKey = loc.getScene();
		PVector absolutePos = loc.getPos().copy();
		if (absolutePos.x < 0) {
			absolutePos.x = activeScene.getWidth() - absolutePos.x;
		}
		Game.getCharacter().setPos(absolutePos);
	}

	public static SceneKey getActiveScene() {
		return activeSceneKey;
	}

	public static void update(float dt) {
		long start = System.nanoTime();
		if (activeScene != null) {
			activeScene.update(dt);
		}
		long diff = System.nanoTime() - start;
		Logger.getGlobal().log(Level.FINE, +diff / 1000 + "ms");
		if (diff / 1000 > 1000) {
//			System.out.println(diff / 1000);
		}
	}

	public static void draw(PGraphics graphics, PVector characterPos) {
		long start = System.nanoTime();
		if (activeScene != null) {
			activeScene.draw(graphics, characterPos);
		}
		long diff = System.nanoTime() - start;
		Logger.getGlobal().log(Level.FINE, diff / 1000 + "ms");
		if (diff / 1000 > 1000) {
//			System.out.println(diff / 1000);
		}
	}

	public static Scene getScene(SceneKey key) {
		return scenes.get(key);
	}
	
	public static Set<SceneKey> getSceneKeys() {
		return scenes.keySet();
	}

}
