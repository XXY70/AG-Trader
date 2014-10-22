package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.net.www.protocol.http.HttpURLConnection;

public class HTTPConnector {

	private HttpURLConnection conn;
	private Proxy proxy;
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:16.0.1) Gecko/20121011 Firefox/33.0";
	private URL url = new URL("http://www.ag-spiel.de/app/index.php?selection=");
	private List<String> paramList;

	public HTTPConnector(boolean useProxy) throws IOException {
		if (useProxy) {
			SocketAddress addr = new InetSocketAddress("127.0.0.1", 9150);
			proxy = new Proxy(Proxy.Type.SOCKS, addr);
			conn = (HttpURLConnection) url.openConnection(proxy);
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}
		conn.setUseCaches(false);
		conn.setRequestProperty("Host", "ag-spiel.de");
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "de-DE,de;q=0.5");
		for (String cookie : Config.getInstance().cookies) {
			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Referer", "");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		conn.setDoInput(true);
	}

	public String sendPost(String url, String postParams) throws IOException {

		conn.setRequestProperty("Content-Length",
				Integer.toString(postParams.length()));
		conn.setRequestMethod("POST");

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + this.url
				+ url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String getPageContent(String url) throws Exception {

		conn.setRequestMethod("GET");
		if (Config.getInstance().cookies != null) {
			for (String cookie : Config.getInstance().cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(conn.getHeaderFields().get("Set-Cookie"));

		return response.toString();

	}

	public void setFormParam(String html, String elementID, String elementTag,
			String selector, String inputValue)
			throws UnsupportedEncodingException {

		System.out.println("Extracting form's " + selector + "...");

		Document doc = Jsoup.parse(html);

		Element loginform = doc.getElementById(elementID);
		Elements inputElements = loginform.getElementsByTag(elementTag);
		paramList = new ArrayList<String>();
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals(selector)) {
				value = inputValue;
			}
			paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
		}
	}

	public String getFormParams() {

		// build parameters list
		StringBuilder result = new StringBuilder();
		for (String param : paramList) {
			if (result.length() == 0) {
				result.append(param);
			} else {
				result.append("&" + param);
			}
		}
		paramList.clear();
		return result.toString();
	}

	public void test(String url) throws MalformedURLException, IOException {
		HttpURLConnection con = (HttpURLConnection) (new URL(url).openConnection());
		con.setInstanceFollowRedirects(false);
		con.connect();
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		String location = con.getHeaderField("Location");
		System.out.println(location);
	}

	public List<String> getCookies() {
		return Config.getInstance().cookies;
	}

	public void setCookies(List<String> cookies) {
		Config.getInstance().cookies = cookies;
	}

}
