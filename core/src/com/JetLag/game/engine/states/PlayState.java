package com.JetLag.game.engine.states;

import com.JetLag.game.JetLag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.JetLag.game.engine.graphics.sprites.Planet;

import java.util.Random;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class PlayState extends State {

    private static final float G = 0.001f;

    private Texture background;
    private OrthographicCamera camera;
    private Planet planet;
    private Planet[] planets;
    private Random rand;
    private ShapeRenderer sr;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        planet = new Planet(300,200, 100, 500000000,new Vector3(0,0,0));
        rand = new Random();
        planets = new Planet[2];
        planets[0] = planet;
        Planet p2 = new Planet(250,50, 200, 500,new Vector3(400,0,0));
        planets[1] = p2;
        //for (int i = 0; i < 10; i++){
        //    planets[i] = new Planet(rand.nextInt(JetLag.WIDTH),rand.nextInt(JetLag.HEIGHT),50 );
        //}
        background = new Texture("background.png");
        sr = new ShapeRenderer();
        camera = new OrthographicCamera(JetLag.WIDTH, JetLag.HEIGHT);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();
        for(Planet p1: planets) {
            for(Planet p2 : planets){
                if ( p1 != p2 ){
                    float fSize = (G*p1.getMass()*p2.getMass())/ (distance(p1,p2)[2]*distance(p1,p2)[2]);
                    float angle = angle(p1, p2);
                    System.out.println(distance(p1,p2)[2]);
                    Vector3 f = new Vector3(fSize*(float)Math.cos(angle),fSize*(float)Math.sin(angle),0);
                    f = f.scl(1f);
                    Vector3 v = p2.getVelocity().add(new Vector3(f.x/p2.getMass(), f.y/p2.getMass(), 0));
                    p2.addPosition(v.x*dt, v.y*dt, 0);
                }
            }
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0);

        for( Planet p : planets){
            p.render(sb);
        }
        planet.render(sb);
        sb.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1,1,0,1);
        sr.circle(100,100,30);
        sr.end();
    }

    @Override
    public void dispose() {

    }

    public float[] distance(Planet datum, Planet p) {
        float a = p.getPosition().x - datum.getPosition().x;
        float b = p.getPosition().y - datum.getPosition().y;
        float c = (float)Math.sqrt( a*a + b*b );
        float[] dist = new float[]{a,b,c};
        return dist;
    }

    public float angle(Planet datum, Planet p){
        float[] d = distance(datum,p);
        float angle = (float)Math.abs(Math.asin(d[1] / d[2]));

        //First quadrant
        if ( d[0] >= 0 && d[1] >= 0)
            angle += 0;
        //Second quadrant
        else if ( d[0] <= 0 && d[1] >= 0)
            angle = (float)Math.PI - angle;
        //Third quadrant
        else if ( d[0] <= 0 && d[1] <= 0)
            angle = (float)Math.PI + angle;
        //Fourth quadrant
        else
            angle = (float)(2*Math.PI) - angle;

        return angle;
    }

}
