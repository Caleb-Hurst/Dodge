package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class InputHandlerService {

	private SoundManagerService soundManagerService;
	private int a = 0;
	private boolean spaceBarPressedLastFrame = false;

	public InputHandlerService(SoundManagerService soundManagerService) {
		this.soundManagerService = soundManagerService;
	}

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
//		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			System.out.println(rotation);
//			if((rotation <180 && rotation >0) || (rotation > -360 && rotation < -180))
//	        playerShip.rotate(-rotationSpeed * delta);
//			if ( (rotation >=180 && rotation <=360) || (rotation >= -180 && rotation <= 0)) {
//				playerShip.rotate(rotationSpeed * delta);
//			}
//	    }
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
//	    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//	    	System.out.println(rotation);
//	    	if (rotation < 180 && rotation >= 0) {
//	        playerShip.rotate(rotationSpeed * delta);
//	    	}
//	    	if (rotation >180 && rotation <360) {
//	    		playerShip.rotate(-rotationSpeed * delta);
//	    	}
//	    }

	    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        if (!spaceBarPressedLastFrame) {
	            playerLaser.shoot(playerShip);
	            soundManagerService.laser();
	            System.out.println(a++);
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
