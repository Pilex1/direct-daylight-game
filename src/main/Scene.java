package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import processing.core.PGraphics;
import processing.core.PVector;

public class Scene {

	public enum SceneKey {
		CENTRAL, SEVEN_HILLS, HAYMARKET
	}
	
	private SceneKey key;

	private List<Layer> layers;

	/**
	 * the background layer that is closest to the camera i.e. the lowest depth
	 * value
	 */
	private BackgroundLayer foreground;

	/**
	 * this layer contains only the character
	 */
	private CharacterLayer characterLayer;

	public Scene(SceneKey key, List<Layer> layers) {
		this.key = key;
		this.layers = layers;
		for (Layer l : layers) {
			processLayer(l);
		}
		characterLayer = new CharacterLayer();
		processLayer(characterLayer);
		this.layers.sort(new Comparator<Layer>() {

			@Override
			public int compare(Layer l1, Layer l2) {
				return (int) Math.signum(l2.getDepth() - l1.getDepth());
			}
		});
		layers.add(characterLayer);
	}

	private void processLayer(Layer l) {
		if (l instanceof BackgroundLayer) {
			if (foreground == null) {
				foreground = (BackgroundLayer) l;
			} else {
				if (l.offset.z < foreground.offset.z) {
					foreground = (BackgroundLayer) l;
				}
			}
		}
	}

	public void update(float dt) {
		layers.forEach(l -> l.update(dt));
	}

	public void draw(PGraphics graphics, PVector characterPos) {
		// normally, the camera pans with the player, so that the player is always in
		// the middle of the screen
		// however, if the camera is on the edge of the scene we stop panning

		PVector restrictedPos = characterPos.copy();
		restrictedPos.x = Math.max(Game.getWidth() / 2, restrictedPos.x);
		restrictedPos.x = Math.min(foreground.getSize().x - Game.getWidth()/2, restrictedPos.x);
//		System.out.println(foreground.getSize());
//		System.out.println(characterPos.x);

		PVector view = new PVector(restrictedPos.x - Game.getWidth() / 2, restrictedPos.y);

		layers.forEach(l -> l.draw(graphics, view));
	}
	
	public SceneKey getKey() {
		return this.key;
	}
	
	public float getWidth() {
		return foreground.getSize().x;
	}

}
