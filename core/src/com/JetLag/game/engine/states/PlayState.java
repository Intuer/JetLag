package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.*;
import com.JetLag.game.engine.physics.GravityManager;
import com.JetLag.game.engine.physics.System;
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
        player = new Player(1000,0,100,new Vector3(0,0,0),new float[]{0,0,0,1});
        planets.add(player);
        gm = GravityManager.getInstance();

        System s = new System(0, 0);
        s.addObject(planets.get(0), System.TYPE.PASSIVE);
        s.addObject(planets.get(1), System.TYPE.ACTIVE);
        s.addObject(planets.get(2), System.TYPE.ACTIVE);

        Circle c = new Circle(2000, 2000, 50, new Vector3(0, 0, 0), new float[]{1f, 0f, 1f, 1f}, 50);
        s.addObject(c, System.TYPE.ACTIVE);

        gm.setOrbitalSpeed(planets.get(2), planets.get(0));
        planets.get(2).addVel(new Vector3(0,3000,0));
        gm.setOrbitalSpeed(planets.get(1), planets.get(0));
        gm.setOrbitalSpeed(c, planets.get(0));
        map.addSystem(s);

        //TODO
        //gm.registerActive(player);

    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.setVelocity(player.getVelocity().add((float)Math.cos(player.getRotationRad()),(float)Math.sin(player.getRotationRad()),0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
            player.setRotation(player.getRotation() - 1);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setRotation(player.getRotation() + 1);
        }
        player.addPosition(player.getVelocity());
    }

    @Override
    protected void update(float dt) {

        handleInput();
        gm.setDelta(dt);
        cam.position.x = player.getPosition().x + 3*player.getVelocity().x;
        cam.position.y = player.getPosition().y + 3*player.getVelocity().y;
        cam.update();

        map.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1,1,1,1);

        map.drawGrid(sr);

        sr.setProjectionMatrix(cam.combined);
        map.draw(sr);
        planets.get(3).draw(sr);

    }

    @Override
    public void dispose() {

    }
}
