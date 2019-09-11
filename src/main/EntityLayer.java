package main;

import java.awt.Color;
import java.util.stream.Stream;

import entities.Entity;
import processing.core.PVector;

public class EntityLayer extends Layer {

	public EntityLayer(Entity entities[], float depth, Color tint) {
		super(new PVector(0, 0, depth), tint);
		Stream.of(entities).forEach(e -> components.add(e));
	}

	public EntityLayer(Entity... entities) {
		this(entities, 1, Color.WHITE);
	}

	public void addEntity(Entity e) {
		components.add(e);
	}

}
