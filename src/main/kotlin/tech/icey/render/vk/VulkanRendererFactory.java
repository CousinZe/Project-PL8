package tech.icey.render.vk;

import tech.icey.glfw.GLFW;
import tech.icey.glfw.GLFWConstants;
import tech.icey.glfw.handle.GLFWwindow;
import tech.icey.panama.buffer.ByteBuffer;
import tech.icey.render.GLFWSingletonProvider;
import tech.icey.render.IRenderer;
import tech.icey.render.IRendererFactory;
import tech.icey.vk4j.VulkanLoader;
import tech.icey.vk4j.command.EntryCommands;
import tech.icey.vk4j.command.StaticCommands;

import java.lang.foreign.Arena;

public final class VulkanRendererFactory implements IRendererFactory {
    @Override
    public boolean isSupported() {
        return glfw.glfwVulkanSupported() == GLFWConstants.GLFW_TRUE;
    }

    @Override
    public void init() {
        if (!isSupported()) {
            return;
        }

        staticCommands = VulkanLoader.loadStaticCommands();
        entryCommands = VulkanLoader.loadEntryCommands();
    }

    @Override
    public GLFWwindow createWindow(int width, int height, String title) {
        glfw.glfwWindowHint(GLFWConstants.GLFW_CLIENT_API, GLFWConstants.GLFW_NO_API);
        try (var arena = Arena.ofConfined()) {
            var titleBuffer = ByteBuffer.allocateString(arena, title);
            return glfw.glfwCreateWindow(width, height, titleBuffer, null, null);
        }
    }

    @Override
    public IRenderer createRenderer(GLFWwindow window, Object args) {
        return null;
    }

    @Override
    public void destroyRenderer(IRenderer renderer, GLFWwindow window) {
    }

    private static final GLFW glfw = GLFWSingletonProvider.GLFW;
    private static StaticCommands staticCommands;
    private static EntryCommands entryCommands;
}
