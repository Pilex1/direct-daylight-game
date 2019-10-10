package terrain;

import entities.Entity;
import main.Game;
import main.Scene.SceneKey;
import main.SceneManager;
import processing.core.PGraphics;
import processing.core.PVector;

public class Doorway extends Entity {

	public enum Side {
		Left, Right
	}

	private Location entry;
	private Location exit;
	private Side side;

	private static final float offset = 5f;

	public Doorway(SceneKey entry, SceneKey exit, Side side) {
		super(new PVector());
		this.side = side;
		if (side == Side.Left) {
			this.entry = Location.edgeLeft(entry);
			this.exit = Location.edgeRight(exit, new PVector(offset, 0));
		} else if (side == Side.Right) {
			this.entry = Location.edgeRight(entry);
			this.exit = Location.edgeLeft(exit, new PVector(offset, 0));
		}
	}

	@Override
	public void update(float dt) {
		if (entry.getScene() == SceneManager.getActiveScene()) {
			float x = Game.getCharacter().getPos().x;
			if ((side == Side.Left && x <= entry.getPos().x) || (side == Side.Right && x >= entry.getPos().x)) {
				SceneManager.setActiveScene(exit);
			}
		}
	}

	@Override
	public void drawRelative(PGraphics graphics) {
	}

}
