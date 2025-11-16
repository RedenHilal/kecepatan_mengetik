package typing.model.unitmode;

import typing.model.Setting;

public class GameModeFactory {
    public static UnitMode fromSetting(Setting setting) {
        return switch (setting.unit) {
            case WORD -> new WordMode(setting.duration);
            case SECOND -> new SecondsMode(setting.duration);
        };
    }
}

