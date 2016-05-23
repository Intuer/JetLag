package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.math.Vector3;

/**
 * Basic Asteroid.
 *
 * @author Nicklas Hersen.
 * @version 05.23.2016
 */
public class Asteroid extends BasicSprite {
    public Asteroid(int x, int y, int mass, Vector3 vel) {
        super(x, y, mass, vel, "asteroid.png");
    }
}
