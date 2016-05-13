package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-11.
 */
public class Player extends BasicSprite {
    public Player(int x, int y, float mass, Vector3 vel, String textureName) {
        super(x, y, mass, vel, textureName);
    }

    public void draw(SpriteBatch sb){
        sb.draw(texture, pos.x, pos.y);
    }
}
