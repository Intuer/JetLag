package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Basic player class.
 *
 * @author Nicklas Hersen, Jacob Sorme
 * @version 22.05.2016
 */

public class Player extends BasicSprite {
    private float length = 100;
    private Rectangle bounds;

    /**
     * Creates a basic player.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass player mass.
     * @param vel initial player velocity.
     */
    public Player(int x, int y, float mass, Vector3 vel) {
        super(x, y, mass, vel, "ship.png");
        this.bounds = null;
        setScale(0.5f);
    }

    /**
     * Updates the players position and velocity.
     */
    public void update() {
        if (getVelocityLen()> 20) {
            getVelocity().nor().scl(20);
        }

        addPosition(vel);
    }
}
