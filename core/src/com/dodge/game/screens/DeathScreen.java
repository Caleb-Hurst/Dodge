package com.dodge.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.SoundManagerService;

public class DeathScreen implements Screen {
    private ShapeRenderer shapeRenderer;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch spriteBatch;
    private DodgeGame game;
    private Stage stage;

    public DeathScreen(DodgeGame game) {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        font.getData().setScale(5);

        // Create a label for "PLAY AGAIN" and make it clickable
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label playAgainLabel = new Label("PLAY AGAIN", labelStyle);
        playAgainLabel.setFontScale(5); // Adjust the font scale as needed
        playAgainLabel.setPosition(Gdx.graphics.getWidth() / 2, (Gdx.graphics.getHeight() / 2) -80, Align.center);
        playAgainLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle the click event (restart the game)
                game.setScreen(new PlayScreen(game));
                dispose(); // Dispose resources before switching screens
            }
        });

        // Add the label to the stage
        stage.addActor(playAgainLabel);
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        font.draw(spriteBatch, "OOPS YOU LOSE :( ", (Gdx.graphics.getWidth() / 2) - 320,
				(Gdx.graphics.getHeight() / 2) + 80);
        spriteBatch.end();

        // Draw the stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
