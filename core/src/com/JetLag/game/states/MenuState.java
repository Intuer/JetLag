package com.jetlag.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jetlag.game.JetLag;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class MenuState extends State {

    private Texture background;
    private Texture button;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        button = new Texture("button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, JetLag.WIDTH, JetLag.HEIGHT);
        sb.draw(button, JetLag.WIDTH / 2 - (50), JetLag.HEIGHT/2,100,50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        button.dispose();
    }
}
