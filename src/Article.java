/**
 * Zadatak: Napisi program koji ce nuditi spisak clanaka sa stranice
 * http://www.klix.ba/rss/svevijesti, te po odabiru kornika ispisati clanak
 * 
 * @author Sanela Grcic
 *
 */

public class Article {

	private String title;
	private String content;

	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTittle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String printArticle() {
		return "Title:[ " + title + " ]" + "\n" + content;
	}
}