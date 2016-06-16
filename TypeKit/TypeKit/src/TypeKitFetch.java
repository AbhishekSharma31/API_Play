
/*  Assignment to Fetch user generated type kits
	TypeKitFetch.java
	
	Created by Abhishek Sharma on 05/15/16.
	Copyright © 2016 Abhishek. All rights reserved.
	A command line interface to fetch user generated type kits
	
*/
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeKitFetch {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			//ArgumentError if no token is provided
			if (args.length != 1) {
				System.out.println("Please provide token as a command line argument to access the API.");
				System.exit(0);
			}
			//Token passed as argument 
			String token = args[0];
			//URL to fetch the kits information
			String url = "https://typekit.com/api/v1/json/kits";
			String charset = "UTF-8";
			String query = String.format("token=%s", URLEncoder.encode(token, charset));
			//Making URL Connection to the API
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.connect();
			//Getting response code for the URL
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();
				//Loading JSON Object from response string
				JSONObject myjson = new JSONObject(responseBody);
				JSONArray the_json_array = myjson.getJSONArray("kits");
				int size = the_json_array.length();
				System.out.println("User has " + size + " kits");
				for (int i = 0; i < size; i++) {
					JSONObject json_object = the_json_array.getJSONObject(i);
					//Getting Kits information as Output on console 
					System.out.println("kit " + (i + 1) + " is " + json_object);
					System.out.println("kit " + (i + 1) + " id is: " + json_object.get("id"));
					System.out.println("kit " + (i + 1) + " link is: " + json_object.get("link"));
				}
			} else if (responseCode == 401) {
				//ArgumentError if token is invalid
				System.out.println("Invalid token. Unauthorized access.");
			}
		} catch (MalformedURLException mfe) {
			//Error if URL is not formatted correctly
			System.out.println("URL not formatted correctly.");
		} catch (IOException ioe) {
			//Error if there is server issue
			System.out.println("Error from response.");
		} catch (JSONException jse) {
			//Error if JSON is invalid
			System.out.println("Invalid JSON.");
		} finally {
			//Closing the open resources
			if (scanner != null)
				scanner.close();
		}
	}
}
