package TypeKitVariations;

/*
	Version for Family info and its variation
	TypeKitVariations.java
	
	Created by Abhishek Sharma on 05/15/16.
	Copyright © 2016 Abhishek. All rights reserved.
	A command line interface to fetch info about a font family and its variation
	Pass family id  and variation as arguments
*/
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeKitVariations {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			//ArgumentError if no family and variation is provided
			if (args.length != 2) {
				System.out.println("Please provide family Id and variation as a command line argument to access the API.");
				System.exit(0);
			}
			//family id passed as argument 
			String token = args[0];
			//variation passed as second argument
			String var =args[1];
			//URL to fetch the family and variations information
			String url = "https://typekit.com/api/v1/json";
			String charset = "UTF-8";
			String query = String.format("families/%s/%s", URLEncoder.encode(token, charset),URLEncoder.encode(var, charset));
			//Making URL Connection to the API
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "/" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.connect();
			//Getting response code for the URL
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();
				//System.out.println(responseBody);
				JSONObject json = new JSONObject(responseBody); // Convert text to object
				System.out.println(json.toString(4));
//			}
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
		}catch (JSONException jse) {
			//Error if JSON is invalid
			System.out.println("Invalid JSON.");
		}  finally {
			//Closing the open resources
			if (scanner != null)
				scanner.close();
		}
	}
}
