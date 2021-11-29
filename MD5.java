import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(file.getBytes());

            //convert byte array into sig num
            BigInteger no = new BigInteger(1, messageDigest);

            //convert message to hex
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while(hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            return hashText.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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