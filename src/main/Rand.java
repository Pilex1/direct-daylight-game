package main;

import java.util.Random;

public class Rand {

	private static Random r = new Random();

	public static float randFloat(float min, float max) {
		return min + (max - min) * r.nextFloat();
	}
	
	public static float randFloat(float max) {
		return randFloat(0, max);
	}

}
