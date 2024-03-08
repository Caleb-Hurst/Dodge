package com.dodge.game.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Asteroid {
	private Sprite sprite;
	private Boolean active = false;
	private float speed;
	private float angle;
	private float rotation;
	

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(String texturePath) {
		Texture enemyTexture = new Texture(texturePath);
		this.sprite = new Sprite(enemyTexture);
	}

	public void setSize(float x, float y) {
		sprite.setSize(x, y);
	}

	public void draw(Batch batch) {
		if (active) {
			sprite.draw(batch);
		}
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getRotation() {
		return sprite.getRotation();
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		sprite.setRotation(rotation);
	}
	
}
