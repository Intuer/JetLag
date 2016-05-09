package com.JetLag.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.JetLag.game.JetLag;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JetLag.WIDTH;
		config.height = JetLag.HEIGHT;
		config.title = JetLag.TITLE;
		new LwjglApplication(new JetLag(), config);
	}
}
