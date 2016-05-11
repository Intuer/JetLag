package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.JetLag.game.engine.graphics.sprites.Planet;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {

    private Texture background;
    private OrthographicCamera camera;
    private ArrayList<Circle> planets;
    private Random rand;
    private ShapeRenderer sr;
    private GravityManager gm;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        rand = new Random();
        background = new Texture("background.png");
        sr = new ShapeRenderer();
        planets = new ArrayList<Circle>();
        planets.add(new Circle(0,0,1000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        planets.add(new Circle(700,10,100,new Vector3(0,30,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},50));
        gm = GravityManager.getInstance();
        gm.registerPassive(planets.get(0));
        gm.registerActive(planets.get(1));
        cam.setToOrtho(false,JetLag.WIDTH, JetLag.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(1,1,1,1);
        sb.begin();
        //sb.draw(background,0,0,JetLag.WIDTH, JetLag.HEIGHT);
        sb.end();
        for( Circle p : planets){
            p.render(sr);
        }
    }

    @Override
    public void dispose() {

    }
}
