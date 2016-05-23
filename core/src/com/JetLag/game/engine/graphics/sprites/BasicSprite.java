package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Sprite sprite;

    /**
     * Creates a basic renderable sprite with the specified
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
    }

    /**
     * Renders the sprite using the specified spritebatch.
     * Throws a runtime exception if no texture is specified.
     *
     * @throws RuntimeException if no texture is specified.
     * @param sb spritebatch to use.
     */

    public void draw(SpriteBatch sb) {
        sprite.setPosition(pos.x, pos.y);

        if (sprite != null) {
            sprite.draw(sb);
            //sb.draw(tex_region, pos.x - texture.getWidth() / 2, pos.y - texture.getHeight() / 2, texture.getWidth() / 2, texture.getHeight() / 2, (float) texture.getWidth(), (float) texture.getHeight(), scale, scale, angle + 180, false);
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
        return sprite.getWidth();
    }

    /**
     * Returns the height of the sprite.
     *
     * @return the height of the sprite.
     */
    public float getHeight() {
        return sprite.getHeight();
    }

    /**
     * Sets the current rotation of the object, as in the
     * direction the object is facing.
     *
     * @param angle new angle.
     */
    public void setAngle(float angle){
        sprite.setRotation(angle);
    }

    /**
     * Returns the current rotation in degrees.
     *
     * @return the current rotation in degrees.
     */
    public float getAngle(){
        return sprite.getRotation();
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
     * @param scaleX new scale along the x-axis.
     * @param scaleY new scale along the y-axis.
     */
    public void setScale(float scaleX, float scaleY) {
        sprite.setScale(scaleX, scaleY);
    }

    /**
     * Sets the scale of this sprite along both the x, and y-axis
     *
     * @param scale new scale.
     */
    public void setScale(float scale) {
        sprite.setScale(scale, scale);
    }

    /**
     * Returns the scale of this sprite along the x-axis.
     *
     * @return the scale of this sprite along the x-axis.
     */
    public float getScaleX() {
        return sprite.getScaleX();
    }

    /**
     * Returns the scale of this sprite along the y-axis.
     *
     * @return the scale of this sprite along the y-axis.
     */
    public float getScaleY() {
        return sprite.getScaleY();
    }

    /**
     * Sets the texture to use when drawing this sprite.
     *
     * @param name name of the texture.
     */
    public void setTexture(String name) {
        texture = new Texture(name);
        sprite = new Sprite(texture);
    }
}
