package com.JetLag.game.engine.graphics;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.sprites.Background;
import com.JetLag.game.engine.graphics.sprites.BasicSprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

/**
 * Basic game map.
 *
 * @author Nicklas Hersen.
 * @version 05.13.2016
 */
public class Map {
    protected Background background;

    // Objects that won't be removed (ie. planets).
    protected HashMap<Integer, BasicSprite> static_objects;
    protected float zoom;
    protected Rectangle bounds;
    protected Integer obj_id = 0;

    private final float DEFAULT_ZOOM = 5.0f;
    private static final int DEFAULT_WIDTH = 2048;
    private static final int DEFAULT_HEIGHT = 2048;

    /**
     * Creates a map with a specified background and region size.
     * The region size specifies the part of the background image
     * should be rendered.
     *
     * @param bgName background name.
     * @param width map width.
     * @param height map height.
     * @param rwidth background image region width.
     * @param rheight background image region height.
     */
    public Map(String bgName, int width, int height, int rwidth, int rheight) {
        background = new Background(bgName, rwidth, rheight);
        static_objects = new HashMap<>();
        bounds = new Rectangle(-width/2, -height/2, width/2, height/2);
        zoom = DEFAULT_ZOOM;
    }

    /**
     * Creates a map with the standard background and standard region size.
     */
    public Map() {
        this("space-background.png", DEFAULT_WIDTH, DEFAULT_HEIGHT, 512, 512);
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
     * Adds an object to this map.
     *
     * @param sprite sprite name.
     * @return an ID associated with the object.
     */
    public Integer add(BasicSprite sprite) {
        static_objects.put(obj_id, sprite);
        return obj_id++;
    }

    /**
     * Returns the object associated with the specified id.
     * If there is no object associated with the specified id,
     * null is returned.
     *
     * @param objID object id.
     * @return object associated with the specified id.
     */
    public BasicSprite get(Integer objID) {
        if (!static_objects.containsKey(objID)) return null;

        return static_objects.get(objID);
    }

    /**
     * Draw the background.
     *
     * @param sb spritebatch renderer to use.
     */
    public void drawBackground(SpriteBatch sb, PhysObject player) {
        sb.begin();
        sb.draw(background.getRegion(), 0, 0, JetLag.WIDTH * zoom, JetLag.HEIGHT * zoom);
        sb.end();
    }

    /**
     * Draw all the objects currently in the map using
     * the specified spritebatch.
     *
     * @param sb spritebatch to use.
     */
    public void drawObjects(SpriteBatch sb) {
        for (BasicSprite s : static_objects.values()) {
            s.draw(sb);
        }
    }

    /**
     * Get map zoom.
     *
     * @return map zoom.
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * Set map zoom.
     *
     * @param zoom new zoom amount.
     */
    public void setZoom(float zoom) {
        this.zoom  = zoom;
    }

    /**
     * Returns the map domain.
     *
     * @return the map domain.
     */
    public final Rectangle getMapRegion() {
        return bounds;
    }
}
