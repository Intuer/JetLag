package com.JetLag.game.engine.graphics.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class Planet extends BasicSprite {
    protected float radius;
    private String base_name = "planet-";
    private String numbers[] = {"0", "1", "2"};
    private static Random r;

    public Planet(int x, int y, float radius, float mass, Vector3 velocity){
        super(x, y, mass, velocity, null);

        if (r == null) {
            r = new Random();
        }

        String name = base_name + numbers[r.nextInt(numbers.length)] + ".png";
        setTexture(name);

        this.radius = radius;
        setScale((radius*2) / getWidth());
    }
}
