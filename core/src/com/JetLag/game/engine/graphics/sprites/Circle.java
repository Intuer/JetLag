package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Circle. A PhysObject and a BasicObject.
 */

public class Circle extends BasicShape {
    protected int borderwidth = 10;
    protected int shadewidth = 2;

    public Circle(int x, int y, float mass, Vector3 vel, float[] colour, int radius) {
        super(x, y, mass, vel, colour);
        size =  radius;
    }

    public int getRadius() {
        return size;
    }

    public void render(ShapeRenderer sr) {
        //A black border
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.setColor(0,0,0,1);
        sr.circle(pos.x,pos.y,size);

        //The actual circle base
        sr.setColor(colour[0],colour[1],colour[2],1);
        sr.circle(pos.x,pos.y,size-borderwidth);

        sr.end();
    }
}
