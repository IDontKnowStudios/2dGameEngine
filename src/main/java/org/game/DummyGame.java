package org.game;

import static org.lwjgl.glfw.GLFW.*;

import org.engine.GameLoop;
import org.engine.IGameLogic;
import org.engine.KeyboardHandler;
import org.engine.Renderer;
import org.engine.Window;
import org.lwjgl.glfw.GLFWKeyCallback;

public class DummyGame implements IGameLogic {
	private long window;
	private GameLoop gameLoop;
	private Renderer renderer;

	private int width = 640, height = 480;
	private String title = "GAME";

	public DummyGame() {
		Window guiWindow = new Window();
		guiWindow.initWindow(width, height, title);
		window = guiWindow.getWindow();
		renderer = new Renderer(window);
		gameLoop = new GameLoop();
		gameLoop.startLoop(60, 20, this, window);
	}
	
	public void init() {
		
	}

	public void input() {
		glfwPollEvents();
	}

	public void update() {
		if(KeyboardHandler.isKeyDown(GLFW_KEY_ESCAPE)) glfwSetWindowShouldClose(window, true);
	}

	public void render() {
		renderer.render();
	}

}
