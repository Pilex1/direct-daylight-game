package main;

import static main.Main.P;

import java.util.HashMap;

import processing.core.PImage;

public class ResourceManager {

	public enum ResourceKey {
		SEVEN_HILLS_BACKGROUND, SEVEN_HILLS_FOREGROUND, BUS, GREAT_WESTERN, TAKEOFF, CITY_LIGHTS, GUY
	}

	private static HashMap<ResourceKey, PImage> resources;

	public static void init() {
		resources = new HashMap<>();

		resources.put(ResourceKey.SEVEN_HILLS_BACKGROUND, P.loadImage("graphics/background5.gif"));
		resources.put(ResourceKey.SEVEN_HILLS_FOREGROUND, P.loadImage("graphics/sevenhillsforeground.gif"));
		resources.put(ResourceKey.BUS, P.loadImage("graphics/bus.png"));
		resources.put(ResourceKey.GREAT_WESTERN, P.loadImage("graphics/great-western.png"));
		resources.put(ResourceKey.TAKEOFF, P.loadImage("graphics/takeoff.png"));
		resources.put(ResourceKey.CITY_LIGHTS, P.loadImage("graphics/city-lights.png"));
		resources.put(ResourceKey.GUY, P.loadImage("graphics/guy.png"));


	}

	public static PImage get(ResourceKey resource) {
		return resources.get(resource);
	}

}
