package Market;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
//import org.xml.sax.InputSource;

public class EuroDomParser {

	public String getData() throws IOException {

		String url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
		String charset = "UTF-8";
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		connection.connect();
		// int responseCode = connection.getResponseCode();
		InputStream response1 = connection.getInputStream();
		Scanner scanner = new Scanner(response1);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		return responseBody;
		// scanner.close();

		// DocumentBuilderFactory dbFactory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// Document doc = dBuilder.parse(response1);
		// doc.getDocumentElement().normalize();
		//
		// NodeList nList = doc.getElementsByTagName("Cube");
		//
		// for (int temp = 2; temp < nList.getLength(); temp++) {
		// Node nNode = nList.item(temp);
		//
		// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		// Element eElement = (Element) nNode;
		// System.out.println(" Currency: " +
		// eElement.getAttribute("currency"));
		// System.out.println(" Rate: " + eElement.getAttribute("rate"));
		//
		// }
		// }

	}
}
