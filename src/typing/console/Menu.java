package typing.console;

import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import typing.core.AppState;
import typing.model.Setting;

import java.util.function.Consumer;
import java.util.List;

public class Menu implements InputHandler { 
	private int menuIndex = 0;

	private final List<Consumer<KeyType>> handlers =  List.of(
		this::handleMode,
		this::handleDifficulty,
		this::handleUnit,
		this::handleDuration,
		this::handleStart,
		this::exit
	);

	private Setting setting;

	public Menu(Setting _setting){
		setting = _setting;
	}

	public Setting startMenu(AppState app) {
		while(!setting.exit() && !setting.start()){
			drawMenu(app);
			handleInput(app.readKey());
		}	
		return setting;
	}

	public void handleInput(KeyStroke key){
		KeyType type = key.getKeyType();

		switch (type) {
			case ArrowDown ->
				menuIndex = Math.min(handlers.size() - 1, menuIndex + 1);
			case ArrowUp ->
				menuIndex = Math.max(0, menuIndex - 1);
			case ArrowLeft ->
				handlers.get(menuIndex).accept(type);
			case ArrowRight ->
				handlers.get(menuIndex).accept(type);
			case Enter ->
				handlers.get(menuIndex).accept(type);
			default ->
				{}
		}
	}

	public void handleStart(KeyType type){
		switch (type){
			case Enter->
				setting.doStart();
			default->
				{}
		}
	}

	public void handleMode(KeyType type){
		switch (type){
			case ArrowLeft->
				setting.mode = setting.mode.prev();
			case ArrowRight->
				setting.mode = setting.mode.next();
			default->
				{}
		}
	}

	public void handleDifficulty(KeyType type){
		switch (type){
			case ArrowLeft->
				setting.diff = setting.diff.prev();
			case ArrowRight->
				setting.diff = setting.diff.next();
			default->
				{}
		}
	}

	public void handleUnit(KeyType type){
		switch (type){
			case ArrowLeft->
				setting.unit = setting.unit.prev();
			case ArrowRight->
				setting.unit = setting.unit.next();
			default->
				{}
		}
	}

	public void handleDuration(KeyType type){
		switch (type){
			case ArrowLeft->
				setting.decDuration();
			case ArrowRight->
				setting.addDuration();
			default->
				{}
		}
	}

	public void exit(KeyType type){
		switch (type){
			case Enter->
				setting.doExit();
			default->
				{}
		}
	}

	private void drawMenu(AppState app) {
        var screen = app.screen;
        var tg = screen.newTextGraphics();
		String[] items = getMenuItems();

        screen.clear();

		for (int i = 0; i < items.length; i++){
			tg.putString(4, 3 + i, items[i]);
		}
        tg.putString(2, 1, "== SETTINGS ==");
        tg.putString(2, 3 + menuIndex, ">");

        app.refresh();
    }

	private String[] getMenuItems() {
    	return new String[] {
        	"Mode: " + setting.mode.getStrings()[setting.mode.ordinal()],
			"Difficulty: " + setting.diff.getStrings()[setting.diff.ordinal()],
        	"Unit: " + setting.unit.getStrings()[setting.unit.ordinal()],
        	"Duration: " + setting.duration + " " + setting.unit.getStrings()[setting.unit.ordinal()],
			"Start",
        	"Exit"
    	};
	}
}


