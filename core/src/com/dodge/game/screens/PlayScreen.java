package com.dodge.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dodge.game.main.DodgeGame;

public class PlayScreen implements Screen{

	public PlayScreen(DodgeGame game) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
//		
//		ship = dodgeService.generateGameObject("ship",90,90,800 / 2 - 90 / 2,20);
//		laser = dodgeService.generateGameObject("laser", 35, 10, ship.x + ship.width / 2 - 5, ship.y + ship.height);	
//		laserImage = dodgeService.loadImage("laser-2.png");
//		shipImage = dodgeService.loadImage("ship.png");
//		dodgeSoundShoot = dodgeService.loadSound("shoot-1-81135.mp3");
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
//		ScreenUtils.clear(0, 0, 0, 0);
//		camera.update();
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		batch.draw(shipImage, ship.x, ship.y, ship.width, ship.height);
//		if (isShooting) {
//	        batch.draw(laserImage, laser.x, laser.y, laser.width, laser.height);
//	        laser.y += 2000 * Gdx.graphics.getDeltaTime();  // Adjust the speed of the laser
//	       
//	        
//		}
//		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
