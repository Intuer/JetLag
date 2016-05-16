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

    public Player2(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(ShapeRenderer sr) {
        //A black border
        sr.setColor(0,0,0,1);
        sr.rect(pos.x-40, pos.y-40, 80,80);

        //The actual circle base
    }

    public void setVelocity(Vector3 vel){
        this.vel = vel;
    }

    public Vector3 getVelocity(){
        return vel;
    }
}
