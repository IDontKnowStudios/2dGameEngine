package org.engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler extends GLFWCursorPosCallback {
	
	private double xPosition, yPostion;
	
	@Override
	public void invoke(long window, double xPos, double yPos) {
		xPosition = xPos;
		yPostion = yPos;
	}
	
	public double getXPos() {
		return xPosition;
	}
	
	public double getYPos() {
		return yPostion;
	}
}
