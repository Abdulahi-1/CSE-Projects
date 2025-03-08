
import java.util.*;
// This class represents a Caesar cipher that encodes a message by key.
public class CaesarKey extends Substitution {

    // Behavior:
    //   - This method creates a Caesar key using a specified key.
    // Parameters:
    //   - key: user inputted key
    // Returns: 
    //   - N/A
    // Exceptions:
    //   - if inputted key is null or is empty, an IllegalArgumentException is thrown.
    //   - if a character is not within range, an IllegalArgumentException is thrown.
    public CaesarKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);
            if (character < Cipher.MIN_CHAR || character > Cipher.MAX_CHAR) {
                throw new IllegalArgumentException();
            }

            for (int j = i + 1; j < key.length(); j++) {
                if (key.charAt(j) == character) {
                    throw new IllegalArgumentException();
                }
            }
        }
        String encoding = Encode(key);
        super.setEncoding(encoding);
    }


    // Behavior:
    //   - This method creates an encoding for the provided key.
    // Parameters:
    //   - key: user inputted key
    // Returns: 
    //   - String: the encoding for the key
    // Exceptions:
    //   - N/A
    private String Encode(String key) {
        String encodeKey = key;
        for (int i = 0; i < Cipher.TOTAL_CHARS; i++) {
            if (encodeKey.indexOf((char) (Cipher.MIN_CHAR + i)) == -1) {
                    encodeKey += (char) (Cipher.MIN_CHAR + i);
                }
        }
        
        return encodeKey;
    }
}
