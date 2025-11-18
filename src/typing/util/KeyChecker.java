package typing.util;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;


public class KeyChecker {
	public static boolean isEscape(KeyStroke ks){
		if (ks == null) return false;
		KeyType kt = ks.getKeyType();

		if(kt == KeyType.Escape){
			return true;
		} else {
			return false;
		}
	}

}
