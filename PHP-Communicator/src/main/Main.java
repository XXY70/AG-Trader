package main;

import java.net.CookieHandler;
import java.net.CookieManager;

public class Main {

	public static final String CONFIG_FILE = "config.cfg";

	public static void main(String[] args) {
		new MainFrameOld();
	}

	public static void no(String[] args) throws Exception {

		Config.load(CONFIG_FILE);
		// Game URL
		String url = "http://www.ag-spiel.de/app/index.php";
		// Selections
		String login = "?section=login";
		String newAGs = "?section=neue";
		String overview = "?section=start2";
		String profile = "?section=profil";
		String newOrder = "?section=agorderbuch";
		String orderOverview = "?section=agorderbuch";

		// Connector-Class
		HTTPConnector http = new HTTPConnector();

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		// "GET" request (login)
		String page = http.GetPageContent(url + login);
		String postParams = http.getFormParams(page, "Borjan", "bnmopützu");

		// 2. Construct above post's content and then send a POST request for
		// authentication
		http.sendPost(url + login, postParams);

		String result = http.GetPageContent(url + newAGs);
		for (String line : result.split("    ")) {
			if (!line.isEmpty()) {
				int pos = line.indexOf("<a");
				if (pos > 0) {
					line = line.substring(pos); // 6 is the length of your
												// "<text:" marker
					System.out.println(line);
				}
			}
		}
		Config.getInstance().toFile(CONFIG_FILE);
	}
}
