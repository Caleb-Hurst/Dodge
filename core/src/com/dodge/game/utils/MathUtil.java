package com.dodge.game.utils;

public class MathUtil {
	
	
	public static float angleBetweenPoints(float x1, float y1, float x2, float y2) {
		float angle = (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
		// Ensure the angle is in the range [0, 360)
		return (angle + 360) % 360;
	}
}
