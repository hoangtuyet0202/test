package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;


/**
 * Class provides methods to send request to server and get response
 * Date: 07/12/2021
 * @author VuongNT
 * @version 1.0 *
 */

public class API {

	/**
	 * attribute to format date/time
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * attribute to log info to console 
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());
	
	
	/**
	 * Setup connection to server
	 * @param url: path to server
	 * @param method: API method
	 * @param token: hash that needs to verify user
	 * @return connection
	 * @throws IOException
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException{
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}
	
	/**
	 * method to read response from server
	 * @param conn: connection to server
	 * @return respone: response from server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}
	
	/**
	 * method to call GET API
	 * @param url: path to server that needs request
	 * @param token: hash to verify user
	 * @return respone: response from server(string)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		//Part 1 Setup
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		//Part 2 Read response		
		String respone = readResponse(conn);
		
		return respone;
	}

	
	int var;
	
	/**
	 * method to call POST API
	 * @param url: path to server that needs request
	 * @param data: data to push to server to process(JSON)
	 * @param token: hash that need to verify user
	 * @return response: response from server (string)
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
       //Part 1  setup
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		//Part 2 send data
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		//Part 3: read response		
        String respone = readResponse(conn);
		
		return respone;	
	}

	
	/**
	 * method allows to call different APIs (only for Java 11)
	 * @deprecated only works with Java <= 11
	 * @param methods : method for allowing (PATCH, PUT,...)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
