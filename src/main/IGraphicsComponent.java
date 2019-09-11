package main;

import processing.core.PVector;

public interface IGraphicsComponent {

	public void update(float dt);

	public void draw(PVector offset);

}
