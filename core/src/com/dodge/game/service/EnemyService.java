package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Ship;

public class EnemyService {
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private LaserService laserService;
	ArrayList<Enemy> enemies = new ArrayList<>();
	public EnemyService(LaserService laserService) {
        this.laserService = laserService;
    }
	public void generateEnemyEvery3Seconds(Ship playerShip, Viewport viewport){
		Timer.schedule(new Timer.Task() {
		    @Override
		    public void run() {
		        createEnemies(playerShip, viewport);
		        
		    }
		}, 0, 4);
	}
	
	public Enemy shootLaserEvery3Seconds(Enemy enemy) {
		Timer.schedule(new Timer.Task() {
		    @Override
		    public void run() {
		        laserService.enemyShoot(enemy);
		    }
		}, 3, 3);
		return enemy;
	}
	
	public ArrayList<Enemy> createEnemies(Ship playerShip, Viewport viewport){
		Enemy enemy = objectManagerService.createEnemy(playerShip, viewport);
		shootLaserEvery3Seconds(enemy);
		enemy.getSprite().setRotation(playerShip.getRotation());
		enemy.setActive(true);
		enemies.add(enemy);
		return enemies;
	}
	public void updateEnemyShip(float delta, Ship playerShip) {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy currentEnemy = iterator.next();
			// Calculate the movement along x and y axes based on the initial angle
			Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
			Vector2 enemyPosition = new Vector2(currentEnemy.getSprite().getX(), currentEnemy.getSprite().getY());
			// Calculate the angle in radians
	        float angleRad = MathUtils.atan2(playerPosition.y - enemyPosition.y, playerPosition.x - enemyPosition.x);

	        // Convert the angle to degrees and adjust by 90 degrees
	        float angleDeg = MathUtils.radiansToDegrees * angleRad + 270f;

	        // Set the rotation of the enemy ship's sprite
	        currentEnemy.getSprite().setRotation(angleDeg);

			Vector2 direction = playerPosition.sub(enemyPosition).nor();
			float deltaX = currentEnemy.getSpeed() * direction.x * delta;
			float deltaY = currentEnemy.getSpeed() * direction.y * delta;
			currentEnemy.getSprite().translate(deltaX, deltaY);
			

			// Optionally, you can add logic to check if the laser goes off the screen and
			// handle it accordingly
			if (currentEnemy.getSprite().getY() > Gdx.graphics.getHeight() || currentEnemy.getSprite().getY() < 0
					|| currentEnemy.getSprite().getX() < 0
					|| currentEnemy.getSprite().getX() > Gdx.graphics.getWidth()) {
				iterator.remove(); // Remove the laser if it goes off the screen
			}
		}
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

}
