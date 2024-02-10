package com.dodge.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class DodgeGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture dodgeImage;
	private Texture bucketImage;
	private Sound dodgeSoundShoot;
	private Music dodgeMusic;
	private Rectangle bucket;
	private float bucketSpeed = 800;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 50 / 2;
		bucket.y = 20;
		bucket.width = 50;
		bucket.height = 50;
		// load images
		dodgeImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// load sounds
		dodgeSoundShoot = Gdx.audio.newSound(Gdx.files.internal("shoot-1-81135.mp3"));
		dodgeMusic = Gdx.audio.newMusic(Gdx.files.internal("8bit-music-for-game-68698.mp3"));

		// start playback
		dodgeMusic.setLooping(true);
		dodgeMusic.play();
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
		    // Move the bucket left
		    bucket.x -= bucketSpeed * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		    // Move the bucket right
		    bucket.x += bucketSpeed * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
		    // Move the bucket down
		    bucket.y -= bucketSpeed * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
		    // Move the bucket up
		    bucket.y += bucketSpeed * Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		dodgeImage.dispose();
	}
}
