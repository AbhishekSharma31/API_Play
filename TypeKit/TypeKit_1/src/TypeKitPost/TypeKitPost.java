package TypeKitPost;

/*
	Version to add a kit to exiting ID with Token 
	TypeKitPost.java
	
	Created by Abhishek Sharma on 05/15/16.
	Copyright © 2016 Abhishek. All rights reserved.
	A command line interface to post a kit with name and domain to  user with a token
	Pass family id  and variation as arguments
*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeKitPost {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			// ArgumentError if no family id is provided
			if (args.length != 3) {
				System.out.println("Please provide token, name and domain name as a command line arguments to access the API.");
				System.exit(0);
			}
			// token passed as argument
			String token = args[0];
			// name for the kit
			String name = args[1];
			// domain name for the kit
			String dname = args[2];
			// URL to fetch the kits information
			// SAMPLE
			// https://typekit.com/api/v1/json/kits?token=1f28efeb54e80f6340f2c79b41cdb30ddb4cdd47&name=newkit&domains[]=example.com;
			String url = "https://typekit.com/api/v1/json/kits";
			String charset = "UTF-8";
			String query = String.format("token=%s&name=%s&domains=%s", URLEncoder.encode(token, charset),
					URLEncoder.encode(name, charset), URLEncoder.encode(dname, charset));
			URLConnection connection = new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			try (OutputStream output = connection.getOutputStream()) {
				output.write(query.getBytes(charset));
			}
			
			InputStream response = connection.getInputStream();
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
			httpConnection.setRequestMethod("POST");
			
			int responseCode = ((HttpURLConnection) connection).getResponseCode();
			if (responseCode == 200) {
				// InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();
				System.out.println("New Kit has been added!");
				
				// System.out.println(responseBody);
				JSONObject json = new JSONObject(responseBody); // Convert text to object
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
			System.out.println("Error from response.You have reached the maximum number of kits");
		} catch (JSONException jse) { // Error if JSON is invalid
			System.out.println("Invalid JSON.");
		} finally {
			// Closing the open resources
			if (scanner != null)
				scanner.close();
		}
	}
}
