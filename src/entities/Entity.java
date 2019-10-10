package entities;

import main.IGraphicsComponent;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Entity implements IGraphicsComponent {

	protected PVector pos;

	protected Entity(PVector pos) {
		this.pos = pos;
	}

	@Override
	public abstract void update(float dt);

	@Override
	public final void draw(PGraphics graphics, PVector offset) {
		// model matrix
		graphics.translate(pos.x / offset.z, pos.y);
		
		// view matrix (camera)
		graphics.translate(offset.x, offset.y);
		
		drawRelative(graphics);
	}

	public abstract void drawRelative(PGraphics graphics);

	public PVector getPos() {
		return pos;
	}

}
