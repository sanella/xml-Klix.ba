import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Zadatak: Napisi program koji ce nuditi spisak clanaka sa stranice
 * http://www.klix.ba/rss/svevijesti, te po odabiru kornika ispisati clanak
 * 
 * @author Sanela Grcic
 *
 */
public class ReaderXML {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilder docReader = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		// UPISITE BILO KOJI URL ILI KORISTITE OVAJ:
		Document doc = docReader.parse(new URL(
				"http://www.klix.ba/rss/svevijesti").openStream());
		// Nacin na koji citamo xml file sa weba

		// node lista za porlazak kroz item
		NodeList xmlKlix = doc.getElementsByTagName("item");

		// Linked lista za prolazak kroz Iteme
		LinkedList<Element> items = new LinkedList<Element>();

		// LinkedLista za prolazak kroz sve clanke
		LinkedList<Article> articles = new LinkedList<Article>();
		// petlja za prolazak kroz NODLISTU
		for (int i = 0; i < xmlKlix.getLength(); i++) {
			Node current = xmlKlix.item(i);
			if (current instanceof Element) {
				Element currentElement = (Element) current;
				// dodajem sve current Nodove u LinkedListu ITEM
				items.add(currentElement);
				// petlja koja provjerava da li ITEM ima childe
				if (currentElement.hasChildNodes()) {

					// pravim nove nod liste koje su djeca curren Noda
					NodeList titleList = currentElement
							.getElementsByTagName("title");
					NodeList contentList = currentElement
							.getElementsByTagName("clanak");

					// petlja kojom prolazim kroz nove nodove
					for (int j = 0; j < titleList.getLength(); j++) {

						Node currentTitle = titleList.item(j);
						Node currentContent = contentList.item(j);

						if (currentTitle instanceof Element) {
							Element titleElement = (Element) currentTitle;
							Element contentElement = (Element) currentContent;

							String title = titleElement.getTextContent();
							String content = contentElement.getTextContent();
							// dodaj u Linked listu Articles koja sadrzi objekte
							// Article
							articles.add(new Article(title, content));
						}
					}
				}
			}
		}

		Scanner sc = new Scanner(System.in);

		// petlja da se ispisu sve naslove
		System.out.println("Title list: \n");
		for (int i = 0; i < articles.size(); i++) {
			System.out.println(i + ": " + articles.get(i).getTittle());
		}
		int numberOfArticle = -100;

		// PETLJA U KOJOJ BIRA I KOJA ISPISUJE ODABRANI CLANAK
		while (numberOfArticle < 0 || numberOfArticle >= articles.size()) {
			System.out
					.println("\nChoose nuber of article, that U wanna read: ");
			numberOfArticle = sc.nextInt();

		}
		System.out.print(articles.get(numberOfArticle).printArticle());
	}
}