import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.Object;

public class MD5 {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexS = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hash.length == 1) {
                hexS.append('0');
            }
            hexS.append(hex);
        }
        return hexS.toString();
    }
    
    public static boolean compare(String f1, String f2) {
        if (f1.length() != f2.length()) {
            return false;
        }

        for (int i = 0; i < f1.length(); i++) {
            if (f1.charAt(i) != f2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
