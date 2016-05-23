package com.JetLag.game.engine.graphics;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.sprites.Background;
import com.JetLag.game.engine.graphics.sprites.BasicSprite;
import com.JetLag.game.engine.graphics.sprites.Player;
import com.JetLag.game.engine.physics.CollisionManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic game map.
 *
 * @author Nicklas Hersen.
 * @version 05.13.2016
 */
public class Map {
    protected Background background;

    // Objects that won't be removed (ie. planets).
    protected HashMap<Integer, BasicSprite> objects;
    protected float zoom;
    protected Rectangle bounds;
    protected Integer obj_id = 0;
    protected List<Integer> unused_ids;

    private final float DEFAULT_ZOOM = 5.0f;
    private static final int DEFAULT_WIDTH = 20000;
    private static final int DEFAULT_HEIGHT = 20000;

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
        objects = new HashMap<>();
        unused_ids = new LinkedList<>();
        bounds = new Rectangle(-width/2, -height/2, width, height);
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
        Integer id;

        if (unused_ids.size() > 0) {
            id = unused_ids.get(0);
            unused_ids.remove(0);
        } else {
            id = obj_id++;
        }

        objects.put(id, sprite);
        return id;
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
        if (!objects.containsKey(objID)) return null;

        return objects.get(objID);
    }

    /**
     * Removes the object associated with the specified id.
     * If there is no object associated with the specified id
     * nothing is done.
     *
     * @param objID association id.
     */
    public void remove(Integer objID) {
        if (objects.containsKey(objID)) {
            objects.remove(objID);
            unused_ids.add(objID);
        }
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
        for (BasicSprite s : objects.values()) {
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

    /**
     * Calls the update method on all objects in the map.
     */
    public void update() {
        for (BasicSprite sprite : objects.values()) {
            sprite.update();
        }
    }

    public void updatePlayer(Player player) {
        CollisionManager collider = CollisionManager.getInstance();
        Vector3 pos = player.getPosition();
        Vector3 vel = player.getVelocity();
        float length =  player.getWidth();

        for (BasicSprite s : objects.values()) {
            collider.collides(player, s);
        }

        if (pos.x < bounds.x) {
            pos.x = bounds.x;
            player.setVelocity(0, vel.y, vel.z);
        } else if (pos.x > bounds.x + bounds.width - length) {
            pos.x = bounds.x + bounds.width - length;
            player.setVelocity(0, vel.y, vel.z);
        }

        if (pos.y < bounds.y) {
            pos.y = bounds.y;
            player.setVelocity(vel.x, 0, vel.z);
        } else if (pos.y > bounds.y + bounds.height - length) {
            pos.y = bounds.y + bounds.height - length;
            player.setVelocity(vel.x, 0, vel.z);
        }
    }
}
