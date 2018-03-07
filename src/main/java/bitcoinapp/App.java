package bitcoinapp;

import javax.swing.*;

public class App {

	private User user;
	private HTTPRequestHandler httpReq;

	private static final String API_KEY = "4089708b-a883-4e0a-b922-4035b9b3e579";

	private int balance;

	private static App instance = null;


	private GUI mainFrame;

	/**
	 * Singleton instance of the GUI
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
		init();
	}


	/*
	This should all be done in seperate classes...
	 */


	/**
	 * This where the gui will go. HTTP request will be called here by invoking methods from
	 * the httpReq instance.
	 * @throws Exception
	 */
	private void init() throws Exception{

//		httpReq = new HTTPRequestHandler();
//		user = new User();


		/*
		 * First step will be to create new user using their walled id and password. These are the
		 * same wallet Id and password that will be used in checking the balance as described below
		 */
//		user.setGuid([walletID]);
//		user.setPassword([password]);



		/*
		 * Needs a login screen with wallet ID and password fields. To ensure that the login
		 * is valid, a balance inquiry is made. If the login is not successful, a value of -1
		 * will be returned and the user will be notified that they have entered either a wrong
		 * wallet id or password. Otherwise, if the login is successful,
		 * a User object is created with the appropriate guid(Wallet ID) and password fields.
		 * These fields will be referenced through the life of the program. If the login is not
		 * successful, the user is notified that they have entered either a wrong username or
		 * wrong password
		 *
		 * The balance inquiry will look something like this.
		 *
		 *  HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("password", user.getPassword());
			parameters.put("api_code", API_KEY);

			balance = httpReq.retrieveBalance(user, parameters);

			if(balance < 0){
				//here let the user know that their login was unsuccessful
			}else{
				//login to main screen and display balance
			}
		 *
		 *
		 *
		 *  */



		/*
		 * Whenever the user wants to send a payment, the following parameters are required.
		 * (some are actually optional, as specified)
		 *
		 * to - bitcoin address to send to (required)
		 * amount - amount in satoshi to send (required) --- the minimum payment seems to be 1000
		 * password - main wallet password (required)
		 * second_password - second wallet password (required, only if second password is enabled)
		 * api_code - blockchain.info wallet api code (optional)
		 * from - bitcoin address or account index to send from (optional) --- this says optional but i would not work unless i included it with from = 0
		 * fee - specify transaction fee in satoshi(required) - I think the minimum for this is 500
		 * fee_per_byte - specify transaction fee-per-byte in satoshi
		 *
		 *
		 * These parameters need to be put into a HashMap. The HashMap will be sent as an argument
		 * to the HTTPRequestHandler instance to be used as part of the HTTP query.
		 *
		 * It will look something like this.
		 *
		 *  HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("to", [receivers wallet address]);
			parameters.put("amount", [amount in satoshi]);
			parameters.put("password", user.getPassword());
			parameters.put("api_code", API_KEY);
			.
			.
			.
			try{
				System.out.println(httpReq.sendPayment(user, parameters));
			}catch(IOException e){
				System.out.println("Something went wrong with your transaction");
			}
		 *
		 *
		 * A dialog should pop up to let the user know if their transaction was successful.
		 * Also, the balance should be updated each time a successful transaction is completed
		 * by completing a balance inquiry
		 *
		 *
		 * */

	}
}
