package typing.model.constant;

public enum Difficulty implements BaseConstant<Difficulty>{
	EASY,
	MEDIUM,
	HARD,
	HELL,
	CODE;

	public static final Difficulty[] diffs = Difficulty.values();

	public Difficulty next(){
		return diffs[(this.ordinal() + 1) % diffs.length];
	}

	public Difficulty prev(){
		return diffs[(this.ordinal() + 1) % diffs.length];
	}

	public Difficulty getDefault(){
		return diffs[0];
	}

	public int getLength(){
		return diffs.length;
	}

	public String[] getStrings(){
		return new String[] {
			"Easy",
			"Medium",
			"Hard",
			"Hell",
			"Code"
		};
	}
}
