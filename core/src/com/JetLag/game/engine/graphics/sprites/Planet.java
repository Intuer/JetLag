package com.JetLag.game.engine.graphics.sprites;

import com.JetLag.game.engine.PhysObject;
import com.JetLag.game.engine.graphics.RObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class Planet extends PhysObject implements RObject {
    protected float radius;

    public Planet(int x, int y, float radius, float mass, Vector3 velocity){
        super(x, y, mass, velocity, "planet.png");

        this.radius = radius;
    }

    public void update(float dt){
        if ( pos.y > 0 ){
            vel.add(0, -10, 0);
        }

        pos.add(0, vel.y*dt, 0);

        if (pos.y < 0 ){
            vel.y = -vel.y;
            pos.y = 0;
        }
    }

    public void render(SpriteBatch sp) {
        sp.draw(texture, pos.x - radius, (pos.y - radius*(700/500)), pos.x + radius, (pos.y + radius*700/500));
    }
}
