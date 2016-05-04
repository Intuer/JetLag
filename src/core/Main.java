package core;
import java.util.Arrays;
import java.util.Vector;

import core.graphics.Window;
import core.math.Matrix4f;
import core.math.Vector4f;

/**
 * The Main class. Controls the main functions of the game
 */
public class Main {

    //The gravitational constant
    private static final float G = 0.005f;

    /**
     * Initialises the game and it's functions
     */
    public void setup() {

    }

    /**
     * Initialises the run loop of the game.
     * Everything that will happen in the game will be
     * calculated from here.
     */
    public void run() {

    }

    /**
     * Application enter point.
     * @param args
     */
    public static void main(String args[]){
        Window wc = new Window(800,500,"JetLag");
        wc.bindContext();
        wc.setClearColor(120,50,98);

        wc.show();

        while(!wc.shouldWindowClose()){
            wc.pollEvents();
            wc.clear();
            wc.swapBuffers();
        }
        wc.destroy();
    }

    /**
     * TODO
     * Calculates the force that one objects feel from other object
     *
     * @param source The object that is the source of the force
     * @param exposed The object that is exposend and feels the force
     * @return the force that exposed is feeling from source
     */
    /**public Vector4f gravitationalPull(Object source, Object exposed){
        float f = G*(source.mass * exposed.mass) / ( distanceSquared(source, exposed));

    }*/

    /**
     * TODO
     * Calculates the distance between two objects as a Vector4f
     *
     * @param o1 The first object
     * @param o2 The second Object
     * @return The distance as a vector
     */
    /**public Vector4f distanceVector(Object o1, Object o2){
        Vector4f pos1 = o1.getPosition();
        Vector4f pos2 = o2.getPosition();
        Vector4f distance = pos1.mul(-1).add(pos2);
        return distance;
    }*/

    /**
     * TODO
     * Calculates the distance between two objects as a float
     *
     * @param o1 The first object
     * @param o2 The second object
     * @return The distance as a float
     */
    /**public float distance(Object o1, Object o2){
        return distanceVector(o1,o2).length();
    }*/

    /**
     * Calculates the squared distance between two objects as a float
     *
     * @param o1 The first object
     * @param o2 The second object
     * @return The squared distance as a float
     */
    /**public float distanceSquared(Object o1, Object o2){
        return distance(o1,o2)*distance(o1,o2);
    }*/

}
