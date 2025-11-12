package typing.core;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.io.IOException;

public class AppState {
	public Terminal terminal;
	public Screen screen;
	public TextGraphics tg;

	public AppState() throws Exception {

		terminal = new DefaultTerminalFactory().createTerminal();

		screen = new TerminalScreen(terminal);
		screen.startScreen();
		tg = screen.newTextGraphics();
	}

	public void cleanUp() throws Exception{
		screen.stopScreen();
	}

	public KeyStroke readKey(){
		try (KeyStroke key = screen.readInput()){
			return key;
		} catch (IOException){
			e.printStackTrace();
		}
	}

	public void cleanUp(){
		try {
			screen.stopScreen();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
