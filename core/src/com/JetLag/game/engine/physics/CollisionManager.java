package com.JetLag.game.engine.physics;

import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.Circle;
import com.JetLag.game.engine.graphics.sprites.Player;
import com.badlogic.gdx.math.Intersector;

/**
 * Class for checking whether two circles, players or one circle and one player
 * collides.
 *
 * @author Nicklas Hersen
 * @version 05.24.2016
 */
public class CollisionManager {
    /**
     * Check whether the two circles overlaps.
     *
     * @param obj1 circle 1.
     * @param obj2 circle 2.
     * @return Whether the two circles overlap.
     */
    private boolean collides(Circle obj1, Circle obj2) {
        return Intersector.overlaps(obj1.getHitbox(), obj2.getHitbox());
    }

    /**
     * Checks whether the ciecle and the player overlaps.
     *
     * @param obj1 circle.
     * @param obj2 player.
     * @return Whether the circle and the player overlaps.
     */
    private boolean collides(Circle obj1, Player obj2) {
        return Intersector.overlaps(obj1.getHitbox(), obj2.getHitbox());
    }

    /**
     * Checks whether the ciecle and the player overlaps.
     *
     * @param obj1 circle.
     * @param obj2 player.
     * @return Whether the circle and the player overlaps.
     */
    private boolean collides(Player obj1, Circle obj2) {
        return collides(obj2, obj1);
    }

    /**
     * Checks whether the two basic objects collides. The objects must
     * be either Circles or Players, or one of each, otherwise a RuntimeException
     * will be thrown.
     *
     * @throw RuntimeException if the objects isn't an instance of either Circle or Player.
     * @param obj1 object 1.
     * @param obj2 object 2.
     * @return Whether the two objects overlaps.
     */
    public boolean collides(BasicShape obj1, BasicShape obj2) {
        if (obj1 instanceof Player) {
            Player p = (Player) obj1;

            if (obj2 instanceof Player) {
                return collides(p, (Player) obj2);
            } else if (obj2 instanceof Circle) {
                return collides(p, (Circle) obj2);
            }

        } else if (obj1 instanceof Circle) {
            Circle c = (Circle) obj1;

            if (obj2 instanceof Player) {
                return collides(c, (Player) obj2);
            } else if (obj2 instanceof Circle) {
                return collides(c, (Circle) obj2);
            }
        }

        throw new RuntimeException("Type not supported!");
    }
}
