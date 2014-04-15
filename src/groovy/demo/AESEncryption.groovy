package demo


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

/**
 * Created by KRI_108 on 4/15/14.
 * http://www.simplecodestuffs.com/encryption-and-decryption-of-data-using-aes-algorithm-in-java-2/
 * http://www.software-architect.net/articles/using-strong-encryption-in-java/using-stronger-security-algorithms.html
 */
class AESEncryption {

    private static String algorithm = "AES";
//    private static byte[] keyValue = new byte[] { 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    private static byte[] keyValue = "ASecureSecretKey".getBytes();


    // Performs Encryption
    public static String encrypt(String plainText) throws Exception
    {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    // Performs decryption
    public static String decrypt(String encryptedText) throws Exception
    {
        // generate key
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

//generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }

}
