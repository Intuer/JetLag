package com.JetLag.game.sprites;

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
     * Returns the velocity of the object.
     *
     * @return the velocity of the object.
     */
    public float getVelocity() {
        return vel.len();
    }

    /**
     * Returns the objects velocity vector.
     *
     * @return the objects velocity vector.
     */
    public Vector3 getVelocityVector() {
        return vel;
    }

    /**
     * Sets the objects velocity vector.
     *
     * @param vec new velocity vector
     */
    public void setVelocityVector(Vector3 vec) {
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
}
