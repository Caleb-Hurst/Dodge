package com.dodge.game.domain;

import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
	private Music music;
	private boolean active;
	private String fileName;
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
