package game;

public class GameEngine extends Thread {
	
	Game game;

	public GameEngine(Game game) {
		super();
		
		this.game = game;
		setName("GameEngine");
	}
	
	@Override
	public void run() {
		try {
			
			for (;game.isRunning;) {
				game.update();
				game.draw();
				sleep(10);
			}
			
			game.felicita();
			sleep(2300);
			game.dispose();
			interrupt(); //Paro hilo
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
