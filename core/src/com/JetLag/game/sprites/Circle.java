package com.JetLag.game.sprites;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by jacob_000 on 2016-05-09.
 */
public class Circle {

    private Vector3 position;
    private Vector3 velocity;
    private Vector3 forces;
    private float[] colour;
    private int mass;

    public Circle(int x, int y, float[] colour, int mass){
        this.position = new Vector3(x,y,0);
        this.velocity = new Vector3(0,0,0);
        this.forces = new Vector3(0,0,0);
        this.colour = colour;
        this.mass = mass;
    }

}
