package com.dodge.game.domain;

public class GameIncrement {
	private int gameScoreIncrement;
	private int gameScoreIncrementCounter;
	private ObjectSpeed objectSpeed;

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

}
