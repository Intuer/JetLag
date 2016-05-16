package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class Planet extends BasicSprite {
    protected float radius;

    public Planet(int x, int y, float radius, float mass, Vector3 velocity){
        super(x, y, mass, velocity, "planet.png");

        this.radius = radius;
    }

    public void draw(SpriteBatch sp) {
        sp.draw(texture, pos.x - radius, pos.y - radius, 2*radius, 2*radius);
    }
}
