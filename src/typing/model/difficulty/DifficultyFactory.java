package typing.model.difficulty;

import typing.model.constant.Difficulty;

public final class DifficultyFactory {

    private DifficultyFactory() {
    }

    public static BaseDifficulty fromEnum(Difficulty diff) {
        return switch (diff) {
            case EASY   -> new EasyDifficulty();
            case MEDIUM -> new MediumDifficulty();
            case HARD   -> new HardDifficulty();
            case HELL   -> new HellDifficulty();
            case CODE   -> new CodeDifficulty();
            default     -> new MediumDifficulty();
        };
    }
}

