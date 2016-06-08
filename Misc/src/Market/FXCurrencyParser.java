package Market;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
import org.xml.sax.InputSource;

public class FXCurrencyParser {
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = null;
		try {

			String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDEUR%22,%20%22USDJPY%22,%20%22USDBGN%22,%20%22USDCZK%22,%20%22USDDKK%22,%20%22USDGBP%22,%20%22USDHUF%22,%20%22USDLTL%22,%20%22USDLVL%22,%20%22USDPLN%22,%20%22USDRON%22,%20%22USDSEK%22,%20%22USDCHF%22,%20%22USDNOK%22,%20%22USDHRK%22,%20%22USDRUB%22,%20%22USDTRY%22,%20%22USDAUD%22,%20%22USDBRL%22,%20%22USDCAD%22,%20%22USDCNY%22,%20%22USDHKD%22,%20%22USDIDR%22,%20%22USDILS%22,%20%22USDINR%22,%20%22USDKRW%22,%20%22USDMXN%22,%20%22USDMYR%22,%20%22USDNZD%22,%20%22USDPHP%22,%20%22USDSGD%22,%20%22USDTHB%22,%20%22USDZAR%22,%20%22USDISK%22)&env=store://datatables.org/alltableswithkeys";
			String charset = "UTF-8";
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.connect();
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				InputStream response = connection.getInputStream();
				scanner = new Scanner(response);
				String responseBody = scanner.useDelimiter("\\A").next();

				String xml = responseBody;

				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.parse(new InputSource(new StringReader(xml)));
				doc.getDocumentElement().normalize();

				NodeList errNodes = doc.getElementsByTagName("error");
				if (errNodes.getLength() > 0) {
					Element err = (Element) errNodes.item(0);
					System.out.println(err.getElementsByTagName("errorMessage").item(0).getTextContent());
				} else {
					NodeList nList = doc.getElementsByTagName("rate");
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							System.out.println("Code: " + eElement.getAttribute("id") + "\n");
							System.out.println(
									"Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent());
							System.out.println(
									"Rate : " + eElement.getElementsByTagName("Rate").item(0).getTextContent());
							System.out.println(
									"Date : " + eElement.getElementsByTagName("Date").item(0).getTextContent());
							System.out.println(
									"Time : " + eElement.getElementsByTagName("Time").item(0).getTextContent());
							System.out
									.println("Ask : " + eElement.getElementsByTagName("Ask").item(0).getTextContent());
							System.out.println(
									"Bid : " + eElement.getElementsByTagName("Bid").item(0).getTextContent() + "\n");

						}
					}

				}

			} else if (responseCode == 401) {
				// ArgumentError if token is invalid
				System.out.println("Invalid token. Unauthorized access.");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		EuroDomParser edp = new EuroDomParser();
		System.out.println(edp.getData());
		
	}
}