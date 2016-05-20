package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jacob_000 on 2016-05-11.
 */
public class PauseState extends State {

    private Texture pausetext;
    private Texture background;
    private OrthographicCamera menucam;

    protected PauseState(GameStateManager gsm) {
        super(gsm);
        menucam = new OrthographicCamera();
        menucam.setToOrtho(false, JetLag.WIDTH, JetLag.HEIGHT);
        menucam.update();
        background = new Texture("space-background.png");
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
        sb.setProjectionMatrix(menucam.combined);
        sb.begin();
        sb.draw(background,0,0, JetLag.WIDTH, JetLag.HEIGHT);
        sb.draw(pausetext, JetLag.WIDTH/2 - (pausetext.getWidth()/2),200);
        sb.end();
    }

    @Override
    public void dispose() {
        pausetext.dispose();
        background.dispose();
    }
}
