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
     *
     * @param obj
     * @param delta
     */
    public void update(PhysObject obj, float delta) {

        for (PhysObject pObj : passives) {
            Vector3 fdir = new Vector3(pObj.getPosition());
            fdir.sub(obj.getPosition());

            float length = fdir.len();

            fdir.nor();
            fdir.scl( (float) ( (Gconst * obj.getMass() * pObj.getMass()) / ( Math.pow((double)length,1.8)) ));
            fdir.scl(delta);

            obj.getVelocity().add(fdir);
            //obj.setVelocity( obj.getVelocity().scl(0.4f).cpy());

            obj.getPosition().add((obj.getVelocity()).cpy());
        }
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
        float force = getForce(active, passive);

        Vector3 fdir = passive.getPosition().cpy().sub(active.getPosition());
        fdir.nor();
        fdir.scl(fdir);

        active.getVelocity().add(fdir);
        active.setPosition(active.getVelocity());
    }
}
