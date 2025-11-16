package typing.model.unitmode;

import typing.core.AppState;
import typing.model.GameResult;

import java.io.IOException;
import java.util.List;

public interface UnitMode {
    GameResult play(AppState app, List<String> words) throws IOException;
}

