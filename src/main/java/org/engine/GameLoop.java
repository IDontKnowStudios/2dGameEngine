package org.engine;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL;

public class GameLoop {
	public void startLoop(int FPS, int UPS, IGameLogic gameLogic, long window) {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		int y = 0;
		int x = 0;

		GL.createCapabilities();
		glClearColor(1, 0, 0, 1);
		
		double loopStartTime = getTime();

		while (!glfwWindowShouldClose(window)) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				gameLogic.input();
				gameLogic.update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gameLogic.render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
			sync(loopStartTime);
		}
	}

	private void sync(double loopStartTime) {
		float loopSlot = 1f / 50;
		double endTime = loopStartTime + loopSlot;
		while (getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
			}
		}
	}

	public double getTime() {
		return System.nanoTime() / 1000000000.0;
	}
}
