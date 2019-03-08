package engine;

import java.io.InputStream;
import java.util.Scanner;

import org.lwjgl.glfw.GLFWVidMode;

import org.lwjgl.glfw.*;

public class Utils {
	
	private static Window window;
	
    public Utils(Window window) {
    	this.window = window;
    }

    public static String loadResource(String fileName) throws Exception {
        String result;
        try (InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(fileName);
                Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
    
    public static int convertToFloatXCoord(int xCoord) {
    	return (window.getWidth() - xCoord) / (window.getWidth() / 2) - 1;
    }
    
    public static int convertToFloatYCoord(int yCoord) {
    	return (window.getHeight() - yCoord) / (window.getHeight() / 2) - 1;
    }
}
