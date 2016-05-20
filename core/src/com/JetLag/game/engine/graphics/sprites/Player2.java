package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-14.
 */

public class Player2 extends BasicShape {

    protected float rotate = 0;
    private float length = 100;
    private Rectangle bounds;

    public Player2(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
        this.bounds = null;
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

    /**
     * Moves the player into the bounding box.
     * If no bounding box is specified nothing is done.
     */
    public void moveToBounds() {
        if (bounds == null) return;

        if (pos.x < bounds.x) {
            pos.x = bounds.x;
            setVelocity(0, vel.y, vel.z);
        } else if (pos.x > bounds.x + bounds.width - length) {
            pos.x = bounds.x + bounds.width - length;
            setVelocity(0, vel.y, vel.z);
        }

        if (pos.y < bounds.y) {
            pos.y = bounds.y;
            setVelocity(vel.x, 0, vel.z);
        } else if (pos.y > bounds.y + bounds.height - length) {
            pos.y = bounds.y + bounds.height - length;
            setVelocity(vel.x, 0, vel.z);
        }
    }

    /**
     * Sets the area the player is allowed to be in.
     *
     * @param x1 lower left corner x-coordinate.
     * @param y1 lower left corner y-coordinate.
     * @param width width of the bounding box.
     * @param height height of the bounding box.
     */
    public void setBounds(int x1, int y1, int width, int height) {
        this.bounds = new Rectangle(x1, y1, width, height);
    }
}
