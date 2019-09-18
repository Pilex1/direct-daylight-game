package entities;

import main.IGraphicsComponent;
import processing.core.PVector;
import static main.Main.P;

public abstract class Entity implements IGraphicsComponent {

	protected PVector pos;

	protected Entity(PVector pos) {
		this.pos = pos;
	}

	@Override
	public abstract void update(float dt);

	@Override
	public final void draw(PVector offset) {
		// model matrix
		P.translate(pos.x / offset.z, pos.y, 0);
		
		// view matrix (camera)
		P.translate(offset.x, offset.y);
		
		drawRelative();
	}

	public abstract void drawRelative();

	public PVector getPos() {
		return pos;
	}

}
