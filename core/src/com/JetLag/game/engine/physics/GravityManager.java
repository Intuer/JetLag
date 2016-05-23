package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;

/**
 * Handles the gravity in the solar system.
 *
 * There are two types of physics objects, active and passive.
 * A passive object attracts active objects, while active objects
 * gets attracted to passive objects. An object can be both passive
 * and active at the same time.
 *
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public class GravityManager {
    protected static GravityManager _instance = null;

    // This alters the total force between the two objects.
    protected float Gconst;

    // This alters the speed of the simulation
    protected float base_timescale;

    protected List<PhysObject> passives;
    protected List<PhysObject> actives;

    /**
     * Object groups.
     */
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

        Gconst = 3f;
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
        passives.add(obj);
    }

    /**
     * Registers the given object as an active object.
     *
     * @param obj object to register.
     */
    public void registerActive(PhysObject obj) {
        actives.add(obj);
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

    /**
     * Simulates gravity for the specified object.
     *
     * @param obj object to simulate.
     * @param delta time delta.
     */
    public void update(PhysObject obj, float delta) {

        for (PhysObject pObj : passives) {
            if (pObj == obj) continue;

            Vector3 fdir = new Vector3(pObj.getPosition());
            fdir.sub(obj.getPosition());

            fdir.nor();
            fdir.scl(getAttractionForce(obj, pObj));
            fdir.scl(delta);

            obj.addVel(fdir);

            obj.addPosition(obj.getVelocity());
        }
    }

    /**
     * Returns the attraction force between two objects.
     *
     * @param obj1 object 1.
     * @param obj2 object 2.
     * @return the attraction force between the two objects.
     */
    private float getAttractionForce(PhysObject obj1, PhysObject obj2) {
        Vector3 dir = obj1.getPosition().cpy().sub(obj2.getPosition());
        float force = Gconst / (dir.len2());
        force *= obj1.getMass()  * obj2.getMass();

        return force;
    }

    /**
     * Simulates gravity for all the specified active objects.
     *
     * @param delta time delta.
     */
    public void update(float delta) {
        for (PhysObject obj : actives) {
            update(obj, delta);
        }
    }
}
