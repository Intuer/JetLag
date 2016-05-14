package com.JetLag.game.engine.graphics.sprites.grid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-13.
 */
public abstract class GridLine {

    ShapeRenderer sr;
    OrthographicCamera cam;
    float thick;
    Vector3 position;
    float margin;
    float spacing;

    public GridLine(OrthographicCamera cam, float thick, Vector3 position, float margin, float spacing) {
        this.sr = sr;
        this.cam = cam;
        this.thick = thick;
        this.position = position;
        this.margin = margin;
        this.spacing = spacing;
    }

    public abstract void update();

    public abstract void render(ShapeRenderer sr);

    public abstract Vector3 getPosition();


}
