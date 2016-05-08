package core.graphics;

import core.math.Matrix4f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;

/**
 * Basic OpenGL shader program.
 *
 * @author Nicklas Hersen
 */
public class Shader {
    private int shader_handles[];
    private int program_handle;
    private boolean relink;

    /**
     * Creates a basic (empty) shader.
     */
    public Shader() {
        shader_handles = new int[2];
    }

    /**
     * Compiles and adds a shader to this program.
     *
     * @param src shader source.
     * @param type OpenGL shader type.
     */
    public void loadShader(String src, int type) {
        int index;

        switch (type) {
            case GL_VERTEX_SHADER:
                index = 0;
                break;

            case GL_FRAGMENT_SHADER:
                index = 1;
                break;

            default:
                throw new IllegalArgumentException("Invalid shader type!");
        }

        shader_handles[index] = glCreateShader(type);
        glShaderSource(shader_handles[index], src);
        glCompileShader(shader_handles[index]);

        int state;
        state = glGetShaderi(shader_handles[index], GL_COMPILE_STATUS);
        if (state == GL_FALSE) {
            System.err.println(glGetShaderInfoLog(shader_handles[index]));

            throw new RuntimeException("Failed to compile shader.");
        }

        relink = true;
    }

    /**
     * Creates and links a program based on the specified shaders.
     */
    private void linkProgram() {
        if (program_handle != 0) glDeleteProgram(program_handle);

        program_handle = glCreateProgram();
        for (int shader : shader_handles) glAttachShader(program_handle, shader);

        glLinkProgram(program_handle);
        int status = glGetProgrami(program_handle, GL_LINK_STATUS);
        if (status == GL_FALSE) {
            System.err.println(glGetProgramInfoLog(program_handle));

            throw new RuntimeException("Failed to link program.");
        }

        relink = false;
    }

    /**
     * Tells OpenGL to use this shader program when rendering.
     */
    public void useProgram() {
        if (relink) linkProgram();

        glUseProgram(program_handle);
    }

    /**
     * Destroys this shader program.
     */
    public void destroy() {
        if (program_handle == 0) glDeleteProgram(program_handle);

        for (int shader : shader_handles) {
            if (shader == 0) glDeleteShader(shader);
        }
    }

    /**
     * Returns a handle to the specified uniform location.
     *
     * @return a handle to the specified uniform.
     */
    public int getUniformLocation(String name) {
        return glGetUniformLocation(program_handle, name);
    }

    /**
     * Sets the specified 4 dimensional matrix to the specified value.
     *
     * @param location uniform handle.
     * @param m value to copy.
     */
    public void setUniform(int location, Matrix4f m) {
        glUniformMatrix4fv(location, false, m.asFloatArray());
    }
}
