package typing.word.stash;

import java.io.*;
import java.nio.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Stash {
		private final String path;

		public Stash(String _path){
			path = _path;
		}

		public List<String> loadList() {
			try (var stream = Stash.class.getResourceAsStream(path);
            	var reader = new BufferedReader(new InputStreamReader(stream))) {
            	return reader.lines().collect(Collectors.toList());
        	} catch (Exception e) {
            	throw new RuntimeException("Cannot load word list: " + path, e);
        	}	
		}
}
