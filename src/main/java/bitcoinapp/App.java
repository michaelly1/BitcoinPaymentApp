package bitcoinapp;

public class App {

	private static App instance = null;

	private GUI mainFrame;

	/**
	 * Singleton instance of the App
	 * @return App
	 * @throws Exception
	 */

	protected static App getInstance() throws Exception{
		if(instance == null){
			instance = new App();
			instance.run();
		}
		return instance;
	}

	private void run() {
		try {
			mainFrame = new GUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private App() throws Exception{

	}


}
