package com.dodge.game.service;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraService {
	
	public OrthographicCamera generateCamera() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		return camera;
	}
}
