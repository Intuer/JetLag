package com.JetLag.game.engine.utils;

import com.JetLag.game.JetLag;
import com.JetLag.game.engine.graphics.Map;
import com.JetLag.game.engine.graphics.sprites.Asteroid;
import com.JetLag.game.engine.graphics.sprites.BasicShape;
import com.JetLag.game.engine.graphics.sprites.BasicSprite;
import com.JetLag.game.engine.graphics.sprites.Player;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nicklas Hersén on 2016-05-23.
 */
public class AsteroidUtils {
    private int spawn_rate;
    private int limit;
    private List<Integer> asteroids;

    private int base_speed;
    private int rand_speed;
    private int margin;
    private int base_mass;
    private int rand_mass;

    private Map map;
    private Player player;
    private Random random;

    private final int DEFAULT_MARGIN = 100;
    private final int DEFAULT_LIMIT = 64;
    private final int DEFAULT_RATE = 1;

    /**
     * Creates a basic AsteroidUtil.
     *
     * @param map current map.
     * @param player player to spawn asteroids around.
     */
    public AsteroidUtils(Map map, Player player, int base_speed, int rand_speed, int base_mass, int rand_mass) {
        random = new Random();
        asteroids = new LinkedList<>();
        this.map = map;
        this.player = player;
        this.base_speed = base_speed;
        this.rand_speed = rand_speed;
        this.base_mass = base_mass;
        this.rand_mass = rand_mass;
        spawn_rate = DEFAULT_RATE;
        limit = DEFAULT_LIMIT;
        margin = DEFAULT_MARGIN;
    }

    /**
     * Returns the number of asteroids that spawn on each spawn
     * call.
     *
     * @return the number of asteroids spawned on each spawn call.
     */
    public int getSpwanRate() {
        return spawn_rate;
    }

    /**
     * Sets the number of astroids that spawn during each
     * spawn call.
     *
     * @param rate number of asteroids/spawn call.
     */
    public void setSpwanRate(int rate) {
        this.spawn_rate = rate;
    }

    /**
     * Sets the maximum number of live asteroids at a given time.
     *
     * @param limit new limit.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Returns the maximum number of live asteroids spawned by
     * this class at a given time.
     *
     * @return live asteroid limit.
     */
    public int getLimit() { return limit; }

    /**
     * Spawns asteroids around the player based on the properties of this class.
     */
    public void spawn() {
        int playerPosXOffset = (int) (player.getPosition().x - JetLag.WIDTH*map.getZoom()/2);
        int playerPosYOffset = (int) (player.getPosition().y - JetLag.HEIGHT*map.getZoom()/2);

        for (int i = 0; i < spawn_rate; i++) {
            if (asteroids.size() >= limit) return;
            int mass = base_mass + random.nextInt(rand_mass);
            Asteroid asteroid;

            // Choose spawn pos.
            switch(random.nextInt(4)) {
                // Left side of the screen
                default:
                case 0: {
                    int xpos = playerPosXOffset - margin;
                    int ypos = playerPosYOffset + random.nextInt((int) (JetLag.HEIGHT * map.getZoom()));
                    Vector3 vel = new Vector3(base_speed + (rand_speed - rand_speed * 2 * random.nextInt(2)), base_speed + rand_speed - (base_speed + rand_speed) * 2 * random.nextInt(2), 0);

                    asteroid = new Asteroid(xpos, ypos, mass, vel);
                    break;
                }

                // Above of the screen.
                case 1: {
                    int xpos = playerPosXOffset + random.nextInt((int) (JetLag.WIDTH * map.getZoom()));
                    int ypos = playerPosYOffset + (int) (JetLag.HEIGHT * map.getZoom()) + margin;
                    Vector3 vel = new Vector3(base_speed + rand_speed - (base_speed + rand_speed) * 2 * random.nextInt(2), -base_speed + (rand_speed - rand_speed * 2 * random.nextInt(2)), 0);

                    asteroid = new Asteroid(xpos, ypos, mass, vel);
                    break;
                }

                // Right of the screen
                case 2: {
                    int xpos = (int) (playerPosXOffset + JetLag.WIDTH * map.getZoom() + margin);
                    int ypos = playerPosYOffset + random.nextInt((int) (JetLag.HEIGHT * map.getZoom()));
                    Vector3 vel = new Vector3(-base_speed + (rand_speed - rand_speed * 2 * random.nextInt(2)), base_speed + rand_speed - (base_speed + rand_speed) * 2 * random.nextInt(2), 0);

                    asteroid = new Asteroid(xpos, ypos, mass, vel);
                    break;
                }

                // Below the screen.
                case 3: {
                    int xpos = playerPosXOffset + random.nextInt((int) (JetLag.WIDTH * map.getZoom()));
                    int ypos = playerPosYOffset - margin;
                    Vector3 vel = new Vector3(base_speed + rand_speed - (base_speed + rand_speed) * 2 * random.nextInt(2), base_speed + (rand_speed - rand_speed * 2 * random.nextInt(2)), 0);

                    asteroid = new Asteroid(xpos, ypos, mass, vel);
                    break;
                }
            }

            asteroids.add(map.add(asteroid));
       }
    }

    public void update() {
        for (int i = 0; i < asteroids.size(); i++) {
            BasicSprite sprite = map.get(asteroids.get(i));
            float dist = player.getPosition().cpy().sub(sprite.getPosition()).len();

            if (dist > 2 * JetLag.WIDTH * map.getZoom()) {
                map.remove(asteroids.get(i));
                asteroids.remove(i);
            }
        }
    }
}
