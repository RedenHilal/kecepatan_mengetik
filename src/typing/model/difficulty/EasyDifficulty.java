package typing.model.difficulty;

import typing.model.constant.WordType;

public class EasyDifficulty extends BaseDifficulty{
	public EasyDifficulty(){
		super.weights.put(WordType.EASY_WORD, 0.6);
		super.weights.put(WordType.MEDIUM_WORD, 0.4);
	}	
}
