package typing.model;

import typing.util.Error;
import typing.model.constant.*;

public class Setting {

	public int duration = 0;
	public Mode mode = Mode.NOOBIES;
	public Unit unit = Unit.WORD;

	public Error setDuration(int _duration){
		if (_duration < 5 ){
			return Error.SHORT_DUR;
		}
		else if (_duration > 100){
			return Error.LONG_DUR;
		}

		duration = _duration;
		return Error.NO_ERR;
	}

	public void setMode(Mode _mode){
		mode = _mode;
	}

	public void setUnit(Unit _unit){
		unit = _unit;
	}

}
