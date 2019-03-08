package engine;

public interface IGameLogic {
	
	void input(long window);
	
	void update();
	
	void render(Window window);
	
	void cleanup();
	
	void init(Window window) throws Exception;
}
