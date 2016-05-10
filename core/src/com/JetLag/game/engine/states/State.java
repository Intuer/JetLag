package com.JetLag.game.engine.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        mouse = new Vector3();
        cam = new OrthographicCamera();

    }

    protected abstract void handleInput();

    protected abstract void update(float dt);

    protected abstract void render(SpriteBatch sb);

    public abstract void dispose();

}
