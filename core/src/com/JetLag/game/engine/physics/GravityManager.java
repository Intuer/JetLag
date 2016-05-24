package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.PhysObject;
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

    // This alters the speed of the simulation
    protected float base_timescale;

    protected List<PhysObject> passives;
    protected List<PhysObject> actives;

    enum OBJECT_TYPE_HINT {
        UNKNOWN,
        ACTIVE,
        PASSIVE
    }

    /**
     * Initializes an instance of a gravity manager.
     */
    private GravityManager() {
        passives = new LinkedList<PhysObject>();
        actives = new LinkedList<PhysObject>();

        threshold = 10;
        Gconst = 1f;
        base_timescale = 15;
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

    /**
     * Registers the given object as a passive object.
     *
     * @param obj object to register.
     */
    public void registerPassive(PhysObject obj) {
        if (!contains(obj, OBJECT_TYPE_HINT.PASSIVE)) passives.add(obj);
    }

    /**
     * Registers the given object as an active object.
     *
     * @param obj object to register.
     */
    public void registerActive(PhysObject obj) {
        if (!contains(obj, OBJECT_TYPE_HINT.ACTIVE)) actives.add(obj);

    }

    /**
     * Checks whether the given object is registered.
     *
     * @param obj object to search for.
     */
    public void contains(PhysObject obj) {
        contains(obj, OBJECT_TYPE_HINT.UNKNOWN);
    }

    /**
     * Checks whether the given object is registered.
     *
     * @param obj object to search for.
     * @param hint object type hint.
     * @return whether the object has been registered.
     *
     * @throws IllegalArgumentException if the object hint type is unknown.
     */
    public boolean contains(PhysObject obj, OBJECT_TYPE_HINT hint) throws IllegalArgumentException {
        switch (hint) {
            case PASSIVE:
                return passives.contains(obj) ? true : actives.contains(obj);

            case ACTIVE:
            case UNKNOWN:
                return actives.contains(obj) ? true : passives.contains(obj);
        }

        throw new IllegalArgumentException("Invalid hint type!");
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

            if (length > 200) {

                fdir.nor();
                fdir.scl((float) ((Gconst * obj.getMass() * pObj.getMass()) / (Math.pow((double) length, 1.8))));
                fdir.scl(delta);

                //obj.addVel(fdir);
                obj.getVelocity().add(fdir);
            }
        }
    }

    /**
     *
     * @param delta
     */
    public void update(float delta) {
        for (PhysObject obj : actives) {
            update(obj, delta);
        }
    }
}
