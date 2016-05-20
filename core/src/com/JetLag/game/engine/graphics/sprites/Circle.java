package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Circle. A PhysObject and a BasicObject.
 */

public class Circle extends BasicShape {

    protected int radius;
    protected int borderwidth = 10;
    protected int shadewidth = 2;

    public Circle(int x, int y, float mass, Vector3 vel, float[] colour, int radius) {
        super(x, y, mass, vel, colour);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(ShapeRenderer sr) {
        //A black border
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,0,0,1);
        sr.circle(pos.x,pos.y,radius);

        //The actual circle base
        sr.setColor(colour[0],colour[1],colour[2],1);
        sr.circle(pos.x,pos.y,radius-borderwidth);
        sr.end();
    }
}
