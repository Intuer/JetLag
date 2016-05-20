package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.*;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {
    private ArrayList<BasicSprite> planets;
    private Player player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;
    private OrthographicCamera staticcam;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(1,1,1,1);
        staticcam = new OrthographicCamera();

        map = new Map("space-background.png", 512, 512);
        sr = new ShapeRenderer();

        cam.setToOrtho(false, JetLag.WIDTH * map.getZoom(), JetLag.HEIGHT * map.getZoom());
        staticcam.setToOrtho(false, JetLag.WIDTH * map.getZoom(), JetLag.HEIGHT * map.getZoom());

        planets = new ArrayList<>();
        planets.add(new Planet(0, 0, 500, 100000, new Vector3(0,0,0)));
        planets.add(new Planet(1100, 0, 100, 100, new Vector3(0,22,0)));
        planets.add(new Planet(1300, 0, 50, 100, new Vector3(0,-24,0)));
        player = new Player(JetLag.WIDTH/2, JetLag.HEIGHT/2, 100,new Vector3(0,0,0),new float[]{87,0,131,1});
        //planets.add(player);

        gm = GravityManager.getInstance();
        gm.registerPassive(planets.get(0));
        gm.registerActive(planets.get(1));
        gm.registerActive(planets.get(2));
        //gm.registerActive(player);


        player.setBounds(-10000, -10000, 20000, 20000);

        //TODO
        //gm.registerActive(player);

    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.setVelocity(player.getVelocity().add((float)Math.cos(player.getRotateRad()),(float)Math.sin(player.getRotateRad()),0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            player.setRotate(player.getRotate() - 1);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setRotate(player.getRotate() +1 );
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
        player.update();
        //cam.translate(player.getVelocity().x, -player.getVelocity().y);
        map.moveBackground((int) player.getVelocity().x / 5, (int) -player.getVelocity().y / 5);

        player.moveToBounds();
        cam.translate(player.getVelocity().x, player.getVelocity().y);
        cam.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(staticcam.combined);
        map.drawBackground(sb, player);

        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        for (BasicSprite shape : planets) {
            shape.draw(sb);
        }
        sb.end();

        player.render(sr);
    }

    @Override
    public void dispose() {

    }
}
