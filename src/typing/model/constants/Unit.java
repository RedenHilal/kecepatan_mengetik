package typing.model.constant;


public enum Unit implements BaseConstant<Unit> {
	WORD,
	SECOND;

	public static final Unit[] units = Unit.values();

	public Unit next(){
		return units[(this.ordinal() + 1) % units.length];
	}

	public Unit prev(){
		return units[(this.ordinal() - 1 + units.length) % units.length];
	}

	public Unit getDefault(){
		return units[0];
	}

	public int getLength(){
		return units.length;
	}

	public String[] getStrings(){
		return new String[]{
			"Words",
			"Seconds"
		};
	}
} 
