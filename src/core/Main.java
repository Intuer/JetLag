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
}
