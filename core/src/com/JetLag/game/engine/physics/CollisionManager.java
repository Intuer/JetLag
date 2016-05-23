package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.graphics.sprites.BasicSprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

/**
 * Created by Nicklas Hersén on 2016-05-23.
 */
public class CollisionManager {
    private static CollisionManager manager;

    public static CollisionManager getInstance() {
        if (manager == null) {
            manager = new CollisionManager();
        }

        return manager;
    }

    public void collides(BasicSprite obj1, BasicSprite obj2) {
        Vector3 vec = obj1.getPosition().cpy().sub(obj2.getPosition());

        if (Intersector.overlaps(obj1.getHitBox(), obj2.getHitBox())) {
            vec.nor().scl(obj1.getVelocity().len());
            obj1.setVelocity(vec);
        }
    }
}
