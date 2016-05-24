package com.JetLag.game.engine.graphics;

import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.Player;
import com.JetLag.game.engine.graphics.sprites.grid.Grid;
import com.JetLag.game.engine.physics.CollisionManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;
import java.util.*;

/**
 * Basic game map.
 *
 * @author Nicklas Hersen.
 * @version 05.13.2016
 */
public class Map {
    private Grid grid;
    private Rectangle bounds;

    private HashMap<Integer, BasicShape> objects;
    private List<Integer> unused_ids;
    private Set<BasicShape> bound_objects;
    private Integer object_id;

    private CollisionManager cmanager;
    /**
     * Creates a map with a specified background and region size.
     * The region size specifies the part of the background image
     * should be rendered.
     *
     * @param cam projection camera.
     * @param width map width.
     * @param height map height.
     */
    public Map(OrthographicCamera cam, int width, int height){
        grid = new Grid(cam);
        objects = new HashMap<>();
        unused_ids = new LinkedList<>();
        bound_objects = new HashSet<>();

        this.bounds = new Rectangle(-width/2, -height/2, width, height);
        object_id = 0;
        cmanager = new CollisionManager();
    }

    /**
     * Updates various components stored in the map.
     *
     * @param dt time since last update.
     */
    public void update(float dt) {
        grid.update();

        if (bounds != null) {
            for (BasicShape shape : bound_objects) {
                enforceBounds(shape);
            }
        }

        for (int i = 1; i < objects.size(); i++) {
            objects.get(i).update();

            if (objects.get(i) instanceof Player) {
                for (int j = 0; j < i; j++) {
                    if (cmanager.collides(objects.get(i), objects.get(j))) {
                        BasicShape obj1 = objects.get(i);
                        BasicShape obj2 = objects.get(j);

                        Vector3 dir = obj1.getPosition().cpy();
                        dir.sub(obj2.getPosition());
                        dir.nor();

                        obj1.setVelocity(dir.cpy().scl(obj1.getVelocity().len()));
                        obj2.setVelocity(dir.cpy().scl(obj2.getVelocity().len()).scl(-1));
                    }
                }
            }
        }
    }

    /**
     * Enforces map bounds on the specified object.
     *
     * @param obj object to enforce bounds on.
     */
    private void enforceBounds(BasicShape obj) {
        Vector3 pos = obj.getPosition();
        Vector3 vel = obj.getVelocity();
        float length = obj.getSize();

        if (pos.x < bounds.x) {
            pos.x = bounds.x;
            obj.setVelocity(0, vel.y, vel.z);
        } else if (pos.x > bounds.x + bounds.width - length) {
            pos.x = bounds.x + bounds.width - length;
            obj.setVelocity(0, vel.y, vel.z);
        }

        if (pos.y < bounds.y) {
            pos.y = bounds.y;
            obj.setVelocity(vel.x, 0, vel.z);
        } else if (pos.y > bounds.y + bounds.height - length) {
            pos.y = bounds.y + bounds.height - length;
            obj.setVelocity(vel.x, 0, vel.z);
        }
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

        return id;
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

    /**
     * Renders all objects contained in this map.
     *
     * @param sr shape renderer to use.
     */
    public void render(ShapeRenderer sr) {
        grid.render(sr);

        for (BasicShape shape : objects.values()) {
            shape.render(sr);
        }
    }

    /**
     * Forces the object to stay within map bounds.
     *
     * @param id id associated with the object.
     */
    public void setBound(Integer id) {
        if (objects.containsKey(id)) {
            bound_objects.add(get(id));
        }
    }

    /**
     * Disables map bound tests for this object.
     * If the specified id isn't associated with any
     * object in the map, nothing is done.
     *
     * @param id id associated with te object.
     */
    public void unsetBound(Integer id) {
        if (bound_objects.contains(id)) {
            bound_objects.remove(id);
        }
    }
}

