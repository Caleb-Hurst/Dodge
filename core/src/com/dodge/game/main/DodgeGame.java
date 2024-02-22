package com.dodge.game.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dodge.game.screens.HomeScreen;

public class DodgeGame implements ApplicationListener {
	private Screen currentScreen;
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new HomeScreen(this));

	}

	public void setScreen(Screen screen) {
		if (currentScreen != null) {
			currentScreen.hide();
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
	  public SpriteBatch getBatch() {
	        return batch;
	    }

}
