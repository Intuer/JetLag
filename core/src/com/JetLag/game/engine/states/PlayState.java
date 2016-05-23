package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.*;
import com.JetLag.game.engine.physics.CollisionManager;
import com.JetLag.game.engine.physics.GravityManager;
import com.JetLag.game.engine.utils.AsteroidUtils;
import com.JetLag.game.engine.utils.MapUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {
    private Player player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;
    private OrthographicCamera staticcam;
    private AsteroidUtils au;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(1,1,1,1);
        staticcam = new OrthographicCamera();
        gm = GravityManager.getInstance();

        map = new Map();
        MapUtils maputils = new MapUtils(map, 300, 100000);
        maputils.setRandomRadiusAmount(1.3f);
        maputils.spawnRandomPlanets(5);
        sr = new ShapeRenderer();

        cam.setToOrtho(false, JetLag.WIDTH * map.getZoom(), JetLag.HEIGHT * map.getZoom());
        staticcam.setToOrtho(false, JetLag.WIDTH * map.getZoom(), JetLag.HEIGHT * map.getZoom());

        /*
        int id = map.add(new Planet(0, 0, 500, 100000, new Vector3(0,0,0)));
        gm.registerPassive(map.get(id));
        //map.add(new Planet(1100, 0, 700, 100, new Vector3(0,22,0)));

        for (int i = 0; i < 3; i++) {
            id = map.add(new Planet(1000*(i + 1), 0, 800, 100, new Vector3(0, -24 + 6*(i), 0)));
            gm.registerActive(map.get(id));
        }

        */
        player = new Player((int) (map.getZoom()*JetLag.WIDTH)/2, (int) (map.getZoom()*JetLag.HEIGHT)/2, 100, new Vector3(0,0,0));
        gm.registerActive(player);

        au = new AsteroidUtils(map, player, 7, 3, 6, 3);

        //TODO

    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.addVel(new Vector3((float)Math.cos(player.getAngleInRad()),(float)Math.sin(player.getAngleInRad()),0));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            player.setAngle(player.getAngle() - 3);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setAngle(player.getAngle() + 3);
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
        au.update();
        au.spawn();

        map.moveBackground((int) player.getVelocity().x / 5, (int) -player.getVelocity().y / 5);
        map.update();
        map.updatePlayer(player);

        //cam.translate(player.getVelocity().x, player.getVelocity().y);
        cam.position.x = player.getPosition().x;
        cam.position.y = player.getPosition().y;
        cam.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(staticcam.combined);
        map.drawBackground(sb, player);

        sb.setProjectionMatrix(cam.combined);


        sb.begin();
        player.draw(sb);

        map.drawObjects(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
