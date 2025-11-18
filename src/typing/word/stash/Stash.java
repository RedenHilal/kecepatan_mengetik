package typing.word.stash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Stash {
    private final String path;

    public Stash(String _path) {
        this.path = _path;
    }

    public List<String> loadList() {
        try (var stream = Stash.class.getResourceAsStream(path);
             var reader = new BufferedReader(new InputStreamReader(stream))) {

            return reader.lines()
                .map(line -> line
                    .replace("\uFEFF", "")
                    .replace("\u200B", "") 
                    .replace("\u200C", "")
                    .replace("\u200D", "")
                    .replace("\u00AD", "")  
                    .trim()
                )
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Cannot load word list: " + path, e);
        }
    }
}

