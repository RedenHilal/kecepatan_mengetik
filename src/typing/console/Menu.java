package typing.console;

import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import typing.core.AppState;
import typing.model.Setting;

import java.util.function.Consumer;
import java.util.List;

public class Menu implements InputHandler { 
	private static final String[] modes = {"Noobies", "Normies"};
	private static final String[] units = {"Word", "Second"};

	private int menuIndex = 0;

	private final List<Consumer<KeyStroke>> handlers =  List.of(
		this::handleMode,
		this::handleUnit,
		this::handleDuration,
		this::exit
	);

	private Setting setting;

	public Setting menu(AppState app) {

		while(handleInput(app.readKey()));	
		return setting;
	}

	public boolean handleInput(KeyStroke key){
		switch (key) {
			case KeyType.ArrowDown:
			case KeyType.ArrowUp:
			case KeyType.ArrowLeft:
			case KeyType.ArrowRight:
			case KeyType.Enter:

		}
	}

	public void handleMode(KeyStroke key){

	}

	public void handleUnit(KeyStroke key){

	}

	public void handleDuration(KeyStroke key){
	
	}

	public void exit(KeyStroke key){
		
	}
}


