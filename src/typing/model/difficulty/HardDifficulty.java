package typing.model.difficulty;

import typing.model.constant.WordType;

public class HardDifficulty extends BaseDifficulty{
	public HardDifficulty(){
		super.weights.put(WordType.MEDIUM_WORD, 0.3);
		super.weights.put(WordType.HARD_WORD, 0.5);
		super.weights.put(WordType.EXTENDED_WORD, 0.2);
	}
}
