package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

/**
 * Basic player class.
 *
 * @author Nicklas Hersen, Jacob Sorme
 * @version 05.20.2016
 */
public class Player2 extends BasicShape {
    protected float rotate = 0;
    private Polygon shape;

    /**
     * Creates a player.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass mass of the player.
     * @param vel initial velocity.
     * @param colour player colour.
     */
    public Player2(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
        this.shape = new Polygon(new float[]{-50,50,50,0,-50,-50,-50,50});
        shape.setPosition(pos.x,pos.y);
        size = (int) shape.getBoundingRectangle().width;
    }

    /**
     * Renders the player.
     *
     * @param sr shapeRenderer to render the player with.
     */
    public void render(ShapeRenderer sr) {
        //A black border
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.setColor(0,0,0,1);
//        sr.identity();
//        sr.rect(pos.x,pos.y,(length/2),(length/2),length,length,1,1,rotate);
//        sr.end();

        Gdx.gl.glEnable(GL20.GL_ALIASED_LINE_WIDTH_RANGE);
        Gdx.gl.glLineWidth(2);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(0,0,0,1);
        shape.setRotation(rotate);
        shape.setPosition(pos.x, pos.y);
        sr.polygon(shape.getTransformedVertices());
        sr.end();
        Gdx.gl.glDisable(GL20.GL_ALIASED_LINE_WIDTH_RANGE);
    }

    /**
     * Set player rotation. The rotation should
     * be specified in degrees.
     *
     * @param rot new rotation (in degrees).
     */
    public void setRotate(float rot){
        this.rotate = rot;
    }

    /**
     * Returns the rotation of the player.
     *
     * @return the rotation of the player.
     */
    public float getRotate(){
        return rotate;
    }

    /**
     * Returns the rotation in radians.
     *
     * @return  rotation in radians.
     */
    public float getRotateRad(){
        return getRotate()*(float)(Math.PI/180);
    }
}
