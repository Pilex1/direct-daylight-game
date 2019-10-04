package terrain;

import main.Scene.SceneKey;
import processing.core.PVector;

public class Location {

	private SceneKey scene;
	private PVector pos;

	public Location(SceneKey scene, PVector pos) {
		super();
		this.scene = scene;
		this.pos = pos;
	}

	public SceneKey getScene() {
		return scene;
	}

	public void setScene(SceneKey scene) {
		this.scene = scene;
	}

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

}
