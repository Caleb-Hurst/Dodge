package com.dodge.game.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dodge.game.screens.HomeScreen;

public class DodgeGame implements ApplicationListener {
	private Screen currentScreen;

	@Override
	public void create() {
		setScreen(new HomeScreen(this));
	}

	public void setScreen(Screen screen) {
		if (currentScreen != null) {
			currentScreen.dispose();
		}

		currentScreen = screen;
		currentScreen.show();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		if (currentScreen != null) {
            currentScreen.render(Gdx.graphics.getDeltaTime()); // You might want to pass a delta time here
        }

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
