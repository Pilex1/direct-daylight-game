package entities;

import static main.Main.P;

import main.ResourceManager;
import main.ResourceManager.ResourceKey;
import processing.core.PImage;
import processing.core.PVector;

public abstract class SpriteEntity extends Entity {

	protected PImage image;
	
	public SpriteEntity(PVector pos, ResourceKey key) {
		super(pos);
		image = ResourceManager.get(key);
	}
	
	@Override
	public void drawRelative() {
		P.image(image, 0, 0);
	}
	
}
