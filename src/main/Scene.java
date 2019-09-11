package main;

import java.util.Arrays;
import java.util.List;

public class Scene {

	private List<Layer> layers;

	public Scene(Layer... layers) {
		this.layers = Arrays.asList(layers);
	}

	public void update(float dt) {
		layers.forEach(l -> l.update(dt));
	}

	public void draw(Camera camera) {
		layers.forEach(l -> l.draw(camera));
	}

}
