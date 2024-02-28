package com.dodge.game.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Laser {
	private Sprite sprite;
	private Rectangle boundingBox;
	private Boolean active;
	private float speed;
    private float angle;
    private float initialAngle;

	public Laser(float x, float y, float width, float height, String texturePath, float speed, float shipRotation) {
		Texture lasterTexture = new Texture(texturePath);
		sprite = new Sprite(lasterTexture);

		// Set the dimensions of the sprite
		sprite.setSize(width, height);

		// Set the bounding box for collision detection
		boundingBox = new Rectangle(x, y, width, height);

		active = false;
		this.speed = speed;
        this.angle = shipRotation; // Initial angle
        this.initialAngle = shipRotation; 
	}

	public void setSize(float x, float y) {
		sprite.setSize(x, y);
	}
	public void shoot(Ship playerShip) {
		float spriteCenterX = playerShip.getX() + sprite.getWidth() / 2;
		float spriteBottomY = playerShip.getY() + -20f; // Change to bottom

		// Set the origin to the bottom-center of the sprite
		sprite.setOrigin(sprite.getWidth() / 2, 0);

		// Calculate a position relative to the bottom-center of the sprite
		float offsetX = 25f;
		/* your offset from the center along the x-axis */;
		float offsetY = -50f;
		/* your offset from the bottom along the y-axis */;

		float targetX = spriteCenterX + offsetX;
		float targetY = spriteBottomY - offsetY; // Subtract offsetY from the bottom Y
		sprite.setPosition(targetX, targetY);
		sprite.setRotation(playerShip.getRotation());
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public void draw(Batch batch) {
		if (active) {
			sprite.draw(batch);
		}
	}
	public void update(float delta, float initialAngle) {
        // Calculate the movement along x and y axes based on the initial angle
        float deltaX = speed * delta * MathUtils.cosDeg(initialAngle);
        float deltaY = -speed * delta * MathUtils.sinDeg(initialAngle);  // Negate the sin component

        // Move the laser based on calculated values
        sprite.translate(deltaY, deltaX);

        // Optionally, you can add logic to check if the laser goes off the screen and handle it accordingly
        if (sprite.getY() > Gdx.graphics.getHeight() || sprite.getY() < 0 ||
            sprite.getX() < 0 || sprite.getX() > Gdx.graphics.getWidth()) {
            // Optionally, you can handle what should happen when the laser goes off the screen
        }
    }
}
