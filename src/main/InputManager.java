package main;

import java.util.HashSet;
import java.util.Set;

import processing.event.KeyEvent;

public class InputManager {

	private static Set<Integer> keysPressed;

	public static void init() {
		keysPressed = new HashSet<>();
	}

	public static boolean isKeyPressed(int keyCode) {
		return keysPressed.contains(keyCode);
	}

	static void onKeyPress(KeyEvent event) {
		keysPressed.add(event.getKeyCode());
	}

	static void onKeyRelease(KeyEvent event) {
		keysPressed.remove(event.getKeyCode());
	}

}
