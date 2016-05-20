package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-14.
 */

public class Player extends BasicShape {

    protected float rotation = 0;
    protected float length = 100;

    public Player(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
    }

    public void draw(ShapeRenderer sr) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1,0,0,1);
        sr.identity();
        sr.rect(pos.x,pos.y,(length/2),(length/2),length,length,1,1,rotation);
        sr.end();
        System.out.println(Math.sin(Math.PI));
    }

    public void setVelocity(Vector3 vel){
        this.vel = vel;
    }

    public Vector3 getVelocity(){
        return vel;
    }

    public float getRotation(){
        return rotation;
    }

    public float getRotationRad(){
        return getRotation()*(float)(Math.PI/180);
    }


    public void setRotation(float rot){
        this.rotation = rot;
    }

    public void addRotation(float a){
        this.rotation = rotation + a;
    }

}
