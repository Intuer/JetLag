package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicklas on 5/16/16.
 */
public class System extends PhysObject {
    public Set<PhysObject> objects;

    public System(int x, int y) {
        super(x, y, 0, new Vector3(0, 0, 0));
        objects = new HashSet<>();
    }

    @Override
    public float getMass() {
        float mass = 0;

        for (PhysObject o : objects) {
            mass += o.getMass();
        }

        return mass;
    }

    public void addObject() {

    }
}
