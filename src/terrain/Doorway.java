package terrain;

import entities.Entity;
import main.Game;
import main.SceneManager;
import processing.core.PGraphics;
import processing.core.PVector;

public class Doorway extends Entity {

	private float range = 100f;
	private Location entry;
	private Location exit;

	public Doorway(Location entry, Location exit) {
		super(entry.getPos());
		this.entry = entry;
		this.exit = exit;
	}

	@Override
	public void update(float dt) {
		if (SceneManager.getActiveScene() == entry.getScene()
				&& PVector.dist(Game.getCharacter().getPos(), entry.getPos()) < range) {
			SceneManager.setActiveScene(exit);
		}
	}

	@Override
	public void drawRelative(PGraphics graphics) {
	}

}
