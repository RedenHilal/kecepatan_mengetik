package typing.model.difficulty;

import java.util.Map;
import java.util.HashMap;

import typing.model.constant.WordType;

public class BaseDifficulty {
	public enum Difficulty {
		EASY,
		MEDIUM,
		HARD,
		HELL,
		CODE
	};

	public Map<WordType, Double> weights;

	public BaseDifficulty(){
		weights = new HashMap<>();
	}

	public static BaseDifficulty createDifficulty(Difficulty diff ){
		return switch (diff){
			case EASY   ->  new EasyDifficulty();
			case MEDIUM ->  new MediumDifficulty();
			case HARD   ->  new HardDifficulty();
			case HELL   ->  new HellDifficulty();
			case CODE   ->  new CodeDifficulty();
			default     ->  new MediumDifficulty();
		};
	}

}
