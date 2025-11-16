package typing.core;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class TypingEngine {
    private final String target;
    private final StringBuilder typed = new StringBuilder();

    public TypingEngine(String target) {
        this.target = target;
    }

    public void processKey(KeyStroke ks) {
        if (ks == null) return;

        if (ks.getKeyType() == KeyType.Character) {
            typed.append(ks.getCharacter());
        }
        else if (ks.getKeyType() == KeyType.Backspace && typed.length() > 0) {
            typed.deleteCharAt(typed.length() - 1);
        }
    }

    public boolean isComplete() {
        return typed.toString().equals(target);
    }

    public boolean submitted() {
        return typed.length() > 0 && typed.charAt(typed.length() - 1) == ' ';
    }

    public int mistakes() {
        int m = 0;
        for (int i = 0; i < typed.length(); i++) {
            if (i >= target.length()) m++;
            else if (typed.charAt(i) != target.charAt(i)) m++;
        }
        return m;
    }

    public String getTyped() { return typed.toString(); }
    public String getTarget() { return target; }
    public void reset(String newTarget) {
        typed.setLength(0);
    }
}

