package typing.model.unitmode;

import typing.console.TypingSessionRenderer;
import typing.core.TypingSession;
import typing.core.AppState;
import typing.model.GameResult;
import typing.util.SleepHelper;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.TerminalSize;

import java.io.IOException;
import java.util.List;


public class SecondsMode implements UnitMode {
   
	private int totalMistake = 0;
	private int seconds;

	public SecondsMode(int _seconds){
		seconds = _seconds;
	}

	public GameResult play(AppState app, List<String> words){
		TerminalSize terminalSize = app.getTerminalSize();
        TypingSessionRenderer renderer = new TypingSessionRenderer(terminalSize.getColumns());
		TypingSession ts = new TypingSession(words);
        int index = 0;

        long end = System.currentTimeMillis() + seconds * 1000;
		long start = System.currentTimeMillis();

        while (System.currentTimeMillis() < end) {
			var now = System.currentTimeMillis();
			index = ts.getIndex();

			SleepHelper.sleep(30);
            KeyStroke ks = app.pollKey();
            ts.processKey(ks);


			var g = app.tg;

			long remaining = (end - now) / 1000;
			g.putString(1, 1, "SECONDS MODE — " + remaining + "s left");

            renderer.renderSession(
                app.tg,
                1, 5,
                words,
                ts.getTyped(),
				ts.getCurrentTyped()
            );
            app.refresh();
        }

   		 return new GameResult(ts.getIndex(), ts.getKeyPress() - ts.getMistakes(), ts.getKeyPress(), ts.getMistakes(), (System.currentTimeMillis() - start) / 1000);
    }
}

