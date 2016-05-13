package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.graphics.sprites.Player;
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
    private ArrayList<PhysObject> planets;
    private Random rand;
    private Player player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(1,1,1,1);

        map = new Map("space-background.png", 512, 512);
        rand = new Random();
        sr = new ShapeRenderer();
        planets = new ArrayList<PhysObject>();
        planets.add(new Circle(0,0,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        planets.add(new Circle(1100,0,100,new Vector3(0,22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));
        planets.add(new Circle(1300,0,100,new Vector3(0,-24,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},50));
        player = new Player(900,0,100,new Vector3(0,5,0),new float[]{0.4f,0.4f,0.4f});
        planets.add(player);
        gm = GravityManager.getInstance();
        gm.registerPassive(planets.get(0));
        gm.registerActive(planets.get(1));
        gm.registerActive(planets.get(2));
        gm.registerActive(planets.get(3));
        cam.setToOrtho(false,JetLag.WIDTH / 0.3f, JetLag.HEIGHT / 0.3f);
    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.setVelocity(player.getVelocity().add(0,1,0));
            map.moveBackground(0, -1);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.DOWN) ){
            player.setVelocity(player.getVelocity().add(0,-1,0));
            map.moveBackground(0, 1);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ){
            player.setVelocity(player.getVelocity().add(1,0,0));
            map.moveBackground(1, 0);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ){
            player.setVelocity(player.getVelocity().add(-1,0,0));
            map.moveBackground(-1, 0);
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);
        cam.position.x = player.getPosition().x;
        cam.position.y = player.getPosition().y;
        cam.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sr.setProjectionMatrix(cam.combined);
        map.drawBackground(sb);

        for( PhysObject p : planets){
            p.render(sr);
        }

    }

    @Override
    public void dispose() {

    }
}
