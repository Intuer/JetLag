package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Class describing a basic renderable sprite.
 *
 * @author Nicklas Hersen
 * @version 22.05.2016
 */
public abstract class BasicSprite extends PhysObject{
    private Texture texture;
    private TextureRegion tex_region;
    protected float angle;
    protected float scale;

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
            setTexture(textureName);
        }

        scale = 1.0f;
    }

    /**
     * Renders the sprite using the specified spritebatch.
     * Throws a runtime exception if no texture is specified.
     *
     * @throws RuntimeException if no texture is specified.
     * @param sb spritebatch to use.
     */

    public void draw(SpriteBatch sb) {
        if (tex_region != null) {
            sb.draw(tex_region, pos.x - texture.getWidth() / 2, pos.y - texture.getHeight() / 2, texture.getWidth() / 2, texture.getHeight() / 2, (float) texture.getWidth(), (float) texture.getHeight(), scale, scale, angle + 180, false);
        } else {
            throw new RuntimeException("No texture specified!");
        }
    }

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

    /**
     * Sets the current rotation of the object, as in the
     * direction the object is facing.
     *
     * @param angle new angle.
     */
    public void setAngle(float angle){
        this.angle = angle;
    }

    /**
     * Returns the current rotation in degrees.
     *
     * @return the current rotation in degrees.
     */
    public float getAngle(){
        return angle;
    }

    /**
     * Returns the current rotation in radians.
     *
     * @return rotation in radians.
     */
    public float getAngleInRad() {
        return getAngle() * (float) (Math.PI / 180);
    }

    /**
     * Sets the scale of this sprite.
     *
     * @param scale new scale.
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * Returns the scale of this sprite.
     *
     * @return the scale of this sprite.
     */
    public float getScale() {
        return scale;
    }

    /**
     * Sets the texture to use when drawing this sprite.
     *
     * @param name name of the texture.
     */
    public void setTexture(String name) {
        texture = new Texture(name);
        tex_region = new TextureRegion(texture);
    }
}
