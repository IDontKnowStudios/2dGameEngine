package org.engine;

import static org.lwjgl.glfw.GLFW.*;

import org.engine.input.KeyboardHandler;
import org.engine.input.MouseHandler;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
	private long window;
	private boolean resized;
	private int width, height;

	public void initWindow(int width, int height, String title) {
		this.width = width;
		this.height = height;
		
		if (!glfwInit()) {
			System.err.println("GLFW could not be initialized!");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		window = glfwCreateWindow(10, 10, title, 0, 0);
		if (window == 0) {
			System.err.println("Window could not be initialized!");
		}
		
		glfwSetKeyCallback(window, new KeyboardHandler());
		glfwSetCursorPosCallback(window, new MouseHandler());
		
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		glfwSetFramebufferSizeCallback(window, (window, pwidth, pheight) -> {
			this.width = pwidth;
			this.height = pheight;
			Window.this.setResized(true);
		});

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
		glfwMakeContextCurrent(window);
		
		glfwSwapInterval(1);
		
		glfwShowWindow(window);
	}
	
	public long getWindow() {
		return window;
	}
	
	public void setResized(boolean resized) {
		this.resized = resized;
	}
}
