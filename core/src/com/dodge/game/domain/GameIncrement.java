package com.dodge.game.domain;

public class GameIncrement {
	private int gameScoreIncrement = 50;
	private int gameScoreIncrementCounter;
	private int previousGameScoreIncrement;
	private ObjectSpeed objectSpeed = new ObjectSpeed();
	private boolean isAsteroidEventHappening = false;

	public int getGameScoreIncrement() {
		return gameScoreIncrement;
	}

	public void setGameScoreIncrement(int gameScoreIncrement) {
		this.gameScoreIncrement = gameScoreIncrement;
	}

	public int getGameScoreIncrementCounter() {
		return gameScoreIncrementCounter;
	}

	public void setGameScoreIncrementCounter(int gameScoreIncrementCounter) {
		this.gameScoreIncrementCounter = gameScoreIncrementCounter;
	}

	public ObjectSpeed getObjectSpeed() {
		return objectSpeed;
	}

	public void setObjectSpeed(ObjectSpeed objectSpeed) {
		this.objectSpeed = objectSpeed;
	}

	public int getPreviousGameScoreIncrement() {
		return previousGameScoreIncrement;
	}

	public void setPreviousGameScoreIncrement(int previousGameScoreIncrement) {
		this.previousGameScoreIncrement = previousGameScoreIncrement;
	}

	public boolean isAsteroidEventHappening() {
		return isAsteroidEventHappening;
	}

	public void setAsteroidEventHappening(boolean isAsteroidEventHappening) {
		this.isAsteroidEventHappening = isAsteroidEventHappening;
	}
	

}
