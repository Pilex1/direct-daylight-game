package entities;

import main.ResourceManager;
import main.ResourceManager.ResourceKey;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public abstract class SimpleSpriteEntity extends Entity {

	protected PImage image;
	
	public SimpleSpriteEntity(PVector pos, String name) {
		super(pos);
		image = ResourceManager.getSprite(name);
	}
	
	@Override
	public void drawRelative(PGraphics graphics) {
		graphics.image(image, 0, 0);
	}
	
}
