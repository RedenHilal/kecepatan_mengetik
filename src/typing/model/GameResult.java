package typing.model;

public class GameResult {

    public final int wordsTyped; 
    public final int charsTyped;
    public final int keyPresses;
    public final int mistakes; 
    public final long durationSec;

    public GameResult(int wordsTyped,
                      int charsTyped,
                      int keyPresses,
                      int mistakes,
                      long durationSec) {
        this.wordsTyped = wordsTyped;
        this.charsTyped = charsTyped;
        this.keyPresses = keyPresses;
        this.mistakes = mistakes;
        this.durationSec = durationSec;
    }

    public double wpm() {
        if (durationSec <= 0) return 0;
        double minutes = durationSec / 60.0;
        return (charsCorrect() / 5.0) / minutes;
    }

    
    public double cpm() {
        if (durationSec <= 0) return 0;
        double minutes = durationSec / 60.0;
        return charsCorrect() / minutes;
    }

    
    public double accuracy() {
        int total = keyPresses;
        if (total == 0) return 100.0;
        return (charsCorrect() * 100.0) / total;
    }

    
    public int charsCorrect() {
        return Math.max(0, keyPresses - mistakes);
    }

    
    public double errorRate() {
        if (keyPresses == 0) return 0;
        return (mistakes * 100.0) / keyPresses;
    }

    
    public double wps() {
        if (durationSec <= 0) return 0;
        return wordsTyped / (double) durationSec;
    }

    
    public double cps() {
        if (durationSec <= 0) return 0;
        return charsCorrect() / (double) durationSec;
    }



    public String summary() {
        return String.format(
            "Words Typed: %d%n" +
            "Characters Typed: %d%n" +
            "Correct Characters: %d%n" +
            "Mistakes: %d%n" +
            "Key Presses: %d%n" +
            "Duration: %ds%n" +
            "WPM: %.2f%n" +
            "CPM: %.2f%n" +
            "Accuracy: %.2f%%",
            wordsTyped,
            charsTyped,
            charsCorrect(),
            mistakes,
            keyPresses,
            durationSec,
            wpm(),
            cpm(),
            accuracy()
        );
    }
}

