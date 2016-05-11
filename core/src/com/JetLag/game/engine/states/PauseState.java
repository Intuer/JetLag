package com.JetLag.game.engine.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jacob_000 on 2016-05-11.
 */
public class PauseState extends State {

    private Texture pausetext;

    protected PauseState(GameStateManager gsm) {
        super(gsm);
        pausetext = new Texture("pausetext.png");
    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.pop();
            dispose();
        }

    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(pausetext,200,200);
        sb.end();
    }

    @Override
    public void dispose() {
        pausetext.dispose();
    }
}
