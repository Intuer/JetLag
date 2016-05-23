package com.JetLag.game.engine.utils;

import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.Planet;
import com.JetLag.game.engine.physics.GravityManager;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Basic map utilities such as level generation.
 *
 * @author Nicklas Hersen
 * @version 05.22.2016
 */
public class MapUtils {
    private Map map;
    private Random rand;
    private int base_radius;
    private int base_mass;
    private int radius_rand;
    private int mass_rand;

    private final float RANDOM_AMOUNT = 0.05f;

    /**
     * Initializes a MapUtils instance with the specified properties.
     *
     * @param map map to work on.
     * @param brad base radius.
     * @param bmass base mass.
     */
    public MapUtils(Map map, int brad, int bmass) {
        this.map = map;
        rand = new Random();
        base_radius = brad;
        base_mass = bmass;

        setRandomRadiusAmount(RANDOM_AMOUNT);
        setRandomMassAmount(RANDOM_AMOUNT);
    }

    public Integer[] spawnRandomPlanets(int number) {
        Rectangle domain = map.getMapRegion();
        Integer ids[] = new Integer[number];
        GravityManager g = GravityManager.getInstance();

        for (int i = 0; i < number; i++) {
            int x = (int) domain.x + rand.nextInt((int) domain.width);
            int y = (int) domain.y + rand.nextInt((int) domain.height);

            Planet planet = new Planet(x, y, base_radius + rand.nextInt(radius_rand), base_mass + rand.nextInt(mass_rand));

            ids[i] = map.add(planet);
            g.registerPassive(planet);
        }

        return ids;
    }

    public void setRandomRadiusAmount(float amount) { this.radius_rand = (int) (base_radius * amount); }

    public void setRandomMassAmount(float amount) { this.mass_rand = (int) (base_mass * amount); }
}
