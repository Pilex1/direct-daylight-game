package entities;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import main.ResourceManager;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class AnimatedSpriteEntity<T extends Enum<T>> extends Entity {
	
	private Map<T, AnimationCycle> animations;
	private T currentState;
	
	public AnimatedSpriteEntity(PVector pos, T initialState, String name) {
		super(pos);
		animations = ResourceManager.<T>getAnimation(initialState.getDeclaringClass(), name);
		currentState = initialState;
	}
	
	public PImage getCurrentSprite() {
		return animations.get(currentState).getCurrentSprite();
	}
	
	public T getAnimationState() {
		return currentState;
	}
	
	public void setAnimationState(T state) {
		currentState = state;
	}
	
	@Override
	public void update(float dt) {
		animations.get(currentState).update();
	}

	@Override
	public void drawRelative(PGraphics graphics) {
		
	}
	

}
