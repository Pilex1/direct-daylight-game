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

	/**
	 * 
	 * @param scene
	 * @return null if no heights exist for the given scene
	 */
	public static PImage getHeights(SceneKey scene) {
		for (String ext : extensions) {
			String fullFile = "graphics/background/" + scene.toString().toLowerCase().replace("_", "-") + "/heights"
					+ ext;
			if (new File(fullFile).exists()) {
				return Game.getPApplet().loadImage(fullFile);
			}
		}
		return null;
	}

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
		String filename = "graphics/sprite/" + name;
		for (String ext : extensions) {
			String fullFile = filename + ext;
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
