package typing.model.unitmode;

import typing.console.TypingSessionRenderer;
import typing.core.TypingSession;
import typing.core.AppState;
import typing.model.GameResult;
import typing.util.SleepHelper;
import typing.util.KeyChecker;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;


public class WordMode implements UnitMode {
   
	private int totalMistake = 0;
	private int words;

	public WordMode(int _words){
		words = _words;
	}

	public GameResult play(AppState app, List<String> words){
		TerminalSize terminalSize = app.getTerminalSize();
        TypingSessionRenderer renderer = new TypingSessionRenderer(terminalSize.getColumns());
		TypingSession ts = new TypingSession(words);
        int index = 0;

		long start = System.currentTimeMillis() / 1000;

        while (!ts.finished()) {
			index = ts.getIndex();

			SleepHelper.sleep(30);
            KeyStroke ks = app.pollKey();
            ts.processKey(ks);
			
			if (KeyChecker.isEscape(ks)){
				return null;
			}

			var g = app.tg;

			g.setForegroundColor(TextColor.ANSI.WHITE);
			g.setBackgroundColor(TextColor.ANSI.DEFAULT);
			g.putString(1, 1, "WORD MODE — " + (this.words - ts.getIndex()) + "word(s) left");

            renderer.renderSession(
                app.tg,
                1, 5,
                words,
                ts.getTyped(),
				ts.getCurrentTyped()
            );
            app.refresh();
        }

        return new GameResult(ts.getIndex(), ts.getKeyPress() - ts.getMistakes(), ts.getKeyPress(), ts.getMistakes(), System.currentTimeMillis() / 1000 - start);
    }

}


