package com.JetLag.game.engine.graphics;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.BasicObject;
import com.JetLag.game.engine.graphics.sprites.Background;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Set;

/**
 * Basic game map.
 *
 * @author Nicklas Hersen.
 * @version 05.13.2016
 */
public class Map {
    protected Background background;

    // Objects that won't be removed (ie. planets).
    protected Set<BasicObject> static_objects;

    /**
     * Creates a map with a specified background and region size.
     * The region size specifies the part of the background image
     * should be rendered.
     *
     * @param bgName background name.
     * @param rwidth region width.
     * @param rheight region height.
     */
    public Map(String bgName, int rwidth, int rheight) {
        background = new Background(bgName, rwidth, rheight);
    }

    /**
     * Creates a map with the standard background and standard region size.
     */
    public Map() {
        this("space-background.png", 512, 512);
    }

    /**
     * Moves the background region by a specified amount (in pixels).
     *
     * @param x amount along the x-axis.
     * @param y amount along the y-axis.
     */
    public void moveBackground(int x, int y) {
        background.moveRegion(x, y);
    }

    /**
     * Draw the background.
     *
     * @param sb spritebatch renderer to use.
     */
    public void drawBackground(SpriteBatch sb) {
        sb.begin();
        sb.draw(background.getRegion(), 0, 0, JetLag.WIDTH, JetLag.HEIGHT);
        sb.end();
    }
}
