package main;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;

public class AGActions {

	// Lists
	private static String[] newAGs = { "1", "2", "3" };
	private static String[] depot = { "1", "2", "3" };
	// Selections
	private static String login = "login";
	private static String mainMenu = "start";
	private static String getNewAGs = "neue";
	private static String overview = "start2";
	private static String profile = "profil";
	private static String newOrder = "agorderbuch";
	private static String orderOverview = "agorderbuch";

	private static HTTPConnector http;

	public static boolean login() throws Exception {
		if (checkCookies()) {
			try {
				http = new HTTPConnector(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
			CookieHandler.setDefault(new CookieManager());

			String page = http.getPageContent(login);
			http.setFormParam(page, "content", "input", "username",
					Config.getInstance().Username);
			http.setFormParam(page, "content", "input", "password",
					Config.getInstance().Password);
			http.sendPost(login, http.getFormParams());

			String result = http.getPageContent(mainMenu);
			return result.contains("<a class='button' href='index.php?section=start2'>Überblick</a>");
		} else {
			return false;
		}
	}

	private static boolean checkCookies() {
		return false;
	}

	public static String[] getNewAgs() {
		return newAGs;

	}

	public static String[] getDepot() {
		return depot;

	}

	public static boolean buy() {
		return false;
	}

	public static boolean sell() {
		return false;
	}

	public static String getAGName(int wkn) {

		return null;
	}

	public static int getWKN(String name) {
		return 0;
	}
}
