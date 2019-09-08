package main;

import static main.Main.P;

import java.awt.Color;

import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public class BackgroundLayer extends Layer {

	public BackgroundLayer(ResourceKey key, PVector offset, Color tint, float scale) {
		super(offset, tint);
		components.add(new Background(key, scale));
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
			P.translate(offset.x / offset.z, offset.y, 0);

			P.image(image, 0, 0);
		}

	}

}
