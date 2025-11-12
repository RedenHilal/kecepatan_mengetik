package typing.core;

import typing.model.Setting;
import typing.console.Menu;

public class Game {
	private static Setting setting;

	public static void main(String[] args) throws Exception{
		AppState app = new AppState();
		Menu menu = new Menu();

		setting = menu.menu(app);

		app.cleanUp();
	}

}
