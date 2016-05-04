package core.graphics;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Creates a windows with an openGL context.
 * Remeber to call bindContext before use, as well as
 * destroy whenever your done with the window.
 *
 * @author Nicklas Hersen
 * @author Jacob Sörme
 */
public class Window {
    private static boolean not_first;

    private int width, height;
    private String window_title;
    private long window_handle;

    /**
     * Create a window capable of displaying opengGL
     * graphics.
     *
     * @param width window width.
     * @param height window height.
     * @param windowTitle window title.
     */
    public Window(int width, int height, String windowTitle) {
        if (!not_first) {
            not_first = true;
            if (!glfwInit()) {
                throw new IllegalStateException("Unable to initialize GLFW.");
            }
        }

        this.width = width;
        this.height = height;
        window_title = windowTitle;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window_handle = glfwCreateWindow(width, height, windowTitle, 0, 0);

        if (window_handle == 0) throw new RuntimeException("Failed to create GLFW window!");

        glfwSetKeyCallback(window_handle, (window, key, scancode, action, mode) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true);
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window_handle, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
    }

    /**
     * Specifies the function to use whenever a keyboard input
     * is received from the window.
     *
     * @param func window keyboard input event function.
     */
    public void setKeyCallbackFunc(GLFWKeyCallback func) {
        glfwSetKeyCallback(window_handle, func);
    }

    /**
     * Makes the window visible.
     */
    public void show() {
        glfwShowWindow(window_handle);
    }

    /**
     * Hides the window.
     */
    public void hide() {
        glfwHideWindow(window_handle);
    }

    /**
     * Binds the gl context to the current window.
     */
    public void bindContext() {
        glfwMakeContextCurrent(window_handle);
        GL.createCapabilities();
    }

    /**
     * Enables v-sync.
     */
    public void enableVSync() {
        glfwSwapInterval(1);
    }

    /**
     * Disable v-sync
     */
    public void disableVSync() {
        glfwSwapInterval(0);
    }

    /**
     * Destroys the current window, must be called before
     * exiting the program.
     */
    public void destroy() {
        if (window_handle != 0) {
            glfwDestroyWindow(window_handle);
        }
        glfwTerminate();
    }

    /**
     * Process any events the window might have
     * received.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Returns whether the window should close.
     *
     * @return whether the window should close.
     */
    public boolean shouldWindowClose() {
        return glfwWindowShouldClose(window_handle);
    }

    /**
     * Swap window openGL buffers.
     */
    public void swapBuffers() {
        glfwSwapBuffers(window_handle);
    }

    /**
     * Set backgound color
     *
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public void setClearColor(int r, int g, int b){
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("Invalid argument range");
        }
        glClearColor((float)r/(float)255,(float)g/(float)255,(float)b/(float)255,1.0f);
    }

    /**
     * Clear window from graphics
     */
    public void clear(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

}
