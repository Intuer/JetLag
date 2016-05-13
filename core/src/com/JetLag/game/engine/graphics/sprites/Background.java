package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Movable background.
 *
 * @author Nicklas Hersen
 * @version 05.13.2016
 */
public class Background {
    protected Texture bgTexture;
    protected TextureRegion region;

    /**
     * Creates a background with the standard space texture.
     */
    public Background() {
        this("space-background.png");
    }

    /**
     * Creates a background from the specified texture.
     *
     * @param name texture name.
     */
    public Background(String name) {
        bgTexture = new Texture(name);
    }

    /**
     * Creates a background form the specified texture and render the specified region.
     *
     * @param name texture name.
     * @param regionWidth region width.
     * @param regionHeight region height.
     */
    public Background(String name, int regionWidth, int regionHeight) {
        this(name);
        region = new TextureRegion(bgTexture, regionWidth, regionHeight);
        bgTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    /**
     * Sets the position of the region.
     *
     * @param x position along the x-axis.
     * @param y position along the y-axis.
     */
    public void setRegionPos(int x, int y) {
        region.setRegion(x, y, region.getRegionWidth(), region.getRegionHeight());

    }

    /**
     * Sets the region size.
     *
     * @param width new region width.
     * @param height new region height.
     */
    public void setRegionSize(int width, int height) {
        if (width <= 0) throw new IllegalArgumentException("Illegal width");
        if (height <= 0) throw new IllegalArgumentException("Illegal height");

        region.setRegion(region.getRegionX(), region.getRegionY(), width, height);
    }

    /**
     * Moves the region by a specified amount (in pixels).
     *
     * @param x amount along the x-axis.
     * @param y amount along the y-axis.
     */
    public void moveRegion(int x, int y) {
        region.setRegion(region.getRegionX() + x, region.getRegionY() + y, region.getRegionWidth(), region.getRegionHeight());
    }

    /**
     * Returns the texture region to draw,
     *
     * @return the texture region that should be drawn.
     */
    public TextureRegion getRegion() { return region; }
}

