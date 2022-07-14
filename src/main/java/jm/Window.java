package jm;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.windows.WNDCLASSEX;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width, height;
    private long glfwWindow;
    String title;

    private static Window window = null;

    private Window() {
        this.width = 800;
        this.height = 600;
        this.title = "game";
    }

    public static Window get() {

        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
    }

    public void init() {
        // error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to Initialize GLFW..");
        }

        // configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // create window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL) {
            throw new IllegalStateException("Failed to Create The GLFW window");
        }

        // OpenGL current context
        glfwMakeContextCurrent(glfwWindow);

        // enable v-sync
        glfwSwapInterval(1);

        // Window Visibility
        glfwShowWindow(glfwWindow);

        // Critical Line -- Allows Bindings
        GL.createCapabilities();
    }

    public void loop() {

        while (!glfwWindowShouldClose(glfwWindow)) {

            glfwPollEvents();
            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(glfwWindow);
        }
    }
}
