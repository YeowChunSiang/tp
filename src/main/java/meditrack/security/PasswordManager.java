package meditrack.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * A stateless utility component dedicated to application security.
 * Handles hashing and verification of the master application password.
 */
public class PasswordManager {

    // A cost factor of 12 provides a strong balance of security and performance
    private static final int WORK_FACTOR = 12;

    /**
     * Hashes a plain-text password for storage.
     *
     * @param plainText password from the user
     * @return BCrypt hash string
     */
    public static String hashPassword(String plainText) {
        // Generate a salt with the specified work factor and hash the password
        String salt = BCrypt.gensalt(WORK_FACTOR);
        return BCrypt.hashpw(plainText, salt);
    }

    /**
     * Checks a password against a stored BCrypt hash.
     *
     * @param plainTextPassword password from the user
     * @param storedHash hash from storage
     * @return {@code true} if they match
     */
    public static boolean checkPassword(String plainTextPassword, String storedHash) {
        if (storedHash == null || !storedHash.startsWith("$2a$")) {
            return false; // Invalid hash format
        }
        return BCrypt.checkpw(plainTextPassword, storedHash);
    }
}