package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.JetLag.game.engine.graphics.sprites.Planet;

import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {

    private Texture background;
    private OrthographicCamera camera;
    private Circle[] planets;
    private Random rand;
    private ShapeRenderer sr;
    private GravityManager gm;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        rand = new Random();
        background = new Texture("background.png");
        sr = new ShapeRenderer();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1,1,1,1);
        sb.begin();
        //sb.draw(background,0,0,JetLag.WIDTH, JetLag.HEIGHT);
        sb.end();
        gm.update();
        for( Circle p : planets){
            p.render(sr);
        }
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1,1,0,1);
        sr.circle(100,100,30);
        sr.end();
    }

    @Override
    public void dispose() {

    }
}
