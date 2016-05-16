package com.JetLag.game.engine.graphics;

import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.grid.Grid;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashSet;
import java.util.Set;

/**
 * Basic game map.
 *
 * @author Nicklas Hersen.
 * @version 05.13.2016
 */
public class Map {
    protected Grid grid;

    // Objects that won't be removed (ie. planets).
    protected Set<BasicShape> static_objects;

    public Map(OrthographicCamera cam) {
        grid = new Grid(cam);
        static_objects = new HashSet<>();
    }

    /**
     * Moves the grid according to the players movement.
     */
    public void update() {
        grid.update();
    }

    /**
     * Draw the background.
     *
     * @param sr shapeRenderer used when drawing the map.
     */
    public void draw(ShapeRenderer sr) {
        grid.draw(sr);

        for (BasicShape shape : static_objects) {
            shape.draw(sr);
        }
    }
}
