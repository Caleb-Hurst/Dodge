package com.dodge.game.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	private Sprite sprite;
	private boolean active = false;
	private float speed;
	private float width;
	private float height;
	private float angle;
	private float x;
	private float y;
	private Vector2 direction;
	
	public float getX() {
		return sprite.getX();
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return sprite.getY();
	}
	public void setY(float y) {
		this.y = y;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(String texturePath) {
		  Texture enemyTexture = new Texture(texturePath);
	        this.sprite = new Sprite(enemyTexture);
	}
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void draw(Batch batch) {
		if (active) {
			sprite.draw(batch);
		}
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	
	public void setSize(float x, float y) {
		sprite.setSize(x, y);
	}
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	public void setRotation(float angle) {
		sprite.setRotation(angle);
		
	}
	public float getRotation() {
		return sprite.getRotation();
	}
	
}
