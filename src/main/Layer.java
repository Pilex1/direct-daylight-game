package main;

import static main.Main.P;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import processing.core.PVector;

public class Layer {

	protected PVector offset;
	protected Color tint;
	protected Set<IGraphicsComponent> components;

	protected Layer(PVector offset, Color tint) {
		components = new HashSet<>();
		this.offset = offset;
		this.tint = tint;
	}

	protected Layer() {
		this(new PVector(0, 0, 1), Color.WHITE);
	}

	public void update(float dt) {
		components.forEach(c -> c.update(dt));
	}

	public void draw(PVector view) {
		P.pushMatrix();
		P.tint(tint.getRGB());

		PVector offset = new PVector();
		offset.x = -view.x + this.offset.x;
		offset.y = -view.y + this.offset.y;
		offset.z = -view.z + this.offset.z;

		components.forEach(c -> c.draw(offset));
		
		P.noTint();
		P.popMatrix();
	}

}
