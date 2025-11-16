package typing.console;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

import java.util.List;

public class TypingSessionRenderer {

    private final int xMax;
    private final int startX;
    private final int startY;

    public TypingSessionRenderer(int xMax) {
        this.xMax = xMax;
        this.startX = 0;
        this.startY = 0;
    }

    public TypingSessionRenderer(int xMax, int startX, int startY) {
        this.xMax = xMax;
        this.startX = startX;
        this.startY = startY;
    }

    public void renderSession(
            TextGraphics g,
            int x,
            int y,
            List<String> targetWords,
            List<String> typedWords,
            String currentTyped
    ) {

        int cx = x;
        int cy = y;

        for (int i = 0; i < typedWords.size(); i++) {

            String target = targetWords.get(i);
            String typed  = typedWords.get(i);

            int[] pos = renderWord(g, cx, cy, target, typed, -1);
            cx = pos[0];
            cy = pos[1];

            if (cx >= xMax) {
                cx = startX;
                cy++;
            }

            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(cx++, cy, " ");
        }

        if (typedWords.size() < targetWords.size()) {

            String target = targetWords.get(typedWords.size());

            int[] pos = renderWord(g, cx, cy, target, currentTyped, currentTyped.length());
            cx = pos[0];
            cy = pos[1];

            if (cx >= xMax) {
                cx = startX;
                cy++;
            }

            g.putString(cx++, cy, " ");
        }

        for (int i = typedWords.size() + 1; i < targetWords.size(); i++) {

            String word = targetWords.get(i);

            for (int j = 0; j < word.length(); j++) {

                if (cx >= xMax) {
                    cx = startX;
                    cy++;
                }

                g.setForegroundColor(TextColor.ANSI.WHITE);
                g.putString(cx++, cy, String.valueOf(word.charAt(j)));
            }

            if (cx >= xMax) {
                cx = startX;
                cy++;
            }

            g.putString(cx++, cy, " ");
        }
    }


    private int[] renderWord(TextGraphics g, int cx, int cy,
                             String target, String typed, int cursorpos) {

        int max = Math.max(target.length(), typed.length());

        for (int i = 0; i < max; i++) {

            if (cx >= xMax) {
                cx = startX;
                cy++;
            }

            char tc = (i < target.length()) ? target.charAt(i) : ' ';
            Character uc = (i < typed.length()) ? typed.charAt(i) : null;

            TextColor color;
            char renderChar;

            if (uc == null) {
                color = TextColor.ANSI.WHITE;
                renderChar = tc;
            }
            else if (tc == uc) {
                color = TextColor.ANSI.GREEN;
                renderChar = uc;
            }
            else {
                color = TextColor.ANSI.RED;
				if (i < target.length()){
					renderChar = tc;
				} else
                	renderChar = uc;
            }

			if (i == cursorpos) {
         	   g.setBackgroundColor(TextColor.ANSI.BLACK_BRIGHT);
			}

            g.setForegroundColor(color);
            g.putString(cx++, cy, String.valueOf(renderChar));
			g.setBackgroundColor(TextColor.ANSI.DEFAULT);
        }

        return new int[]{cx, cy};
    }
}

