package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-11.
 */
public class Player extends PhysObject{
    public Player(int x, int y, float mass, Vector3 vel, String textureName) {
        super(x, y, mass, vel, textureName);
    }

    public Player(int x, int y, float mass, Vector3 vel, float[] colour) {
        super(x, y, mass, vel, colour);
    }

    @Override
    public void render(ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.4f,0.4f,0.4f,1);
        sr.rect(pos.x,pos.y,80,80);
        sr.end();
    }

}
