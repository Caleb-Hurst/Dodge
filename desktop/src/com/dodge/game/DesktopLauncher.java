package com.dodge.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dodge.game.main.DodgeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Dodge");
		config.setWindowedMode(1000, 800);
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new DodgeGame(), config);
	}
}
