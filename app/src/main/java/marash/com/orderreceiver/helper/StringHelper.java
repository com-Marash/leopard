package marash.com.orderreceiver.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility functions for String: SHA1
 *
 * @author Arash Khosravi
 */
public final class StringHelper {

    /**
     * Lower case Hex Digits.
     */
    private static final String HEX_DIGITS = "0123456789abcdef";

    /**
     * Byte mask.
     */
    private static final int BYTE_MSK = 0xFF;

    /**
     * Hex digit mask.
     */
    private static final int HEX_DIGIT_MASK = 0xF;

    /**
     * Number of bits per Hex digit (4).
     */
    private static final int HEX_DIGIT_BITS = 4;

    /**
     * Helper class.
     */
    private StringHelper() {
        //all methods are static
    }

    /**
     * Compute the SHA-1 digest of a String and return the bytes in
     * hexadecimal format.
     *
     * @param message the UTF-8 String to be encoded
     * @return a SHA-1 hash
     * @throws UnsupportedOperationException in the case the VM doesn't support UTF-8 which could
     *                                       be caused by a VM bug, you shouldn't bother catching
     *                                       this exception
     * @throws NullPointerException          if the String to be encoded is null
     */
    public static String computeSha1OfString(final String message) throws UnsupportedOperationException,
            NullPointerException {
        try {
            return computeSha1OfByteArray(message.getBytes(("UTF-8")));
        } catch (UnsupportedEncodingException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /**
     * Compute the SHA-1 digest of raw bytes and return the bytes in
     * hexadecimal format.
     *
     * @param message the raw byte array to be encoded
     * @return a SHA-1 hash
     * @throws UnsupportedOperationException in the case SHA-1 MessageDigest is not supported, you
     *                                       shouldn't bother catching this exception
     */
    private static String computeSha1OfByteArray(final byte[] message)
            throws UnsupportedOperationException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(message);
            byte[] res = md.digest();
            return toHexString(res);
        } catch (NoSuchAlgorithmException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /**
     * Compute a String in HexDigit from the input.
     *
     * @param byteArray a row byte array
     * @return a hex String
     */
    private static String toHexString(final byte[] byteArray) {
        StringBuilder sb = new StringBuilder(byteArray.length * 2);
        for (int i = 0; i < byteArray.length; i++) {
            int b = byteArray[i] & BYTE_MSK;
            sb.append(HEX_DIGITS.charAt(b >>> HEX_DIGIT_BITS)).append(
                    HEX_DIGITS.charAt(b & HEX_DIGIT_MASK));
        }
        return sb.toString();
    }
}
