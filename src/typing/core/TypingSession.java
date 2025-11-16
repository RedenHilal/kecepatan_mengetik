package typing.core;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.List;
import java.util.ArrayList;

public class TypingSession {

    private int mistakes = 0;
    private int keyPress = 0;

    private int listIndex = 0;
    private List<String> words;
	private List<String> typedWords;
    private String wordNow;

    private StringBuilder typed = new StringBuilder();

    public TypingSession(List<String> _words) {
        this.words = _words;
		this.typedWords = new ArrayList<>();
        this.wordNow = words.get(0);
    }

    public void processKey(KeyStroke ks) {
		if(ks == null) return;
        if (ks.getKeyType() == KeyType.Character) {
            char ch = ks.getCharacter();

            if (ch == ' ') {
				typedWords.add(typed.toString());
                checkMistakesForWord();
                advanceWord();
                typed.setLength(0);
                return;
            }

            int pos = typed.length();
            if (pos >= wordNow.length() || ch != wordNow.charAt(pos)) {
                mistakes++; 
            }

        	keyPress++;
            typed.append(ch);

        }

        else if (ks.getKeyType() == KeyType.Backspace) {
            if (typed.length() > 0) {
                typed.deleteCharAt(typed.length() - 1);
            }
        }
    }

    private void checkMistakesForWord() {

        String t = typed.toString();
        int len = Math.max(t.length(), wordNow.length());
        for (int i = 0; i < len; i++) {
            char tc = (i < t.length()) ? t.charAt(i) : '\0';
            char wc = (i < wordNow.length()) ? wordNow.charAt(i) : '\0';
            if (tc != wc) mistakes++;
        }
    }

    private void advanceWord() {
        listIndex++;
        if (listIndex < words.size()) {
            wordNow = words.get(listIndex);
        }
    }

	public int getIndex(){
		return listIndex;
	}

    public boolean finished() {
        return listIndex >= words.size();
    }

    public List<String> getTyped() {
        return typedWords;
    }

    public String getCurrentTyped() {
        return typed.toString();
    }

    public int getMistakes() {
        return mistakes;
    }

    public int getKeyPress() {
        return keyPress;
    }
}

