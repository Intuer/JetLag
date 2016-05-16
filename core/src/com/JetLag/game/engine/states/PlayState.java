package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.*;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {
    private ArrayList<BasicShape> planets;
    private Random rand;
    private Player player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,JetLag.WIDTH / 0.2f, JetLag.HEIGHT / 0.2f);
        Gdx.gl.glClearColor(1,1,1,1);

        map = new Map(cam);
        rand = new Random();
        sr = new ShapeRenderer();
        planets = new ArrayList<>();
        planets.add(new Circle(0,0,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        planets.add(new Circle(1100,0,100,new Vector3(0,22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));
        planets.add(new Circle(1300,0,100,new Vector3(0,-24,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},50));
        player = new Player(1000,0,100,new Vector3(0,20,0),new float[]{0,0,0,1});
        planets.add(player);
        gm = GravityManager.getInstance();
        gm.registerPassive(planets.get(0));
        gm.registerActive(planets.get(1));
        gm.registerActive(planets.get(2));
        gm.registerActive(planets.get(3));



        //TODO
        //gm.registerActive(player);

    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.setVelocity(player.getVelocity().add(0,1,0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.DOWN) ){
            player.setVelocity(player.getVelocity().add(0,-1,0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
            player.setVelocity(player.getVelocity().add(1,0,0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setVelocity(player.getVelocity().add(-1,0,0));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
        cam.position.x = player.getPosition().x + 3*player.getVelocity().x;
        cam.position.y = player.getPosition().y + 3*player.getVelocity().y;
        cam.update();

        map.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1,1,1,1);

        map.draw(sr);
        sr.setProjectionMatrix(cam.combined);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (BasicShape shape : planets) {
            shape.draw(sr);
        }
        sr.end();
    }

    @Override
    public void dispose() {

    }
}
