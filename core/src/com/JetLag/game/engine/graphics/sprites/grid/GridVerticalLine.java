package com.JetLag.game.engine.graphics.sprites.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-13.
 */
public class GridVerticalLine extends GridLine {

    public GridVerticalLine(OrthographicCamera cam, float thick, Vector3 position, float margin, float spacing) {
        super(cam, thick, position, margin, spacing);
    }

    @Override
    public void update(){
        this.position.y = (cam.position.y - cam.viewportHeight/2) - margin*spacing;
    }

    @Override
    public void render(ShapeRenderer sr) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,0,0,0.05f);

        sr.rect(position.x, position.y,thick, cam.viewportHeight + (2*spacing*margin));
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public Vector3 getPosition() {
        return this.position;
    }

}
