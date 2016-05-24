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
public class Player extends BasicShape {
    protected float rotate = 0;
    private float length = 100;
    private Rectangle bounds;
    private Polygon shape;
    private Polygon jet;
    private boolean jetbeam = false;

    /**
     * Creates a player.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass mass of the player.
     * @param vel initial velocity.
     * @param colour player colour.
     */
    public Player(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
        this.bounds = null;
        this.shape = new Polygon(new float[]{-50,50,60,0,-50,-50,-50,50});
        this.jet = new Polygon(new float[]{-50,30,-50,-30,-80,0,-50,30});
        jet.setPosition(pos.x,pos.y);
        shape.setPosition(pos.x,pos.y);
    }

    /**
     * Renders the player.
     *
     * @param sr shapeRenderer to render the player with.
     */
    public void render(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_ALIASED_LINE_WIDTH_RANGE);
        Gdx.gl.glLineWidth(2);
        sr.begin(ShapeRenderer.ShapeType.Line);

        sr.setColor(0,0,0,1);
        shape.setRotation(rotate);
        shape.setPosition(pos.x, pos.y);
        sr.polygon(shape.getTransformedVertices());

        if(this.jetbeam) {
            sr.setColor(1, 0, 0, 1);
            jet.setRotation(rotate);
            jet.setPosition(pos.x, pos.y);
            sr.polygon(jet.getTransformedVertices());
        }

        sr.end();
        Gdx.gl.glDisable(GL20.GL_ALIASED_LINE_WIDTH_RANGE);

        this.jetbeam = false;
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

    /**
     * Sets whether the jet beam should be drawn.
     *
     * @param jet draw jet beam?
     */
    public void setJet(boolean jet){
        this.jetbeam = true;
    }
}
