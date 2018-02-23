package bitcoinapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class HTTPRequestHandler {

	private static final String BASE_URL = "http://localhost:3000/merchant/";
	
	/**
	 * Executes an HTTP request by taking in a URL as a String and creating
	 * a HttpURLConnection object using it.
	 * 
	 * @param urlToRead url to send HTTP request to
  	 * @return JSON file
	 * @throws Exception if a connection is unable to be established
	 */
	public JSONObject executeRequest(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      
	      if(conn.getResponseCode()!=200){
	    	  return null;
	      }
	      
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      
	      JSONObject json = new JSONObject(result.toString());
	      
	      return json;
	}
	
	/**
	 * Sends a payment by calling the executeRequest method. This method simply
	 * concatenates the base url with the endpoint "/payment" and the parameters.
	 * Returns true if the payment was successful.
	 * 
	 * @param user user that is currently logged in
	 * @param paramsMap hashmap of parameters
	 * @return true if payment was successful, false otherwise
	 * @throws Exception if something goes wrong with the HTTP request
	 */
	public boolean sendPayment(User user, HashMap<String, String> paramsMap) throws Exception{
		String endpoint = user.getGuid() + "/payment?";
		String parameters = paramsToString(paramsMap);
		String URL = BASE_URL + endpoint + parameters;
		
		JSONObject response = executeRequest(URL);
		
		if(response == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Checks the balance of the user that is logged in by calling the executeRequest method.
	 * If the HTTP request is successful, the balance is returned, otherwise a value of -1
	 * is returned to let the caller know that the request was unsuccessful.
	 * 
	 * @param user user that is logged in
	 * @param paramsMap hashmap of parameters
	 * @return balance of account if successful, -1 if otherwise
	 * @throws Exception if something goes wrong with the HTTP request
	 */
	public int retrieveBalance(User user, HashMap<String, String> paramsMap) throws Exception{
		String endpoint = user.getGuid() + "/balance?";
		String parameters = paramsToString(paramsMap);
		String URL = BASE_URL + endpoint + parameters;
		
		JSONObject response = executeRequest(URL);

		if(response == null){
			return -1;
		}
		
		return response.getInt("balance");
	}
	
	/**
	 * Converts the hashmap of parameters to a string which will be
	 * concatenated with the URL String to be passed to the HTTP request
	 * 
	 * @param params hashmap of parameters
	 * @return parameters as a string
	 */
	private String paramsToString(HashMap<String, String> params){
		String paramString = "";
		
		for(Map.Entry<String, String> entry : params.entrySet()){
			paramString += entry.getKey();
			paramString += "=";
			paramString += entry.getValue();
			paramString += "&";
		}
		
		if (paramString.length() > 0 && paramString.charAt(paramString.length() - 1) == '&') {
	        paramString = paramString.substring(0, paramString.length() - 1);
	    }
		
		return paramString;
	}
	
	
}
