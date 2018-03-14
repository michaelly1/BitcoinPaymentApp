package bitcoinapp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequestHandler {

	
	/**
	 * Executes an HTTP request by taking in a URL as a String and creating
	 * a HttpURLConnection object using it.
	 * 
	 * @param urlToRead url to send HTTP request to
  	 * @return JSON file
	 * @throws Exception if a connection is unable to be established
	 */
	public static JsonObject executeRequest(String urlToRead) throws Exception {
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

		JsonObject json = new JsonParser().parse(result.toString()).getAsJsonObject();
	      
		return json;
	}

	
	
}
