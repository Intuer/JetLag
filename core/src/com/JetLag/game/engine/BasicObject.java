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

    /**
     * Initializes the object with some basic parameters.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     */
    public BasicObject(int x, int y) {
        pos = new Vector3(x, y, 0);
    }

    /**
     * Initializes the object with some basic parameters.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     */
    public BasicObject(int x, int y, float[] colour) {
        pos = new Vector3(x, y, 0);
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
     * Moves this object along the specified vector.
     *
     * @param vec vector to move the object along.
     */
    public void addPosition(Vector3 vec) { this.pos.add(vec); }

    /**
     * Sets the position of this object.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param z position along the z-axis.
     */
    public void setPosition(float x, float y, float z) { pos.set(x, y, z); }
}
