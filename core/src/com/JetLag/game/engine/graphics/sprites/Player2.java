package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-14.
 */

public class Player2 extends BasicShape {

    protected int radius;
    protected int borderwidth = 10;
    protected int shadewidth = 2;
    protected float rotate = 0;
    private float length = 100;

    public Player2(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void render(ShapeRenderer sr) {
        //A black border
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,0,0,1);
        sr.identity();
        sr.rect(pos.x,pos.y,(length/2),(length/2),length,length,1,1,rotate);
        sr.end();
    }

    public void setRotate(float rot){
        this.rotate = rot;
    }

    public float getRotate(){
        return rotate;
    }

    public float getRotateRad(){
        return getRotate()*(float)(Math.PI/180);
    }

    public void setVelocity(Vector3 vel){
        this.vel = vel;
    }

    public Vector3 getVelocity(){
        return vel;
    }
}
