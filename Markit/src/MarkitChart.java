
/* 
	Copyright © 2016 Abhishek. All rights reserved.
	Markit Interactive Chart API

*/
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class MarkitChart {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			// ArgumentError if no symbol id is provided
			// if (args.length != 1) {
			// System.out.println("Please provide parameters as token as a
			// command line
			// argument to access the API.");
			// System.exit(0);
			// }

			// Pass parameters as argument
			String token = args[0];
			// Sample URL
			// http://dev.markitondemand.com/MODApis/Api/v2/InteractiveChart/jsonp?input=NFLX
			String url = "http://dev.markitondemand.com/MODApis/Api/v2/InteractiveChart/json?parameters=";
			String charset = "UTF-8";
			String query = String.format("parameters=%s", URLEncoder.encode(token, charset));
			// Making URL Connection to the API
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.connect();
			// Getting response code for the URL
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();
				System.out.println(responseBody);

				// Converting to JSON Format by replacing the callback
				// method name
				// String r = responseBody.replace("(function () { })([", " ");
				// String p = r.replace("])", " ");

				// for JSON Pretty
				// JSONObject json = new JSONObject(p);
				JSONObject json = new JSONObject(responseBody);
				System.out.println(json.toString(4));

			} else if (responseCode == 401) {
				// ArgumentError if token is invalid
				System.out.println("Invalid token. Unauthorized access.");

			}
			// }

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
