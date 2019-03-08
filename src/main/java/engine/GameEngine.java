package engine;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameEngine implements Runnable {
	
	private int UPS, FPS;
	
	private Window window;
	private IGameLogic gameLogic;
	private Thread gameLoopThread;
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param title
	 * @param maximised
	 * @param FPS
	 * @param UPS
	 * @param vsync
	 * @param gameLogic
	 */
	public GameEngine(int width, int height, String title, boolean maximised, int FPS, int UPS, boolean vsync, IGameLogic gameLogic) {
		gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
		this.UPS = UPS;
		this.FPS = FPS;
		this.window = new Window(vsync, width, height, title, maximised);
		this.gameLogic = gameLogic;
	}
	
	public void start() {
		String osname = System.getProperty("os.name");
		if(osname.contains("Mac")) {
			gameLoopThread.run();
		} else {
			gameLoopThread.start();
		}
	}
	
	protected void init() throws Exception {
		window.init();
		gameLogic.init(window);
	}
	
	@Override
	public void run() {
		try {
			init();
			startLoop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			gameLogic.cleanup();
		}
	}
	
	public void startLoop() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		int y = 0;
		int x = 0;
		
		while (!glfwWindowShouldClose(window.getWindowId())) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				gameLogic.input(window.getWindowId());
				gameLogic.update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gameLogic.render(window);
				window.update();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}
}
