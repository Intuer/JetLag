package com.jetlag.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jetlag.game.states.GameStateManager;
import com.jetlag.game.states.MenuState;

public class JetLag extends ApplicationAdapter {
	public static final int WIDTH = 700;
	public static final int HEIGHT = 500;
	public static final String TITLE = "Jet Lag";

	private GameStateManager gsm;

	private SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}