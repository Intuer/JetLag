package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by nicklas on 5/13/16.
 */
public abstract class BasicShape extends PhysObject {
    protected float colour[];

    public BasicShape(int x, int y, float mass, Vector3 vel, float colour[]) {
        super(x, y, mass, vel);
        this.colour = colour;
    }

    public abstract void draw(ShapeRenderer sr);
}
