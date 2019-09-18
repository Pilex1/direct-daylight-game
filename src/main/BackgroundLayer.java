package main;

import static main.Main.P;

import java.awt.Color;

import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public class BackgroundLayer extends Layer {

	private Background background;
	
	public BackgroundLayer(ResourceKey key, PVector offset, float depth, Color tint, float scale) {
		super(new PVector(offset.x, offset.y, depth), tint);
		background = new Background(key, scale);
		components.add(background);
	}
	
	public PVector getSize() {
		return new PVector(background.scale * background.image.width, background.scale * background.image.height);
	}

	private class Background implements IGraphicsComponent {

		private float scale;
		private PImage image;

		private Background(ResourceKey key, float scale) {
			this.scale = scale;
			image = ResourceManager.get(key);
		}

		@Override
		public void update(float dt) {
		}

		@Override
		public void draw(PVector offset) {
			// model matrix
			P.scale(scale);
			P.translate(0, 0, 0);

			// view matrix (camera)
			P.translate((int)(offset.x / offset.z), offset.y, 0);

			P.image(image, 0, 0);
		}

	}

}
