package typing.core;

import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;

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

	public KeyStroke pollKey(){
		try {
			KeyStroke key = screen.pollInput();
			return key;
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	public KeyStroke readKey(){
		try {
			KeyStroke key = screen.readInput();
			return key;
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

	public void cleanUp(){
		try {
			screen.stopScreen();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void refresh(){
		try {
			screen.refresh();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public TerminalSize getTerminalSize(){
		try {
			return terminal.getTerminalSize();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
}
