package typing.console;

import typing.core.AppState;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;

public class Result {

    public static void showSummary(AppState app, String summary) {
        app.screen.clear();

        String[] lines = summary.split("\n");
        for (int i = 0; i < lines.length; i++) {
            app.tg.putString(1, 1 + i, lines[i]);
        }

		app.tg.putString(1, lines.length + 2, "Press enter to proceed");

        app.refresh();
        KeyStroke ks;
        do {
            ks = app.readKey();
        }
        while (ks.getKeyType() != KeyType.Enter);
    }
}

