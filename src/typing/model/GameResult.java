package typing.model;

public class GameResult {

    // Raw metrics
    public final int wordsTyped;      // number of completed words
    public final int charsTyped;      // total characters typed (typedWords + currentTyped)
    public final int keyPresses;      // total key presses
    public final int mistakes;        // incorrect keystrokes
    public final long durationSec;     // duration in seconds

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


    // ============================
    //      PERFORMANCE METRICS
    // ============================

    /** Standard WPM: (correct characters / 5) / minutes */
    public double wpm() {
        if (durationSec <= 0) return 0;
        double minutes = durationSec / 60.0;
        return (charsCorrect() / 5.0) / minutes;
    }

    /** CPM: correct characters per minute */
    public double cpm() {
        if (durationSec <= 0) return 0;
        double minutes = durationSec / 60.0;
        return charsCorrect() / minutes;
    }

    /** Raw accuracy (correct keystrokes / total keystrokes) */
    public double accuracy() {
        int total = keyPresses;
        if (total == 0) return 100.0;
        return (charsCorrect() * 100.0) / total;
    }

    /** How many characters were correct (keypresses - mistakes) */
    public int charsCorrect() {
        return Math.max(0, keyPresses - mistakes);
    }

    /** Error rate (%) */
    public double errorRate() {
        if (keyPresses == 0) return 0;
        return (mistakes * 100.0) / keyPresses;
    }

    /** Words per second, just for debugging */
    public double wps() {
        if (durationSec <= 0) return 0;
        return wordsTyped / (double) durationSec;
    }

    /** Characters per second */
    public double cps() {
        if (durationSec <= 0) return 0;
        return charsCorrect() / (double) durationSec;
    }


    // ============================
    //         SUMMARY STRING
    // ============================

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

