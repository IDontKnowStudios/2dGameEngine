package org.engine;

import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class Renderer {
	private long window;
	
	public Renderer(long window) {
		this.window = window;
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glfwSwapBuffers(window);
	}
}
