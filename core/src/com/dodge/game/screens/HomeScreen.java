package com.dodge.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.SoundManagerService;


public class HomeScreen implements Screen{
	
	private DodgeGame game;
    private BitmapFont font;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SoundManagerService soundManagerService;
    private Rectangle startGameButton;
    private Rectangle tutorialButton;

	  public HomeScreen(DodgeGame game) {
	        this.game = game;
	        this.font = new BitmapFont();
	        this.camera = new OrthographicCamera();
	        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        this.batch = new SpriteBatch();
	        this.soundManagerService = new SoundManagerService();
	    }
    @Override
    public void show() {       
    	float buttonWidth = 100;
        float buttonHeight = 30;
        float lineHeight = 30f;
        startGameButton = new Rectangle(Gdx.graphics.getWidth() / 2 - buttonWidth / 2,
                Gdx.graphics.getHeight() / 2 - lineHeight * 2,
                buttonWidth, buttonHeight);

tutorialButton = new Rectangle(Gdx.graphics.getWidth() / 2 - buttonWidth / 2,
               Gdx.graphics.getHeight() / 2 - lineHeight - buttonHeight,
               buttonWidth, buttonHeight);
        soundManagerService.playMusic("8bit-music-for-game-68698.mp3");
    }

    @Override
    public void render(float delta) {
    	 Gdx.gl.glClearColor(0, 0, 0, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

         // Set up the camera and batch
         camera.update();
         batch.setProjectionMatrix(camera.combined);

         // Begin the batch
         batch.begin();

         // Draw the text as a button
         font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
         font.getData().setScale(1);
         float lineHeight = 30f;
        
         font.draw(batch, "DODGE", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - lineHeight * 2); // Adjust the multiplier as needed

         font.draw(batch, "Start Game", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 );
         font.draw(batch, "Tutorial", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - lineHeight);  // End the batch
         if (Gdx.input.justTouched()) {
             Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
             camera.unproject(touchPos); // Convert screen coordinates to world coordinates

             // Check if the click is within the start game button
             if (startGameButton.contains(touchPos.x, touchPos.y)) {
                 game.setScreen(new PlayScreen(game)); // Transition to the GameScreen
             }

             // Check if the click is within the tutorial button
             if (tutorialButton.contains(touchPos.x, touchPos.y)) {
                 game.setScreen(new TutorialScreen(game)); // Transition to the TutorialScreen
             }
         }
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
		// TODO Auto-generated method stub
		
	}

}
