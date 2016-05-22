package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Class describing a basic renderable sprite.
 *
 * @author Nicklas Hersen
 * @version 22.05.2016
 */
public abstract class BasicSprite extends PhysObject{
    protected Texture texture;

    /**
     * Creates a basic renderable sprite with the specifed
     * properties.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     * @param mass object mass.
     * @param vel object velocity.
     * @param textureName name of the texture to use.
     */
    public BasicSprite(int x, int y, float mass, Vector3 vel, String textureName) {
        super(x, y, mass, vel);

        if (textureName != null) {
            this.texture = new Texture(textureName);
        }
    }

    /**
     * Renders the sprite using the specified spritebatch.
     *
     * @param sb spritebatch to use.
     */
    public abstract void draw(SpriteBatch sb);

    /**
     * Returns the width of the sprite.
     *
     * @return the width of the sprite.
     */
    public float getWidth() {
        return texture.getWidth();
    }

    /**
     * Returns the height of the sprite.
     *
     * @return the height of the sprite.
     */
    public float getHeight() {
        return texture.getHeight();
    }
}
