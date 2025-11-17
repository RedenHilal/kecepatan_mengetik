package typing.model;

import typing.model.constant.*;

public class Setting {

	public int duration = 5;
	public Unit unit = Unit.WORD;
	public Difficulty diff = Difficulty.EASY;

	private boolean shouldExit = false;
	private boolean shouldStart = false;

	public void addDuration(){
		duration = Math.min(100, duration + 1);
	}

	public void decDuration(){
		duration = Math.max(5, duration - 1);
	}

	public boolean exit(){
		return shouldExit;
	}

	public boolean start(){
		return shouldStart;
	}

	public void doExit(){
		shouldExit = true;
	}

	public void stopExit(){
		shouldExit = false;
	}

	public void doStart(){
		shouldStart = true;
	}

	public void stopStart(){
		shouldStart = false;
	}

}
