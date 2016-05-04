package core.graphics;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL11.GL_FLOAT;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Nicklas Hersén on 2016-05-04.
 */
public class Mesh {
    private int vbo, ibo;


    FloatBuffer vertices;
    IntBuffer indices;

    public Mesh(float vertices[], int indices[]) {
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

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void setPos(float x, float y, float z) {

    }
}
