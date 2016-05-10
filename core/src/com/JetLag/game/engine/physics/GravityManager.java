package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.PhysObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public class GravityManager {
    protected GravityManager _instance = null;
    protected float threshold;

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
    }

    /**
     * Returns the current instance of the gravity manager. This method
     * always returns the same instance.
     *
     * @return a gravity manager instance.
     */
    public GravityManager getInstance() {
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

    /**
     *
     * @param obj
     * @param delta
     */
    public void update(PhysObject obj, long delta) {

    }

    /**
     *
     * @param delta
     */
    public void update(long delta) {

    }
}
