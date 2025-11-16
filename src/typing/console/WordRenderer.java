package typing.console;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import java.awt.Color;

public class WordRenderer {

    public void render(TextGraphics g, int x, int y, String target, String typed) {
        int min = Math.min(target.length(), typed.length());
        for (int i = 0; i < min; i++) {
            char c = target.charAt(i);
            char t = typed.charAt(i);

            if (c == t) {
                g.setForegroundColor(TextColor.ANSI.GREEN);
            } else {
                g.setForegroundColor(TextColor.ANSI.RED);
            }

			if (i > target.length()){
            	g.putString(x + i, y, String.valueOf(typed.charAt(i)));
			} else {
            	g.putString(x + i, y, String.valueOf(target.charAt(i)));
			}
        }

        for (int i = target.length(); i < typed.length(); i++) {
            g.setForegroundColor(TextColor.ANSI.RED);
            g.putString(x + i, y, String.valueOf(typed.charAt(i)));
        }

        g.setForegroundColor(TextColor.ANSI.WHITE);
        for (int i = typed.length(); i < target.length(); i++) {
            g.putString(x + i, y, String.valueOf(target.charAt(i)));
        }
    }
}

