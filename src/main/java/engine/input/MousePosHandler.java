package engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePosHandler extends GLFWCursorPosCallback {
	
	private static double xPosition, yPostion;
	
	@Override
	public void invoke(long window, double xPos, double yPos) {
		xPosition = xPos;
		yPostion = yPos;
	}
	
	public static double getXPos() {
		return xPosition;
	}
	
	public static double getYPos() {
		return yPostion;
	}
}
