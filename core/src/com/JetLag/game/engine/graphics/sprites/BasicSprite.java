package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by nicklas on 5/13/16.
 */
public abstract class BasicSprite extends PhysObject{
    protected Texture texture;

    public BasicSprite(int x, int y, float mass, Vector3 vel, String textureName) {
        super(x, y, mass, vel);
        this.texture = new Texture(textureName);
    }

    public abstract void draw(SpriteBatch sb);
}
