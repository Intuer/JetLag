package core.graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by Nicklas Hersén on 2016-05-09.
 */
public class Sprite {
    public final Mesh mesh;
    public Texture texture;
    private int vao;
    private int tcoord_handle;

    public Sprite(float width, float height, String texture) {
        float vdata[] = {-width/2, height/2, 0,
                width/2, height/2, 0,
                width/2, -height/2, 0,
                -width/2, -height/2, 0};

        int idata[] = {0, 1, 2,
                2, 3, 0};

        float tdata[] = { 0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f};

        FloatBuffer fb = BufferUtils.createFloatBuffer(tdata.length);
        fb.put(tdata).flip();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        mesh = new Mesh(vdata, idata);

        glActiveTexture(GL_TEXTURE0);
        this.texture = new Texture(texture);

        tcoord_handle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tcoord_handle);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void draw() {
        glBindVertexArray(vao);
        mesh.bind();
        texture.bind();
        glBindBuffer(GL_ARRAY_BUFFER, tcoord_handle);

        glDrawElements(GL_TRIANGLES, mesh.getIndicesCount(), GL_UNSIGNED_INT, 0);

        mesh.unbind();
        texture.unbind();
        glBindVertexArray(0);
    }

    public void destroy() {
        mesh.destroy();
        texture.destroy();
        glDeleteBuffers(tcoord_handle);
        glDeleteVertexArrays(vao);
    }
}
