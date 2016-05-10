package com.JetLag.game.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Basic object class.
 *
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public abstract class BasicObject {
    protected Vector3 pos;
    protected Texture texture;
    protected float[] colour;

    /**
     * Initializes the object with some basic parameters.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param textureName name of the texture to use.
     */
    public BasicObject(int x, int y, String textureName) {
        pos = new Vector3(x, y, 0);
        this.texture = new Texture(textureName);
    }

    /**
     * Initializes the object with some basic parameters.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     */
    public BasicObject(int x, int y, float[] colour) {
        pos = new Vector3(x, y, 0);
        this.texture = null;
        this.colour = colour;
    }

    /**
     * Returns the position of this object.
     *
     * @return the position of this object.
     */
    public Vector3 getPosition() { return pos; }

    /**
     * Moves this object along the specified axis.
     *
     * @param x moves the object along the x-axis.
     * @param y moves the object along the y-axis.
     * @param z moves the object along the z-axis.
     */
    public void addPosition(float x, float y, float z) { pos.add(x, y, z); }

    /**
     * Returns the texture of this object.
     *
     * @return the texture of this object.
     */
    public Texture getTexture() { return texture; }
}
