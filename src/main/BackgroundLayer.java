package main;

import static main.Main.P;

import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public class BackgroundLayer implements GraphicsElement {

	private PImage image;
	private float parallaxFactor;

	public BackgroundLayer(ResourceKey key, PVector offset, float parallaxFactor) {
		image = ResourceManager.get(key);
		this.parallaxFactor = parallaxFactor;
	}

	@Override
	public void draw(Camera camera) {
		P.pushMatrix();
		P.translate(-camera.getPos().x * parallaxFactor, -camera.getPos().y);
		P.image(image, 0, 0);
		P.popMatrix();
	}

}
