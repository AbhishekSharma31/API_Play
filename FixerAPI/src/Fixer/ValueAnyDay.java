package Fixer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class ValueAnyDay {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			// ArgumentError if no day is provided
			if (args.length != 1) {
				System.out.println("Please provide date as YYYY-MM-DD format as a token.");
				System.exit(0);
			}
			// Date in format 2000-01-03 YYYY-MM-DD passed as argument
			String token = args[0];
			// URL to fetch the information http://api.fixer.io/2000-01-03
			String url = "http://api.fixer.io";
			String charset = "UTF-8";
			String query = String.format("%s", URLEncoder.encode(token, charset));
			// Making URL Connection to the API
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "/" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.connect();
			// Getting response code for the URL
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();
				// System.out.println(responseBody);
				JSONObject json = new JSONObject(responseBody); // Convert text
																// to object
				System.out.println(json.toString(4));

			} else if (responseCode == 401) {
				// ArgumentError if token is invalid
				System.out.println("Invalid token. Unauthorized access.");
			}
		} catch (MalformedURLException mfe) {
			// Error if URL is not formatted correctly
			System.out.println("URL not formatted correctly.");
		} catch (IOException ioe) {
			// Error if there is server issue
			System.out.println("Error from response.");
		} catch (JSONException jse) { // Error if JSON is invalid
			System.out.println("Invalid JSON.");
		} finally {
			// Closing the open resources
			if (scanner != null)
				scanner.close();
		}
	}

}
