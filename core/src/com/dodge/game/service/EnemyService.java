package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Ship;

public class EnemyService {
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	ArrayList<Enemy> enemies = new ArrayList<>();
	
	public void generateEnemyEvery3Seconds(Ship playerShip, Viewport viewport){
		Timer.schedule(new Timer.Task() {
		    @Override
		    public void run() {
		        createEnemies(playerShip, viewport);
		    }
		}, 0, 3);
	}
	
	public ArrayList<Enemy> createEnemies(Ship playerShip, Viewport viewport){
		Enemy enemy = objectManagerService.createEnemy(playerShip, viewport);
		enemy.getSprite().setRotation(playerShip.getRotation());
		enemy.setActive(true);
		enemies.add(enemy);
		return enemies;
	}
	public void update(float delta, Ship playerShip) {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy currentEnemy = iterator.next();
			// Calculate the movement along x and y axes based on the initial angle
			Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
			Vector2 enemyPosition = new Vector2(currentEnemy.getSprite().getX(), currentEnemy.getSprite().getY());

			Vector2 direction = playerPosition.sub(enemyPosition).nor();
			float deltaX = currentEnemy.getSpeed() * direction.x * delta;
			float deltaY = currentEnemy.getSpeed() * direction.y * delta;
			currentEnemy.getSprite().translate(deltaX, deltaY);
			currentEnemy.setRotation(playerShip.getRotation()-180);

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
