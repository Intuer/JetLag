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
    protected com.badlogic.gdx.math.Circle hitbox;

    public Circle(int x, int y, float mass, Vector3 vel, float[] colour, int radius) {
        super(x, y, mass, vel, colour);
        hitbox = new com.badlogic.gdx.math.Circle(x, y, radius);
        size = radius;
    }

    public float getRadius() {
        return hitbox.radius;
    }

    public void render(ShapeRenderer sr) {
        //A black border
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.setColor(0,0,0,1);
        sr.circle(pos.x, pos.y, hitbox.radius);

        //The actual circle base
        sr.setColor(colour[0],colour[1],colour[2],1);
        sr.circle(pos.x, pos.y, hitbox.radius - borderwidth);

        sr.end();
    }

    public final com.badlogic.gdx.math.Circle getHitbox() {
        return hitbox;
    }
}
