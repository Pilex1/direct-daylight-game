package main;

import static main.Main.P;

import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class Scene {

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

	public Scene(Layer... layers) {
		this.layers = new ArrayList<Layer>();
		for (Layer l : layers) {
			addLayer(l);
		}
		characterLayer = new CharacterLayer();
		addLayer(characterLayer);
	}

	private void addLayer(Layer l) {
		if (l instanceof BackgroundLayer) {
			if (foreground == null) {
				foreground = (BackgroundLayer) l;
			} else {
				if (l.offset.z < foreground.offset.z) {
					foreground = (BackgroundLayer) l;
				}
			}
		}
		layers.add(l);
	}

	public void update(float dt) {
		layers.forEach(l -> l.update(dt));
	}

	public void draw(PVector characterPos) {
		// normally, the camera pans with the player, so that the player is always in
		// the middle of the screen
		// however, if the camera is on the edge of the scene we stop panning

		PVector view;

		if (characterPos.x < P.width/2) {
			view = new PVector(0, characterPos.y);
		} else if (characterPos.x > foreground.getSize().x - P.width/2) {
			view = new PVector(foreground.getSize().x - P.width, characterPos.y);
		} else {
			view = new PVector(characterPos.x - P.width/2, characterPos.y);
		}

		layers.forEach(l -> l.draw(view));
	}

}
