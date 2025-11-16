package typing.model.difficulty;

import typing.model.constant.WordType;

public class CodeDifficulty extends BaseDifficulty{
	public CodeDifficulty(){
		super.weights.put(WordType.CODE_WORD, 1.0);
	}
}
