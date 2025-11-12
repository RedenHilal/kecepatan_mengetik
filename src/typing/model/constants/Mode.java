package typing.model.constant;


public enum Mode implements BaseConstant<Mode>{
	NOOBIES,
	NORMIES;

	private static final Mode[] modes = Mode.values();

	public Mode next(){
		return modes[(this.ordinal() + 1) % modes.length];
	}

	public Mode prev(){
		return modes[(this.ordinal() - 1 + modes.length) % modes.length];
	}

	public Mode getDefault(){
		return modes[0];
	}

	public int getLength(){
		return modes.length;
	}
}
