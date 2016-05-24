package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * A basic drawable circle with a hitbox.
 *
 * @author Jacob Sorme, Nicklas Hersen
 * @version 05.24.2016
 */

public class Circle extends BasicShape {
    protected int borderwidth = 10;
    protected int shadewidth = 2;
    protected com.badlogic.gdx.math.Circle hitbox;

    /**
     * Creates a basic drawable circle with the specified properties.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass circle mass.
     * @param vel circle velocity.
     * @param colour colour of the circle.
     * @param radius radius of the circle.
     */
    public Circle(int x, int y, float mass, Vector3 vel, float[] colour, int radius) {
        super(x, y, mass, vel, colour);
        hitbox = new com.badlogic.gdx.math.Circle(x, y, radius);
        size = radius;
    }

    /**
     * Returns the radius of the hitbox of the circle.
     *
     * @return the radius of the hit box.
     */
    public float getRadius() {
        return hitbox.radius;
    }

    /**
     * Renders this circle using the specified ShapeRenderer.
     *
     * @param sr ShapeRenderer to use.
     */
    public void render(ShapeRenderer sr) {
        //A black border
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.setColor(0,0,0,1);
        sr.circle(pos.x, pos.y, hitbox.radius);

        //The actual circle base
        sr.setColor(colour[0],colour[1],colour[2],1);
        sr.circle(pos.x, pos.y, hitbox.radius - borderwidth);

        sr.end();
    }

    /**
     * Returns the hitbox of this circle.
     *
     * @return the hitbox of this circle.
     */
    public final com.badlogic.gdx.math.Circle getHitbox() {
        return hitbox;
    }

    /**
     * Updates the objects position based on its velocity.
     */
    @Override
    public void update() {
        super.update();
        hitbox.setPosition(pos.x, pos.y);
    }
}
