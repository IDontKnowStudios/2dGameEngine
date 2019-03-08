package engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import engine.input.KeyboardHandler;
import engine.input.MouseClickHandler;
import engine.input.MousePosHandler;

public class Window {
	private int width, height;
	private long window;

	private boolean resized = false;
	private boolean vsync;
	private boolean maximised;
	
	private String title;

	public Window(boolean vsync, int width, int height, String title, boolean maximised) {
		this.vsync = vsync;
		this.width = width;
		this.height = height;
		this.title = title;
		this.maximised = maximised;
	}

	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		if(maximised) glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

		window = glfwCreateWindow(width, height, title, 0, 0);
		if (window == 0) {
			throw new RuntimeException("Window could not be initialized!");
		}
		
		glfwSetFramebufferSizeCallback(window, (window, pwidth, pheight) -> {
			this.width = pwidth;
			this.height = pheight;
			this.resized = true;
		});

		glfwSetKeyCallback(window, new KeyboardHandler());
		glfwSetCursorPosCallback(window, new MousePosHandler());
		glfwSetMouseButtonCallback(window, new MouseClickHandler());

		//glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

		glfwMakeContextCurrent(window);
		
		if(vsync) glfwSwapInterval(1);

		glfwShowWindow(window);
		
		GL.createCapabilities();

		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		glEnable(GL_DEPTH_TEST);
	}

	public void setClearColor(float r, float g, float b, float alpha) {
		glClearColor(r, g, b, alpha);
	}

	public long getWindowId() {
		return window;
	}

	public boolean isResized() {
		return this.resized;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setResized(boolean resized) {
		this.resized = resized;
	}
	
	public void update() {
		glfwSwapBuffers(window);
        glfwPollEvents();
	}
}
