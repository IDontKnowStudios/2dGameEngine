package org.engine;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import javax.swing.AbstractAction;

public class Input {
	
	private long window;
	private int[] keyArray = new int[26];
	private int[] actionArray = new int[26];
	private AbstractAction[] functionArray = new AbstractAction[26];
	
	public Input(long window) {
		this.window = window;
	}
	
	public void checkKeys() {
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			for(int i = 0; i < keyArray.length; i++) {
				if(keyArray[i] == key && actionArray[i] == action) {
					functionArray[i].actionPerformed(null);
				}
			}
		});
	}
	
	public void addKeyBinding(int key, int action, AbstractAction function) {
		for(int i = 0; i < functionArray.length; i++) {
			if(functionArray[i] == null) {
				keyArray[i] = key;
				actionArray[i] = action;
				functionArray[i] = function;
			}
		}
	}
}
