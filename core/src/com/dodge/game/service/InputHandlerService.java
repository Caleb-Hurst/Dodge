package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class InputHandlerService {

	private LaserService laserService = new LaserService();
	private boolean spaceBarPressedLastFrame = false;

	public void handleInput(float delta, Ship playerShip, Laser playerLaser) {
		float speed = 250 * delta; // Adjust the speed
		float rotationSpeed = 500;
		float rotation = (playerShip.getRotation() + 360) % 360;
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			playerShip.move(0, speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			playerShip.move(0, -speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			playerShip.move(-speed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			playerShip.move(speed, 0);
		}

	    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
	    	System.out.println(rotation);
	        playerShip.rotate(rotationSpeed * delta);
//	        playerLaser.rotate(rotationSpeed * delta);
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
	    	System.out.println(rotation);
	        playerShip.rotate(-rotationSpeed * delta);
//	        playerLaser.rotate(rotationSpeed * delta);
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        if (!spaceBarPressedLastFrame) {
	            laserService.shoot(playerShip,playerLaser);           
	        }
	        spaceBarPressedLastFrame = true;
	    } else {
	        spaceBarPressedLastFrame = false;
//	        playerLaser.deactivate();
	    }
	    
	}
//	private void handleInput() {
//		boolean spaceBarPressed = false;
//    	boolean isShooting = false;
//    	Laser laser = new Laser();
//		 // Check for space bar input
//	    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//	    	
//	    }
//	        if (!spaceBarPressed) {
//	            spaceBarPressed = true;
//	            isShooting = true;
//	            laser.x = ship.x + ship.width / 2 - laser.width / 2; 
//	            laser.y = ship.y + ship.height; // Set the laser starting position just above the ship
//	            dodgeSoundShoot.play(LASER_SOUND_VOLUME);
//	        }
//	    } else {
//	        spaceBarPressed = false;
//	    }
//
//	    // Reset shooting flag when space bar is not pressed
//	    if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//	        isShooting = false;
//	    }

}
