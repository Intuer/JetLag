package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nicklas on 5/16/16.
 */
public class System extends PhysObject {
    protected Set<PhysObject> actives;
    protected Set<PhysObject> passives;
    protected float system_len;

    public enum TYPE {
        PASSIVE,
        ACTIVE
    }

    public System(int x, int y) {
        super(x, y, 0, new Vector3(0, 0, 0));
        actives = new HashSet<>();
        passives = new HashSet<>();
    }

    public void addObject(PhysObject obj, TYPE t) {
        if (obj instanceof System) throw new IllegalArgumentException("System objects currently not allowed");

        switch(t) {
            case PASSIVE:
                passives.add(obj);
                break;

            case ACTIVE:
                actives.add(obj);
                break;

            default:
                throw new RuntimeException("Inavlid type (t)");
        }
    }

    public void update() {
        GravityManager g = GravityManager.getInstance();

        for (PhysObject active : actives) {
            for (PhysObject passive : passives) {
                g.simulate(active, passive);
            }
        }
    }

    public void draw(ShapeRenderer sr) {
        for (PhysObject p : passives) {
            if (p instanceof BasicShape) {
                ((BasicShape) p).draw(sr);
            }
        }

        for (PhysObject p : actives) {
            if (p instanceof BasicShape) {
                ((BasicShape) p).draw(sr);
            }
        }
    }
}
