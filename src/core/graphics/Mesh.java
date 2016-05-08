package core.graphics;

import core.math.Matrix4f;
import core.utils.MatrixUtils;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL11.GL_FLOAT;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Basic mesh class for storing object data before rendering
 * them. Don't forget to destroy the mesh after use.
 *
 * @author Nicklas Hersen
 * @version 05.09.2016
 */
public class Mesh {
    private int vbo, ibo;
    private Matrix4f pos, rot, scale;

    FloatBuffer vertices;
    IntBuffer indices;

    /**
     * Creates a drawable mesh from a number of specified vertices and
     * indices.
     *
     * @param vertices array containing all the vertiecs in the mesh.
     * @param indices array containing draw order of said vertices.
     */
    public Mesh(float vertices[], int indices[]) {
        pos = rot = scale = new Matrix4f();

        this.vertices = BufferUtils.createFloatBuffer(vertices.length);
        this.vertices.put(vertices).flip();

        this.indices = BufferUtils.createIntBuffer(indices.length);
        this.indices.put(indices).flip();

        vbo = glGenBuffers();
        ibo = glGenBuffers();

        bind();
        glBufferData(GL_ARRAY_BUFFER, this.vertices, GL_STATIC_DRAW);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, this.indices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        unbind();
    }

    /**
     * Destroys the content of this mesh. Only call this method when
     * you no longer intend to use this mesh.
     */
    public void destroy() {
        glDeleteBuffers(ibo);
        glDeleteBuffers(vbo);
    }

    /**
     * Binds the prepare the mesh for drawing. (Binds necessary buffers).
     */
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    /**
     * Unbinds all openGL buffers related to this class, regardless
     * of their corresponding object.
     */
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Sets the position of this mesh.
     *
     * @param x
     * @param y
     * @param z
     */
    public void setPos(float x, float y, float z) {
        pos = MatrixUtils.translate(x, y, z);
    }

    /**
     * Sets the orientation of this mesh.
     *
     * @param pitch rotation along x-axis.
     * @param yaw rotation along the y-axis.
     * @param roll rotation along the z-axis.
     */
    public void setRotation(float pitch, float yaw, float roll) {
        rot = MatrixUtils.rotate(pitch, yaw, roll);
    }

    /**
     * Sets the scale of the mesh.
     *
     * @param x scale along the x-axis.
     * @param y scale along the y-axis.
     * @param z scale along the z-axis.
     */
    public void setScale(float x, float y, float z) {
        scale = MatrixUtils.scale(x, y, z);
    }

    /**
     * Moves the mesh in a specified direction.
     *
     * @param x amount along the x-axis.
     * @param y amount along the y-axis.
     * @param z amount along the z-axis.
     */
    public void move(float x, float y, float z) {
        pos = pos.mul(MatrixUtils.translate(x, y, z));
    }

    /**
     * Scales the mesh relative to its current scale.
     *
     * @param x amount along the x-axis.
     * @param y amount along the y-axis.
     * @param z amount alogn the z-axis.
     */
    public void scale(float x, float y, float z) {
        scale = scale.mul(MatrixUtils.scale(x, y, z));
    }

    /**
     * Rotates the mesh relative to its current orientation.
     *
     * @param pitch rotation along the x-axis.
     * @param yaw rotation along the y-axis.
     * @param roll rotation along the z-axis.
     */
    public void rotate(float pitch, float yaw, float roll) {
        rot = rot.mul(MatrixUtils.rotate(pitch, yaw, roll));
    }

    /**
     * Returns the current translation matrix.
     *
     * @return the current translation matrix.
     */
    public Matrix4f getTranslationMatrix() {
        return pos;
    }

    /**
     * Returns the current scale matrix.
     *
     * @return the current scale matrix.
     */
    public Matrix4f getScaleMatrix() {
        return scale;
    }

    /**
     * Returns the current rotation matrix.
     *
     * @return the current rotation matrix.
     */
    public Matrix4f getRotationMatrix() { return rot; }

    /**
     * Returns the number of indices used when drawing this mesh.
     *
     * @return the number of indices used when drawing this mesh.
     */
    public int getIndicesCount() {
        indices.position(0);
        return indices.remaining();
    }
}
