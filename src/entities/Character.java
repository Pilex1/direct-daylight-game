package entities;

import main.InputManager;
import main.ResourceManager.ResourceKey;
import processing.core.PVector;
import static main.Main.P;

public final class Character extends SpriteEntity {

	private float speed = 200;

	public Character() {
		super(new PVector(), ResourceKey.GUY);
	}

	private void moveLeft(float dt) {
		pos.x -= speed * dt;
	}

	private void moveRight(float dt) {
		pos.x += speed * dt;
	}

	@Override
	public void update(float dt) {
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_D)) {
			moveRight(dt);
		}
		if (InputManager.isKeyPressed(java.awt.event.KeyEvent.VK_A)) {
			moveLeft(dt);
		}
	}

	@Override
	public void drawRelative() {
		// change P.height - 150 to something else
		P.image(image, -image.width/2, P.height-image.height/2 - 100);
	}
	

	public void setPos(PVector pos) {
		this.pos = pos;
	}

}
