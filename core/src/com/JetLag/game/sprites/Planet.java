package com.jetlag.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-08.
 */
public class Planet {

    private Vector3 position;
    private Vector3 velocity;
    private Texture planet;
    private float mass;

    public Planet(int x, int y, float mass, Vector3 velocity){
        this.position = new Vector3(x,y,0);
        this.velocity = new Vector3(velocity);
        this.planet = new Texture("planet.png");
        this.mass = mass;
    }

    public void update(float dt){
        if ( position.y > 0 ){
            velocity.add(0, -10, 0);
        }

        position.add(0, velocity.y*dt, 0);

        if (position.y < 0 ){
            velocity.y = -velocity.y;
            position.y = 0;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return planet;
    }

    public float getMass() {
        return mass;
    }

    public void addPosition(float x, float y, float z){
        this.position.add(x,y,z);
    }

}
