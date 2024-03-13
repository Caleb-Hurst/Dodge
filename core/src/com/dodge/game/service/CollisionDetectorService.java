package com.dodge.game.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Ship;

public class CollisionDetectorService {

	public Rectangle createPlayerShipHitBox(Ship playerShip) {
		Rectangle shipRectangle = new Rectangle(playerShip.getSprite().getX(), playerShip.getSprite().getY(),
				playerShip.getSprite().getWidth(), playerShip.getSprite().getHeight());
		shipRectangle.width *= .47f;
		shipRectangle.height *= .47f;
		shipRectangle.x += 16;
		shipRectangle.y += 16;
		return shipRectangle;
	}

	public Rectangle createEnemyHitBox(Enemy currentEnemy) {
		Rectangle shipRectangle = new Rectangle(currentEnemy.getSprite().getX(), currentEnemy.getSprite().getY(),
				currentEnemy.getSprite().getWidth(), currentEnemy.getSprite().getHeight());
		shipRectangle.width *= .47f;
		shipRectangle.height *= .47f;
		shipRectangle.x += 16;
		shipRectangle.y += 16;
		return shipRectangle;
	}

	public Rectangle createAsteroidHitBox(Asteroid asteroid) {
		Rectangle asteroidBoundingBox = new Rectangle(asteroid.getSprite().getBoundingRectangle());
		asteroidBoundingBox.width *= 0.3f; // Adjust as needed
		asteroidBoundingBox.height *= .3f; // Adjust as needed
		asteroidBoundingBox.setPosition(asteroid.getSprite().getX() + 20, asteroid.getSprite().getY() + 10);
		return asteroidBoundingBox;
	}

	public void drawHitBoxPlayerShip(Rectangle rectangle) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		shapeRenderer.identity();
		shapeRenderer.end();
	}
	public void drawHitBoxEnemyShip(Rectangle rectangle) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		shapeRenderer.identity();
		shapeRenderer.end();
	}

	public void drawHitBoxAsteroid(Rectangle rectangle, Asteroid asteroid) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		float rotationAngle = asteroid.getRotation() + 50;
		shapeRenderer.begin(ShapeType.Line);

		// Set the color (optional)
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.translate(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2, 0);
		shapeRenderer.rotate(0, 0, 1, rotationAngle);
		shapeRenderer.translate(-rectangle.x - rectangle.width / 2, -rectangle.y - rectangle.height / 2, 0);
		// Draw the circle
		shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		shapeRenderer.identity();

		// End drawing shapes
		shapeRenderer.end();

	}

}
