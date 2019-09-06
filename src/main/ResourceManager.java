package main;

import static main.Main.P;

import java.util.HashMap;

import processing.core.PImage;

public class ResourceManager {

	public enum ResourceKey {
		Background, Foreground
	}

	private static HashMap<ResourceKey, PImage> resources;

	public static void init() {
		resources = new HashMap<>();

		resources.put(ResourceKey.Background, P.loadImage("graphics/background5.gif"));
		resources.put(ResourceKey.Foreground, P.loadImage("graphics/sevenhillsforeground.gif"));
	}

	public static PImage get(ResourceKey resource) {
		return resources.get(resource);
	}

}
