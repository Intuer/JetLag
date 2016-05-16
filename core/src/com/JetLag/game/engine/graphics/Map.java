package com.JetLag.game.engine.graphics;

import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.grid.Grid;
import com.JetLag.game.engine.physics.System;
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
    protected Set<System> systems;

    public Map(OrthographicCamera cam) {
        grid = new Grid(cam);
        systems = new HashSet<>();
    }

    public void addSystem(System s) {
        systems.add(s);
    }

    /**
     * Moves the grid according to the players movement.
     */
    public void update() {
        grid.update();

        for (System s : systems) {
            s.update();
        }
    }

    /**
     *
     */
    public void drawGrid(ShapeRenderer sr) {
        grid.draw(sr);
    }

    /**
     * Draw the background.
     *
     * @param sr shapeRenderer used when drawing the map.
     */
    public void draw(ShapeRenderer sr) {
        for (System shape : systems) {
            shape.draw(sr);
        }
    }
}
