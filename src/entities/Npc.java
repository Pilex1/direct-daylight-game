package entities;

import main.Rand;
import main.ResourceManager.ResourceKey;
import processing.core.PVector;

public class Npc extends SimpleSpriteEntity {
	
	protected float wanderRadius = 100;
	protected PVector centralPivot;
	protected float wanderSpeed = 1;
	
	private int wanderCooldown;
	private int wanderCooldownMin = 5 * 60;
	private int wanderCooldownMax = 15 * 60;
	private PVector wanderDestination;

	public Npc(PVector pos, String name) {
		super(pos, name);
		centralPivot = pos.copy();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float dt) {
		// when wanderCooldown reaches 0, this triggers the NPC to wander to a random location
		if (wanderCooldown == 0) {
			if (wanderDestination == null) {
				wanderDestination = new PVector(centralPivot.x + Rand.randFloat(wanderRadius), centralPivot.y);
			} else {
				if (wanderDestination.x > pos.x) {
					pos.x += wanderSpeed;
					pos.x = Math.min(pos.x, wanderDestination.x);
				} else if (wanderDestination.x < pos.x) {
					pos.x -= wanderSpeed;
					pos.x = Math.max(pos.x, wanderDestination.x);
				} else {
					// when we reach the destination, reset the wander cooldown timer
					
				}
			}
		} else {
			wanderCooldown--;
		}
	}

}
