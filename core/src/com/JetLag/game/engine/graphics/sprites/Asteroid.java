package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Nicklas Hersén on 2016-05-22.
 */
public class Asteroid extends BasicSprite {
    public Asteroid(int x, int y, int mass, Vector3 vel) {
        super(x, y, mass, vel, "asteroid.png");
    }
}
