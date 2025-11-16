package typing.model.difficulty;

import typing.model.constant.WordType;

public class MediumDifficulty extends BaseDifficulty{
		public MediumDifficulty(){
			super.weights.put(WordType.EASY_WORD, 0.1);
			super.weights.put(WordType.MEDIUM_WORD, 0.5);
			super.weights.put(WordType.HARD_WORD, 0.4);
		}
}
