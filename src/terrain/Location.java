package terrain;

import main.Game;
import main.Scene.SceneKey;
import main.SceneManager;
import processing.core.PVector;

public class Location {
	
	private enum Relative{
		/**
		 * x = 0 is on the very left of the scene
		 */
		EdgeLeft, 
		
		/**
		 * x = 0 is the left-most point you can go whilst still remaining in the center
		 */
		Left, 
		
		/**
		 * always in the center of the screen regardless of what x is
		 */
		Center, 
		
		Right, 
		
		EdgeRight
	}

	private Relative relative;

	private SceneKey scene;
	private PVector pos;
	
	private Location(SceneKey scene, PVector pos, Relative relative) {
		this.relative = relative;
		this.scene = scene;
		this.pos = pos;
	}
	
	public static Location edgeLeft(SceneKey scene) {
		return new Location(scene, new PVector(), Relative.EdgeLeft);
	}
	
	public static Location edgeLeft(SceneKey scene, PVector pos) {
		return new Location(scene, pos, Relative.EdgeLeft);
	}
	
	public static Location edgeRight(SceneKey scene) {
		return new Location(scene, new PVector(), Relative.EdgeRight);
	}
	
	public static Location edgeRight(SceneKey scene, PVector pos) {
		return new Location(scene, pos, Relative.EdgeRight);
	}
	
	public static Location left(SceneKey scene) {
		return new Location(scene, new PVector(), Relative.Left);
	}
	
	public static Location left(SceneKey scene, PVector pos) {
		return new Location(scene, pos, Relative.Left);
	}
	
	public static Location right(SceneKey scene) {
		return new Location(scene, new PVector(), Relative.Right);
	}
	
	public static Location right(SceneKey scene, PVector pos) {
		return new Location(scene, pos, Relative.Right);
	}
	
	public static Location center(SceneKey scene) {
		return new Location(scene, new PVector(), Relative.Center);
	}

	public SceneKey getScene() {
		return scene;
	}

	public void setScene(SceneKey scene) {
		this.scene = scene;
	}

	public PVector getPos() {
		float width = SceneManager.getScene(scene).getWidth();
		switch (relative) {
		case EdgeLeft:
			return new PVector(pos.x, pos.y);
		case Left:
			return new PVector(pos.x+Game.getWidth()/2, pos.y);
		case Center:
			return new PVector(width/2, pos.y);
		case Right:
			return new PVector(width-Game.getWidth()/2-pos.x, pos.y);
		case EdgeRight:
			return new PVector(width-pos.x, pos.y);
		}
		throw new RuntimeException();
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

}
