package com.dodge.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Ship;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.ObjectManagerService;
import com.dodge.game.service.SoundManagerService;

public class HomeScreen implements Screen {
	private Stage stage;
	private DodgeGame game;
	private BitmapFont font;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SoundManagerService soundManagerService;
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private Ship playerShip = ObjectManagerService.createPlayerShip();
	private Asteroid asteroid = objectManagerService.testAsteroid(playerShip);

	public HomeScreen(DodgeGame game) {
		this.game = game;
		this.font = new BitmapFont();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.batch = new SpriteBatch();
		this.soundManagerService = new SoundManagerService();
		asteroid.setActive(true);

	}

	@Override
	public void show() {
		// A 2D scene graph containing hierarchies of actors.
		// Stage handles the viewport and distributes input events.
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font.getData().setScale(5);
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		// Using something called a table that organizes all of my elements to display
		// on the screen
		Table table = new Table();
		// Makes the table take the whole stage
		table.setFillParent(true);
		// Creating the actual buttons
		TextButton playButton = new TextButton("PLAY", textButtonStyle);
		TextButton tutorialButton = new TextButton("TUTORIAL", textButtonStyle);
//	        soundManagerService.playMusic("8bit-music-for-game-68698.mp3");
		// Add button listeners
		// Add buttons to the table
		table.add(playButton).padBottom(20).row();
		table.add(tutorialButton).padBottom(20).row();

		// Add the table to the stage
		stage.addActor(table);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Handle Play button click (navigate to Play screen, etc.)
				game.setScreen(new PlayScreen(game));
				dispose();
				playButton.clearListeners();
				playButton.remove();
				tutorialButton.clearListeners();
				tutorialButton.remove();
			}
		});

		tutorialButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Handle Tutorial button click (navigate to Tutorial screen, etc.)
				game.setScreen(new TutorialScreen(game));
				dispose();
				tutorialButton.clearListeners();
				tutorialButton.remove();
				playButton.clearListeners();
				playButton.remove();
			}
		});

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw the stage
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		batch.begin();
		font.draw(batch, "DODGE", (Gdx.graphics.getWidth() / 2) - 135, Gdx.graphics.getHeight() - 10);
		asteroid.draw(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

	}

}
