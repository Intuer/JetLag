package com.JetLag.game.sprites;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class Planet extends PhysObject {
    public Planet(int x, int y, float mass, Vector3 velocity){
        super(x, y, mass, velocity, "planet.png");
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
}
