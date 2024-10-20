package tech.icey.render;

import tech.icey.glfw.GLFW;
import tech.icey.glfw.GLFWConstants;
import tech.icey.glfw.GLFWLoader;

public final class GLFWSingletonProvider {
    public static final GLFW GLFW;

    static {
        GLFWLoader.loadGLFWLibrary();
        GLFW = GLFWLoader.loadGLFW();
        if (GLFW.glfwInit() != GLFWConstants.GLFW_TRUE) {
            throw new RuntimeException("unable to initialize GLFW");
        }
    }
}
