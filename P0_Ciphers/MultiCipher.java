
import java.util.*;

// This class represents a MultiCipher, which combines multiple ciphers to encrypt and
//  decrypt messages.Each cipher in the MultiCipher is applied to be encrypted and/or decrypted.
public class MultiCipher extends Cipher{
    private List<Cipher> ciphers;



    // Behavior:
    //   - Initializes the MultiCipher with a list of ciphers.
    // Parameters:
    //   - ciphers: a list of Cipher objects to be used in sequence for encryption and decryption.
    // Returns: None
    // Exceptions: if the inputted ciphers is null, then an IllegalArgumentException is thrown.
    public MultiCipher(List<Cipher> ciphers){
        if(ciphers == null){
            throw new IllegalArgumentException();
        }
        this.ciphers = ciphers;
    }


    // Behavior:
    //   - Encrypts the users input string by applying each cipher in the list of ciphers.
    // Parameters:
    //   - input: the string to be encrypted.
    // Returns:
    //   - The encrypted string after applying all ciphers in sequence.
    // Exceptions: 
    //   - if the input is null, IllegalArgumentException is thrown.

    public String encrypt(String input){
        if (input == null){
            throw new IllegalArgumentException();
        }
        String message = input;

        for (int i = 0; i < this.ciphers.size(); i++){
            Cipher text = this.ciphers.get(i);
            message = text.encrypt(message);
        }
        return message;
    }

    // Behavior:
    //   - Decrypts the input string by applying each cipher in reverse order.
    // Parameters:
    //   - input: the string to be decrypted.
    // Returns:
    //   - The decrypted string after applying all ciphers in reverse sequence.
    // Exceptions:
    //  - if input is null, IllegalArgumentException is thrown.
    public String decrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        String messageDecrypt = input;
        for(int i = this.ciphers.size() - 1; i >= 0; i-- ){
            messageDecrypt = this.ciphers.get(i).decrypt(messageDecrypt);
        }
        return messageDecrypt;
    }


}