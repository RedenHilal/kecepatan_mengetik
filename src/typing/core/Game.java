package typing.core;

import typing.model.Setting;
import typing.console.Menu;
import typing.model.GameResult;
import typing.word.Generator;
import typing.model.difficulty.*;
import typing.console.Result;

import typing.model.unitmode.UnitMode;
import typing.model.unitmode.GameModeFactory;

import java.util.List;

public class Game {
	private static Setting setting = new Setting();

	public static void main(String[] args) throws Exception{
		AppState app = new AppState();
		Menu menu = new Menu(setting);
		Generator gen = new Generator();
		
		while(true){
			menu.startMenu(app);

			if(setting.exit()) break;
			app.screen.clear();
			app.refresh();
			List<String> strings = gen.generate(DifficultyFactory.fromEnum(setting.diff), setting, 0.1);

			UnitMode game = GameModeFactory.fromSetting(setting);
			GameResult gr = game.play(app, strings);

			Result.showSummary(app, gr.summary());

			setting.stopStart();
		}


		app.cleanUp();


	}

}
