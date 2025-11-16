package typing.model.difficulty;

import typing.model.constant.WordType;


public class HellDifficulty extends BaseDifficulty{
	public HellDifficulty(){
		super.weights.put(WordType.HARD_WORD, 0.4);
		super.weights.put(WordType.EXTENDED_WORD, 0.4);
		super.weights.put(WordType.PUNCTUATION_WORD, 0.2);
	}
}
