package typing.word;

import typing.model.constant.WordType;
import typing.model.constant.Unit;
import typing.model.difficulty.BaseDifficulty;
import typing.model.Setting;
import typing.word.stash.*;

import java.util.*;
import java.util.stream.Collectors;

public class Generator {
    private final Random rng = new Random();

   
    private final Map<WordType, List<String>> wordBank = new EnumMap<>(WordType.class);

    public Generator() {
        wordBank.put(WordType.EASY_WORD, (new EasyStash()).loadList());
        wordBank.put(WordType.MEDIUM_WORD, (new MediumStash()).loadList());
        wordBank.put(WordType.HARD_WORD,   (new HardStash()).loadList());
		wordBank.put(WordType.EXTENDED_WORD, (new ExtendedStash()).loadList());
		wordBank.put(WordType.PUNCTUATION_WORD, (new PunctuationStash()).loadList());
        wordBank.put(WordType.CODE_WORD,   (new CodeStash()).loadList());
    }

    public List<String> generate(BaseDifficulty difficulty, Setting setting, double variance) {
		int totalCount = setting.duration;
		if (setting.unit == Unit.SECOND) {
			totalCount *= 10;
		}

        Map<WordType, Double> weights = randomizeWeights(difficulty.weights, variance);

        List<String> result = new ArrayList<>();

        for (var entry : weights.entrySet()) {
            WordType type = entry.getKey();
            double proportion = entry.getValue();

            List<String> source = wordBank.getOrDefault(type, List.of());
            if (source.isEmpty()) continue;

            int count = (int) Math.round(totalCount * proportion);
            List<String> shuffled = new ArrayList<>(source);
            Collections.shuffle(shuffled, rng);

            result.addAll(shuffled.subList(0, Math.min(count, shuffled.size())));
        }

        Collections.shuffle(result, rng);
        return result;
    }

    private Map<WordType, Double> randomizeWeights(Map<WordType, Double> baseWeights, double variance) {
        Map<WordType, Double> adjusted = new EnumMap<>(WordType.class);
        double sum = 0.0;

        for (var e : baseWeights.entrySet()) {
            double multiplier = rng.nextDouble(1.0 - variance, 1.0 + variance);
            double newWeight = e.getValue() * multiplier;
            adjusted.put(e.getKey(), newWeight);
            sum += newWeight;
        }

        double finalSum = sum == 0 ? 1.0 : sum;
        for (var key : adjusted.keySet()) {
            adjusted.put(key, adjusted.get(key) / finalSum);
        }

        return adjusted;
    }

    public String availableSources() {
        return wordBank.entrySet().stream()
                .map(e -> e.getKey() + " (" + e.getValue().size() + " words)")
                .collect(Collectors.joining(", "));
    }
}

