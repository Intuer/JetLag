package com.JetLag.game.engine.physics;

import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public class GravityManager {
    protected static GravityManager _instance = null;
    protected double threshold;
    protected float last_delta;

    // This alters the total force between the two objects.
    protected float Gconst;

    /**
     * Initializes an instance of a gravity manager.
     */
    private GravityManager() {
        threshold = 10;
        Gconst = 1f;
    }

    /**
     * Returns the current instance of the gravity manager. This method
     * always returns the same instance.
     *
     * @return a gravity manager instance.
     */
    public static GravityManager getInstance() {
        if (_instance == null) _instance = new GravityManager();

        return _instance;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Returns absolute value of the force between the two objects.
     *
     * @param obj1
     * @param obj2
     * @return absolute value of the force between two objects.
     */
    protected float getForce(PhysObject obj1, PhysObject obj2) {
        float dist = obj2.getPosition().cpy().sub(obj1.getPosition()).len();

        return Math.abs((Gconst * obj1.getMass() * obj2.getMass()) / ((float) Math.pow(dist, 1.8)));
    }

    /**
     *
     */
    public void simulate(PhysObject active, PhysObject passive) {
        Vector3 fdir = passive.getPosition().cpy().sub(active.getPosition());

        fdir.scl(last_delta);

        active.getVelocity().add(fdir);
        active.addPosition(active.getVelocity().cpy().scl(last_delta));
    }

    public void setDelta(float dt) {
        this.last_delta = dt;
    }

    public void setOrbitalSpeed(PhysObject active, PhysObject passive) {
        Vector3 fdir  = active.getPosition().cpy().sub(passive.getPosition());
        fdir.crs(0, 0, -1);

        active.setVelocity(fdir);
    }
}
