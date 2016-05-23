package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.graphics.sprites.Player;
import com.JetLag.game.engine.graphics.sprites.Player2;
import com.JetLag.game.engine.graphics.sprites.grid.Grid;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayState. The state that handles the actual
 */
public class PlayState extends State {
    private ArrayList<PhysObject> planets;
    private Random rand;
    private Player2 player;
    private ShapeRenderer sr;
    private GravityManager gm;
    private Map map;
    private Grid grid;
    private Texture pause;
    private Texture tm;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,JetLag.WIDTH / 0.2f, JetLag.HEIGHT / 0.2f);
        Gdx.gl.glClearColor(1,1,1,1);
        pause = new Texture("pausebutton.png");
        grid = new Grid(cam);
        tm = new Texture("tm.png");
        //map = new Map("space-background.png", 512, 512);
        rand = new Random();
        sr = new ShapeRenderer();
        planets = new ArrayList<PhysObject>();
        planets.add(new Circle(-3000,0,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));
        planets.add(new Circle(8000,8000,100000,new Vector3(0,0,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},500));

        planets.add(new Circle(-2200,0,100,new Vector3(0,20,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));
        planets.add(new Circle(-2000,0,100,new Vector3(0,-22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},50));
        planets.add(new Circle(-4000,0,100,new Vector3(0,-22,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},200));
        player = new Player2(-2000,0,100,new Vector3(0,20,0),new float[]{0,0,0,1});

        planets.add(new Circle(8800,8000,100,new Vector3(0,20,0),new float[]{rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1},100));

        planets.add(player);
        gm = GravityManager.getInstance();
        gm.registerPassive(planets.get(0));
        gm.registerPassive(planets.get(1));
        gm.registerActive(planets.get(2));
        gm.registerActive(planets.get(3));
        gm.registerActive(planets.get(4));
        gm.registerActive(planets.get(5));
        gm.registerActive(planets.get(6));


        player.setBounds(-20000, -20000, 40000, 40000);

        //TODO
        //gm.registerActive(player);

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
            player.setJet(true);
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
        //cam.translate(player.getVelocity().x, -player.getVelocity().y);
        //map.moveBackground((int) player.getVelocity().x / 500, (int) player.getVelocity().y / 500);

        cam.position.x = player.getPosition().x + 3*player.getVelocity().x;
        cam.position.y = player.getPosition().y + 3*player.getVelocity().y;
        player.moveToBounds();
        cam.update();
        grid.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1,1,1,1);
        grid.render(sr);

        //map.drawBackground(sb);
        sr.setProjectionMatrix(cam.combined);


        for (PhysObject shape : planets) {
            shape.render(sr);
        }

        sb.begin();
        sb.draw(pause,20,JetLag.HEIGHT-pause.getHeight()-20);
        sb.draw(tm,JetLag.WIDTH-70,20);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
