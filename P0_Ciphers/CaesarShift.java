
import java.util.*;
// This class represents a Caesar cipher that shifts characters by a specified shift.
public class CaesarShift extends Substitution {

    // Behavior:
    //   - Initializes the CaesarKey cipher with a shift key.
    // Parameters:
    //   - key: a string representing the shift key.
    // Returns: 
    //   - N/A
    // Exceptions:
    //   - if shift is negative or zero, IllegalArgumentException is thrown.

    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException();
        }
        String encoding = encoder(shift);
        super.setEncoding(encoding);
    }

    // Behavior:
    //   - shifts the character and encodes the character.
    // Parameters:
    //   - key: a string representing the shift key.
    // Returns: 
    //   - String: encoded characters
    // Exceptions:
    //   - N/A

    private String encoder(int shift) {
        String encoded = "";
        for (int i = 0; i < Cipher.TOTAL_CHARS; i++) {
            char shiftedChar = (char) (Cipher.MIN_CHAR + ((i + shift) % Cipher.TOTAL_CHARS));
            encoded += shiftedChar;
        }
        return encoded;
    }
}



    
