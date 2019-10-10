package main;

import java.awt.Color;

import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class BackgroundLayer extends Layer {

	private Background background;

	public BackgroundLayer(PImage image, float depth, PVector offset, Color tint, float scale) {
		super(new PVector(offset.x, offset.y, depth), tint);

		background = new Background(image, scale);
		components.add(background);
	}

	public PVector getSize() {
		return new PVector(background.getScale() * background.image.width, background.getScale() * background.image.height);
	}


	private class Background implements IGraphicsComponent {

		private float scale;
		private PImage image;

		private Background(PImage image, float scale) {
			this.scale = scale;
			this.image = image;
		}

		private float getScale() {
			float widthAdjust = (float) Game.getWidth() / image.width;
			float heightAdjust = (float) Game.getHeight() / image.height;
			return scale * Math.max(widthAdjust, heightAdjust);
		}
		
		@Override
		public void update(float dt) {
		}
		

		@Override
		public void draw(PGraphics graphics, PVector offset) {
			graphics.translate((int) (offset.x / offset.z), offset.y);
			graphics.scale(getScale());
			graphics.translate(0, 0, 0);
			graphics.image(image, 0, 0);
		}

	}

}
