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

public class TutorialScreen implements Screen{
    private BitmapFont font = new BitmapFont();
    private SpriteBatch spriteBatch;
    private DodgeGame game;
    private Stage stage;

    public TutorialScreen(DodgeGame game) {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        font.getData().setScale(2);

        // Create a label for "PLAY AGAIN" and make it clickable
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLUE);
        Label playAgainLabel = new Label("PLAY", labelStyle);
        playAgainLabel.setFontScale(5); // Adjust the font scale as needed
        playAgainLabel.setPosition((Gdx.graphics.getWidth() / 2) - 50, (Gdx.graphics.getHeight() / 2) -240, Align.center);
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
    	 Gdx.gl.glClearColor(0, 0, 0, 1);
    	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        font.draw(spriteBatch, "SURVIVE AS LONG AS POSSIBLE AS THE GAME GETS FASTER! ", (Gdx.graphics.getWidth() / 2) - 420,
				(Gdx.graphics.getHeight() / 2) + 240);
        font.draw(spriteBatch, "USE THE A AND D KEYS TO ROTATE YOUR SHIP", (Gdx.graphics.getWidth() / 2) - 320,
				(Gdx.graphics.getHeight() / 2) + 160);
        font.draw(spriteBatch, "USE THE ARROW KEYS TO MOVE AROUND SPACE", (Gdx.graphics.getWidth() / 2) - 320,
				(Gdx.graphics.getHeight() / 2) +80);
        font.draw(spriteBatch, "USE THE USE THE SPACEBAR TO SHOOT INCOMING ENEMY'S!", (Gdx.graphics.getWidth() / 2) - 420,
				(Gdx.graphics.getHeight() / 2) +0);
        font.draw(spriteBatch, "GOOD LUCK!", (Gdx.graphics.getWidth() / 2) -80,
				(Gdx.graphics.getHeight() / 2) -80);
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
