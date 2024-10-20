package tech.icey.render.gles2;

import org.jetbrains.annotations.Nullable;
import tech.icey.gles2.GLES2;
import tech.icey.glfw.GLFW;
import tech.icey.glfw.GLFWConstants;
import tech.icey.glfw.handle.GLFWwindow;
import tech.icey.panama.buffer.ByteBuffer;
import tech.icey.render.GLFWSingletonProvider;
import tech.icey.render.IRenderer;
import tech.icey.render.IRendererFactory;

import java.lang.foreign.Arena;
import java.lang.foreign.Linker;

public final class GLES2RendererFactory implements IRendererFactory {
    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    public void init() {}

    @Override
    public @Nullable GLFWwindow createWindow(int width, int height, String title) {
        try (var arena = Arena.ofConfined()) {
            glfw.glfwWindowHint(GLFWConstants.GLFW_CLIENT_API, GLFWConstants.GLFW_OPENGL_ES_API);
            glfw.glfwWindowHint(GLFWConstants.GLFW_CONTEXT_VERSION_MAJOR, 2);
            glfw.glfwWindowHint(GLFWConstants.GLFW_CONTEXT_VERSION_MINOR, 0);
            glfw.glfwWindowHint(GLFWConstants.GLFW_OPENGL_PROFILE, GLFWConstants.GLFW_OPENGL_ANY_PROFILE);

            var window = glfw.glfwCreateWindow(
                    width,
                    height,
                    ByteBuffer.allocateString(arena, title),
                    null,
                    null
            );
            if (window == null) {
                return null;
            }

            if (gles2 == null) {
                glfw.glfwMakeContextCurrent(window);
                gles2 = new GLES2((name, descriptor) -> {
                    try (var localArena = Arena.ofConfined()) {
                        var nameSegment = ByteBuffer.allocateString(localArena, name);
                        var pfn = glfw.glfwGetProcAddress(nameSegment);
                        if (pfn == null) {
                            throw new RuntimeException("unable to load function: " + name);
                        }

                        return Linker.nativeLinker().downcallHandle(pfn, descriptor);
                    }
                });
            }

            return window;
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
    private static GLES2 gles2 = null;
}
