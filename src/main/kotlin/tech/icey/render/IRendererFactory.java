package tech.icey.render;

import tech.icey.glfw.handle.GLFWwindow;

public interface IRendererFactory {
    boolean isSupported();

    void init();
    GLFWwindow createWindow(int width, int height, String title);
    IRenderer createRenderer(GLFWwindow window, Object args);

    void destroyRenderer(IRenderer renderer, GLFWwindow window);
}
