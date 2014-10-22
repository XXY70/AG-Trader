package main;

public class Main {

	public static final String CONFIG_FILE = "config.cfg";

	public static void no(String[] args) {
		new MainFrameOld();
	}

	public static void main(String[] args) throws Exception {

		Config.load(CONFIG_FILE);
		System.out.println(AGActions.login());
		Config.getInstance().toFile(CONFIG_FILE);
	}
}
