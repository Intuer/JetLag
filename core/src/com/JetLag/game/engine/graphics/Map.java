package com.JetLag.game.engine.graphics;

import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.grid.Grid;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.awt.Rectangle;
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
    protected Grid grid;
    protected Rectangle bounds;

    protected HashMap<Integer, BasicShape> objects;
    private Integer object_id;
    private List<Integer> unused_ids;

    /**
     * Creates a map with a specified background and region size.
     * The region size specifies the part of the background image
     * should be rendered.
     *
     * @param cam projection camera.
     */
    public Map(OrthographicCamera cam, Rectangle bounds){
        grid = new Grid(cam);
        objects = new HashMap<>();
        unused_ids = new LinkedList<>();

        this.bounds = bounds;
        object_id = -1;
    }

    /**
     * Updates various components stored in the map.
     *
     * @param dt time since last update.
     */
    public void update(float dt) {
        grid.update();
    }

    /**
     * Adds an object to the map.
     *
     * @param shape object to add.
     * @return an integer associated with the object.
     */
    public Integer add(BasicShape shape) {
        Integer id;

        if (unused_ids.size() > 0) {
            id = unused_ids.get(0);
            unused_ids.remove(0);
        } else {
            id = object_id++;
        }

        objects.put(id, shape);

        return object_id;
    }

    /**
     * Returns the object associated with the specified id.
     * @param id object id.
     * @return the object associated with the id.
     */
    public BasicShape get(Integer id) {
        return objects.get(id);
    }

    /**
     * Removes an object from the map.
     *
     * @param id of the associated object.
     */
    public void remove(Integer id) {
        if (!objects.containsKey(id)) {
            throw new RuntimeException("There is no object associated with the specified key!");
        }

        unused_ids.add(id);
        objects.remove(id);
    }
}

