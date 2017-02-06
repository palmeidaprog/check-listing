package com.github.palmeidaprog.checklisting.data;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by paulo on 2/3/17.
 */
public class EncryptPass {
    private String generatedSecuredPasswordHash;

    public EncryptPass(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        generatedSecuredPasswordHash = generateStorngPasswordHash(password);
        System.out.println("String:" + password); // @debug
        System.out.println("hash:" + generatedSecuredPasswordHash); // @debug
    }

    // How to Use it
/*    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String  originalPassword = "password";
        String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
        System.out.println(generatedSecuredPasswordHash);
    }*/

    private static String generateStorngPasswordHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
        System.out.println(salt); // @debug

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
