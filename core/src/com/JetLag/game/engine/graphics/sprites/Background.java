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

    public void setRegionPos(int x, int y) {
        region.setRegionX(x);
        region.setRegionX(y);

    }

    public void setRegionSize(int width, int height) {
        if (width <= 0) throw new IllegalArgumentException("Illegal width");
        if (height <= 0) throw new IllegalArgumentException("Illegal height");

        region.setRegionHeight(width);
        region.setRegionWidth(height);
    }

    public void moveRegion(int x, int y) {
        region.setRegion(region.getRegionX() + x, region.getRegionY() + y, region.getRegionWidth(), region.getRegionHeight());
    }

    public TextureRegion getRegion() { return region; }
}

