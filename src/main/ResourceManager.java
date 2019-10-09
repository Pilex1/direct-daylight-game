package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.AnimationCycle;
import main.Scene.SceneKey;
import processing.core.PImage;

public class ResourceManager {
	private static final String[] extensions = new String[] { ".png", ".jpg", ".gif" };

	public enum ResourceKey {
		SEVEN_HILLS_BACKGROUND, SEVEN_HILLS_FOREGROUND, BUS, GREAT_WESTERN, TAKEOFF, CITY_LIGHTS, GUY, CENTRAL_1, CENTRAL_FRAME, CENTRAL_LIGHTS, CENTRAL_GROUND, CENTRAL_INSIDE_ARCHES, CENTRAL_INSIDE_GROUND, CENTRAL_INSIDE_FURNITURE, CENTRAL_INSIDE_LIGHTS, CENTRAL_INSIDE_TERMINAL, HAYMARKET_BACKGROUND1, HAYMARKET_BACKGROUND2, HAYMARKET_SKY, HAYMARKET_STREET
	}

	private static HashMap<ResourceKey, PImage> resources;

	public static void init() {
		resources = new HashMap<>();

//		load(ResourceKey.SEVEN_HILLS_BACKGROUND, "graphics/background5.gif");
//		load(ResourceKey.SEVEN_HILLS_FOREGROUND, "graphics/sevenhillsforeground.gif");
//		load(ResourceKey.BUS, "graphics/bus.png");
//		load(ResourceKey.GREAT_WESTERN, "graphics/great-western.png");
//		load(ResourceKey.TAKEOFF, "graphics/takeoff.png");
//		load(ResourceKey.CITY_LIGHTS, "graphics/city-lights.png");
//		load(ResourceKey.GUY, "graphics/guy.png");
//		load(ResourceKey.CENTRAL_GROUND, "graphics/ground.png");
//		load(ResourceKey.CENTRAL_1, "graphics/central-bg1.png");
//		load(ResourceKey.CENTRAL_LIGHTS, "graphics/central-lights.png");
//		load(ResourceKey.CENTRAL_FRAME, "graphics/central-frame.png");
//
//		load(ResourceKey.CENTRAL_INSIDE_ARCHES, "graphics/central inside/arches.png");
//		load(ResourceKey.CENTRAL_INSIDE_GROUND, "graphics/central inside/ground.png");
//		load(ResourceKey.CENTRAL_INSIDE_FURNITURE, "graphics/central inside/furniture.png");
//		load(ResourceKey.CENTRAL_INSIDE_LIGHTS, "graphics/central inside/lights.png");
//		load(ResourceKey.CENTRAL_INSIDE_TERMINAL, "graphics/central inside/terminalboard.png");
//
//		load(ResourceKey.HAYMARKET_BACKGROUND1, "graphics/Haymarket/Background1.png");
//		load(ResourceKey.HAYMARKET_BACKGROUND2, "graphics/Haymarket/Background2.png");
//		load(ResourceKey.HAYMARKET_SKY, "graphics/Haymarket/sky.png");
//		load(ResourceKey.HAYMARKET_STREET, "graphics/Haymarket/Street.png");

	}

//	private static void load(ResourceKey key, String filename) {
//		resources.put(key, Canvas.getPApplet().loadImage(filename));
//	}
//
//	public static PImage get(ResourceKey resource) {
//		return resources.get(resource);
//	}

	public static PImage getBackground(SceneKey scene, String file) {
		for (String ext : extensions) {
			String fullFile = "graphics/background/" + scene.toString().toLowerCase().replace("_", "-") + "/" + file
					+ ext;
			if (new File(fullFile).exists()) {
				return Game.getPApplet().loadImage(fullFile);

			}
		}
		throw new RuntimeException("file not found: " + file);
	}

	public static PImage getSprite(String name) {
		String filename = "graphics/sprite/"+name;
		for (String ext:extensions) {
			String fullFile = filename+ext;
			if (new File(fullFile).exists()) {
				return Game.getPApplet().loadImage(fullFile);
			}
		}
		throw new RuntimeException("file not found for sprite " + name);
	}

	public static <K extends Enum<K>> Map<K, AnimationCycle> getAnimation(Class<K> enumClass, String name) {
		Map<K, AnimationCycle> map = new HashMap<>();
		String rootFolder = "graphics/sprite/" + name;
		for (K k : enumClass.getEnumConstants()) {
			String folder = rootFolder + "/" + k.name().toLowerCase().replace("_", "-");
			AnimationCycle anim = getAnimationCycle(folder);
			if (anim.getNumSprites() == 0) {
				throw new RuntimeException("no sprites in " + folder);
			}
			map.put(k, anim);
		}
		return map;
	}

	private static AnimationCycle getAnimationCycle(String folder) {
		int i = 0;
		List<PImage> images = new ArrayList<>();
		while (true) {
			boolean fileFound = false;
			for (String ext : extensions) {
				String filename = folder + "/" + i + ext;
				if (new File(filename).exists()) {
					images.add(Game.getPApplet().loadImage(filename));
					fileFound = true;
				}
			}
			if (!fileFound) {
				break;
			}
			i++;
		}
		return new AnimationCycle(images.toArray(new PImage[0]));

	}

}
