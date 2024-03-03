package com.dodge.game.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy {
	private Sprite sprite;
	private boolean active = false;
	private float speed;
	private float width;
	private float height;
	private float x;
	private float y;
	private float angle;
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(String texturePath) {
		  Texture enemyTexture = new Texture(texturePath);
	        this.sprite = new Sprite(enemyTexture);
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public float getX() {
		return sprite.getY();
	}
	public void setX(float x) {
		this.x = x;
		sprite.setX(x);
	}
	public float getY() {
		return sprite.getY();
	}
	public void setY(float y) {
		this.y = y;
		sprite.setY(y);
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void draw(Batch batch) {
		if (active) {
			sprite.draw(batch);
		}
	}
}
