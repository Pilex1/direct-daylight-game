package entities;

import main.IGraphicsComponent;
import processing.core.PVector;

public abstract class Entity implements IGraphicsComponent {

	protected PVector pos;

	protected Entity(PVector pos) {
		this.pos = pos;
	}

	@Override
	public abstract void update(float dt);

	@Override
	public abstract void draw(PVector offset);

}
