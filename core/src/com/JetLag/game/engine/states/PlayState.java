package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.graphics.sprites.Player2;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {
    private Random rand;
    private Player2 player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;
    private Texture pause;
    private Texture tm;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(1,1,1,1);
        cam.setToOrtho(false,JetLag.WIDTH / 0.2f, JetLag.HEIGHT / 0.2f);

        pause = new Texture("pausebutton.png");
        tm = new Texture("tm.png");
        map = new Map(cam, 20000, 20000);
        rand = new Random();
        sr = new ShapeRenderer();

        map.add(new Circle(-3000,0,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        map.add(new Circle(8000,8000,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        map.add(new Circle(-2200,0,100,new Vector3(0,20,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));
        map.add(new Circle(-2000,0,100,new Vector3(0,-22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},50));
        map.add(new Circle(-4000,0,100,new Vector3(0,-22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},200));
        player = new Player2(-2000,0,100,new Vector3(0,20,0),new float[]{0,0,0,1});

        map.add(new Circle(8800,8000,100,new Vector3(0,20,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));

        int id = map.add(player);
        map.setBound(id);

        gm = GravityManager.getInstance();
        gm.registerPassive(map.get(0));
        gm.registerPassive(map.get(1));
        gm.registerActive(map.get(2));
        gm.registerActive(map.get(3));
        gm.registerActive(map.get(4));
        gm.registerActive(map.get(5));
        gm.registerActive(map.get(6));
    }

    @Override
    protected void handleInput() {
        if ( Gdx.input.getX() < 70 ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            gsm.push(new PauseState(gsm));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.UP) ){
            player.setVelocity(player.getVelocity().add(new Vector3((float) Math.cos(player.getRotateRad()), (float) Math.sin(player.getRotateRad()), 0).scl(0.3f)));
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) {
            player.setRotate(player.getRotate() - 2);
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
            player.setRotate(player.getRotate() +2 );
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        gm.update(dt);

        cam.position.x = player.getPosition().x + 3*player.getVelocity().x;
        cam.position.y = player.getPosition().y + 3*player.getVelocity().y;
        cam.update();
        map.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sr.setProjectionMatrix(cam.combined);
        map.render(sr);

        sb.begin();
        sb.draw(pause,20,JetLag.HEIGHT-pause.getHeight()-20);
        sb.draw(tm,JetLag.WIDTH-70,20);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
