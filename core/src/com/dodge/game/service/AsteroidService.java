package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class AsteroidService {
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private ObjectManagerService objectManagerService = new ObjectManagerService();


	public void generateAsteroidEvery10Seconds(Ship playerShip) {

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				Asteroid asteroid = objectManagerService.createAsteroid(playerShip);
				asteroid.setActive(true);
				asteroids.add(asteroid);
			}
		}, 0, 1);
	}
	public void updateAsteroids(float delta, Ship playerShip) {
	    Iterator<Asteroid> iterator = asteroids.iterator();
	    while (iterator.hasNext()) {
	        Asteroid currentAsteroid = iterator.next();

	        // Calculate the movement along x and y axes based on the current angle
	        float deltaX = currentAsteroid.getSpeed() * delta * MathUtils.cosDeg(currentAsteroid.getAngle());
	        float deltaY = -currentAsteroid.getSpeed() * delta * MathUtils.sinDeg(currentAsteroid.getAngle());

	        // Move the asteroid based on calculated values
	        currentAsteroid.getSprite().translate(deltaY, deltaX);

	        // Optionally, you can add logic to check if the asteroid goes off the screen and
	        // handle it accordingly
	        if (currentAsteroid.getSprite().getY() > Gdx.graphics.getHeight() || currentAsteroid.getSprite().getY() < 0
	                || currentAsteroid.getSprite().getX() < 0
	                || currentAsteroid.getSprite().getX() > Gdx.graphics.getWidth()) {
	            iterator.remove(); // Remove the asteroid if it goes off the screen
	        }
	    }
	}

	
	public ArrayList<Asteroid> getAsteroids(){
		return asteroids;
	}
}
