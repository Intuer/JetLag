package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        moveToBounds();
    }

    /**
     * Ensures that the player stays inside of the specified
     * region. If no region (bound) is specified nothing is done.
     */
    public void moveToBounds() {
        if (bounds == null) return;

        if (pos.x < bounds.x) {
            pos.x = bounds.x;
            setVelocity(0, vel.y, vel.z);
        } else if (pos.x > bounds.x + bounds.width - length) {
            pos.x = bounds.x + bounds.width - length;
            setVelocity(0, vel.y, vel.z);
        }

        if (pos.y < bounds.y) {
            pos.y = bounds.y;
            setVelocity(vel.x, 0, vel.z);
        } else if (pos.y > bounds.y + bounds.height - length) {
            pos.y = bounds.y + bounds.height - length;
            setVelocity(vel.x, 0, vel.z);
        }
    }

    /**
     * Sets the area the player is allowed to be in.
     *
     * @param x1 lower left corner x-coordinate.
     * @param y1 lower left corner y-coordinate.
     * @param width width of the bounding box.
     * @param height height of the bounding box.
     */
    public void setBounds(int x1, int y1, int width, int height) {
        this.bounds = new Rectangle(x1, y1, width, height);
    }
}
