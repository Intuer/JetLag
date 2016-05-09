package core;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import core.graphics.*;
import core.math.Matrix4f;
import core.utils.MatrixUtils;

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
        Window wc = new Window(800, 500, "JetLag");
        wc.bindContext();
        wc.setClearColor(0,0,0);

        Shader s = new Shader();

        wc.show();

        // Create a simple shader
        String vs = "#version 330\n layout(location=0) in vec3 pos;\n layout(location=1) in vec2 tcoord;\n uniform mat4 translation;\n uniform mat4 rotation;\n uniform mat4 scale;\n varying vec2 coords;\n void main() {\n coords = tcoord;\n gl_Position = translation * rotation * scale * vec4(pos, 1.0f);\n }\n";
        String fs = "#version 330\n uniform sampler2D samp;\n varying vec2 coords;\n void main() {\n gl_FragColor = texture(samp, coords);\n}\n";
        s.loadShader(vs, GL_VERTEX_SHADER);
        s.loadShader(fs, GL_FRAGMENT_SHADER);
        s.useProgram();

        int uni = s.getUniformLocation("translation");
        int uni3 = s.getUniformLocation("rotation");
        int uni2 = s.getUniformLocation("scale");


        Sprite sp = new Sprite(1, 1, "res\\test.png");
        Sprite sp2 = new Sprite(2, 1, "res\\test2.png");

        while(!wc.shouldWindowClose()){
            wc.pollEvents();
            wc.clear();

            s.setUniform(uni, MatrixUtils.translate(0, 0.5f, 0));
            s.setUniform(uni2, new Matrix4f());
            sp.mesh.rotate(0, (float) Math.PI / 100, 0);
            s.setUniform(uni3, sp.mesh.getRotationMatrix());

            sp.draw();

            s.setUniform(uni3, new Matrix4f());
            s.setUniform(uni, MatrixUtils.translate(0, -0.5f, 0));

            sp2.draw();

            wc.swapBuffers();
            try {
                Thread.sleep(10);
            } catch (Exception e) {}
        }

        s.destroy();
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
