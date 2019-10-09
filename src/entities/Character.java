package entities;

import java.awt.event.KeyEvent;

import main.Game;
import main.InputManager;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public final class Character extends AnimatedSpriteEntity<Movement4> {

	private float speed = 200;

	public Character() {
		super(new PVector(), Movement4.RIGHT_STILL, "character");
	}

	private void moveLeft(float dt) {
		pos.x -= speed * dt;
	}

	private void moveRight(float dt) {
		pos.x += speed * dt;
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		if (getAnimationState() == Movement4.LEFT) {
			setAnimationState(Movement4.LEFT_STILL);
		}
		if (getAnimationState() == Movement4.RIGHT) {
			setAnimationState(Movement4.RIGHT_STILL);
		}
		
		
		if (InputManager.isKeyPressed(KeyEvent.VK_D)) {
			moveRight(dt);
			setAnimationState(Movement4.RIGHT);
		}
		if (InputManager.isKeyPressed(KeyEvent.VK_A)) {
			moveLeft(dt);
			setAnimationState(Movement4.LEFT);
		}
		
	}

	@Override
	public void drawRelative(PGraphics graphics) {
		// change P.height - 150 to something else
		PImage image = getCurrentSprite();
		graphics.image(image, -image.width/2, Game.getHeight()-image.height/2 - 100);
	}
	

	public void setPos(PVector pos) {
		this.pos = pos;
	}

}
