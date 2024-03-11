package com.dodge.game.domain;

public class ObjectSpeed {
	private float enemyShipSpeed;
	private float randomEnemyShipSpeed;
	private float laserSpeed;
	private float randomEnemyLaserSpeed;
	private float asteroidSpeed;
	private float randomAsteroidSpeed;
	private float chaosAsteroidSpeed;
	private boolean hasBeenSet = true;
	public float getEnemyShipSpeed() {
		return enemyShipSpeed;
	}
	public void setEnemyShipSpeed(float enemyShipSpeed) {
		this.enemyShipSpeed = enemyShipSpeed;
	}
	public float getRandomEnemyShipSpeed() {
		return randomEnemyShipSpeed;
	}
	public void setRandomEnemyShipSpeed(float randomEnemyShipSpeed) {
		this.randomEnemyShipSpeed = randomEnemyShipSpeed;
	}
	public float getLaserSpeed() {
		return laserSpeed;
	}
	public void setLaserSpeed(float laserSpeed) {
		this.laserSpeed = laserSpeed;
	}
	public float getRandomEnemyLaserSpeed() {
		return randomEnemyLaserSpeed;
	}
	public void setRandomEnemyLaserSpeed(float randomEnemyLaserSpeed) {
		this.randomEnemyLaserSpeed = randomEnemyLaserSpeed;
	}
	public float getAsteroidSpeed() {
		return asteroidSpeed;
	}
	public void setAsteroidSpeed(float asteroidSpeed) {
		this.asteroidSpeed = asteroidSpeed;
	}
	public float getRandomAsteroidSpeed() {
		return randomAsteroidSpeed;
	}
	public void setRandomAsteroidSpeed(float randomAsteroidSpeed) {
		this.randomAsteroidSpeed = randomAsteroidSpeed;
	}
	public float getChaosAsteroidSpeed() {
		return chaosAsteroidSpeed;
	}
	public void setChaosAsteroidSpeed(float chaosAsteroidSpeed) {
		this.chaosAsteroidSpeed = chaosAsteroidSpeed;
	}
	public boolean isHasBeenSet() {
		return hasBeenSet;
	}
	public void setHasBeenSet(boolean hasBeenSet) {
		this.hasBeenSet = hasBeenSet;
	}
	

}
