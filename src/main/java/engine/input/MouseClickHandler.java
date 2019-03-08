package engine.input;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseClickHandler extends GLFWMouseButtonCallback {

	private static boolean[] mouseButton = new boolean[65536];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		mouseButton[button] = action != GLFW_RELEASE;
	}

	public static boolean getMouseClicked(int button) {
		return mouseButton[button];
	}
}
