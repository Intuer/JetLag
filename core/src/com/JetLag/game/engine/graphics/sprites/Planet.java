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
        texture = new Texture(name);

        this.radius = radius;
    }

    public void draw(SpriteBatch sp) {
        sp.draw(texture, pos.x - radius, pos.y - radius, 2*radius, 2*radius);
    }
}
