package org.game;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import static org.lwjgl.glfw.GLFW.*;

import org.engine.IGameLogic;
import org.engine.Renderer;
import org.engine.Window;
import org.engine.GameLoop;

public class DummyGame implements IGameLogic {
	private Window window;
	private GameLoop gameLoop;
	private Renderer renderer;

	private int width = 640, height = 480;
	private String title = "GAME";

	public DummyGame() {
		window = new Window();
		window.initWindow(width, height, title);
		renderer = new Renderer(window.getWindow());
		gameLoop = new GameLoop();
		gameLoop.startLoop(60, 20, this, window.getWindow());
	}

	public void input() {
		glfwPollEvents();
	}

	public void update() {

	}

	public void render() {
		renderer.render();
	}

}
