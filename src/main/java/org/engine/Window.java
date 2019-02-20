package org.engine;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

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
		
		glfwSetFramebufferSizeCallback(window, (window, pwidth, pheight) -> {
			this.width = pwidth;
			this.height = pheight;
			Window.this.setResized(true);
		});

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
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
