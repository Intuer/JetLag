package com.JetLag.game.engine;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Basic class describing objects with some physical properties.
 *
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public abstract class PhysObject extends BasicObject {
    protected float mass;
    protected Vector3 vel;

    /**
     * Initializes the object with some basic properties.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass object mass.
     * @param vel object velocity.
     */
    public PhysObject(int x, int y, float mass, Vector3 vel, String textureName) {
        super(x, y, textureName);

        this.mass = mass;
        this.vel = vel;
    }

    /**
     * Initializes the object with some basic properties.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass object mass.
     * @param vel object velocity.
     */
    public PhysObject(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, colour);
        this.mass = mass;
        this.vel = vel;
        this.colour = colour;
    }

    /**
     * Returns the velocity of the object.
     *
     * @return the velocity of the object.
     */
    public Vector3 getVelocity() {
        return vel;
    }

    /**
     * Returns the objects velocity vector.
     *
     * @return the objects velocity vector.
     */
    public float getVelocityLen() {
        return vel.len();
    }

    /**
     * Sets the objects velocity vector.
     *
     * @param vec new velocity vector
     */
    public void setVelocity(Vector3 vec) {
        this.vel = vec;
    }

    /**
     * Adds a vector onto the objects current velocity vector.
     *
     * @param vec vector to add.
     */
    public void addVel(Vector3 vec) {
        vel.add(vec);
    }

    /**
     * Returns the mass of the object.
     *
     * @return objects mass.
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets the mass of the object.
     *
     * @param mass new objects mass.
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    public void render(ShapeRenderer sr){

    }
}