package main;

import processing.core.PGraphics;
import processing.core.PVector;

public interface IGraphicsComponent {

	public void update(float dt);

	public void draw(PGraphics graphics, PVector offset);

}
