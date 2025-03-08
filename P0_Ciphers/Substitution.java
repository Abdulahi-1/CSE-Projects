
import java.util.*;
// This class represents a Substitution cipher, where each character in the input is replaced
// by a corresponding character from a specified key.
public class Substitution extends Cipher {
    private String encoding;

    // Behavior:
    //   - Initializes the Substitution cipher with an encoding key.
    // Parameters:
    //   - N/A
    // Returns: 
    //   - N/A
    // Exceptions:
    //   - N/A
    public Substitution() {
        this.encoding = null;
    }

    // Behavior:
    //   - sets the encoding for the given input.
    // Parameters:
    //   - encoding: inputted encoding string
    // Returns: 
    //   - N/A
    // Exceptions:
    //   - if inputted encoding is null or the length doesnt equal total num of characters,
    //   _ an IllegalArgumentException is thrown.

    public Substitution(String encoding) {
        setEncoding(encoding);
    }

    // Behavior:
    //   - sets the encoding for the given input.
    // Parameters:
    //   - encoding: inputted encoding string
    // Returns: 
    //   - N/A
    // Exceptions:
    //   - if inputted encoding is null or the length doesnt equal the total num of characters
    //   _ IllegalArgumentException is thrown.

    public void setEncoding(String encoding) {
        if (encoding == null || encoding.length() != Cipher.TOTAL_CHARS) {
                throw new IllegalArgumentException();
            }
        for (int i = 0; i < encoding.length(); i++) {
            char letter = encoding.charAt(i);
            if (letter < Cipher.MIN_CHAR || letter > Cipher.MAX_CHAR) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < encoding.length(); j++) {
                if (encoding.charAt(j) == letter) {
                    throw new IllegalArgumentException();
                }
            }
        }
        this.encoding = encoding;
    }

    // Behavior:
    //   - Encrypts the input string by replacing each character 
    // with the corresponding character in the encoding. This method only encodes
    // acceptable characters that are within the range A-G.
    //  characters outside this range will not be encrypted. 
    // Parameters:
    //   - input: the string to be encrypted.
    // Returns:
    //   - The encrypted string.
    // Exceptions:
    //   - if input isn't inputted, IllegalArgumentException is thrown.
    //   - if the encoding is empty, IllegalStateException is thrown.

    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (this.encoding == null) {
            throw new IllegalStateException();
        }
        String encryptCode = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int range = character - Cipher.MIN_CHAR;
            encryptCode += this.encoding.charAt(range);
        }
        return encryptCode;
    }

    // Behavior:
    //   - Decrypts the input string by reversing the encryption.
    // Parameters:
    //   - input: the string to be decrypted.
    // Returns:
    //   - The decrypted string.
    // Exceptions: 
    //   - if input is null, IllegalArgumentException is thrown.
    //   - if encoding is null, IllegalStateException is thrown.

    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (this.encoding == null) {
            throw new IllegalStateException();
        }
        String decryptCode = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int range = this.encoding.indexOf(character);
            decryptCode += (char) (range + Cipher.MIN_CHAR);
        }
        return decryptCode;
    }
}
