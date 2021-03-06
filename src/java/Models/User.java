package Models;

import static Models.Player.editPlayer;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * A model for a user of the database
 *
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class User {

    // eli passwordin paikalle tallennetaan hash ja kun tarkistetaan salasanaa, niin verrataan vain annetun salasanan muodostamaa hashia ja 
    // tietokannassa olevaa hashia...
    private int id;
    private String name;
    private String password;
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public User(int id, String Name, String Password) {
        this.id = id;
        this.name = Name;
        this.password = Password;
    }

    public User() {
    }

    /**
     * Fetches a specific user from the database
     *
     * @param username the name of the user
     *
     * @throws SQLException if an SQL error occurs
     *
     * @return user the specified user
     */
    public static User getUser(String username) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT userid, name, password from username where name = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setString(1, username);
            rs = query.executeQuery();

            User user = null;
            
            if (rs.next()) {
                user = new User();
                user.setID(Integer.parseInt(rs.getString("userid")));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            return user;

        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                query.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * Fetches all the users from the database
     *
     * @return users all users from the database
     */
    public static List<User> getUsers() throws SQLException {
        List<User> users = null;
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT name from username;";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            ResultSet results = query.executeQuery();

            users = new ArrayList<User>();
            while (results.next()) {
                try {
                    User user = new User();
                    user.setName(results.getString("name"));

                    users.add(user);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return users;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                query.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Inserts a new user into the database
     *
     * @param name the name of the new user
     * @param the password of the new user
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void createUser(String name, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet ids = null;
        try {
            sql = "INSERT INTO username(name, password) VALUES(?,?)";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            
            String hashedPassword = createHash(password);

            query.setString(1, name);
            query.setString(2, hashedPassword);

            ids = query.executeQuery();
            ids.next();

        } finally {
            try {
                ids.close();
            } catch (Exception e) {
            }
            try {
                query.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Checks if the specified name is available for a new user
     *
     * @param name the searched player name
     *
     * @throws SQLException if an SQL error occurs
     *
     * @return true if the name is available, false if not
     */
    public static boolean isNameAvailable(String name) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT name from username where name = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setString(1, name);
            rs = query.executeQuery();

            User user = null;

            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
            }
            if (user == null) {
                return true;
            } else {
                return false;
            }
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                query.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public void setID(int id) {
        this.id = id;
    }
    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTE_SIZE = 24;
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 1000;
    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    public static String createHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    public static String createHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param password the password to check
     * @param correctHash the hash of the valid password
     * @return true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), correctHash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param password the password to check
     * @param correctHash the hash of the valid password
     * @return true if the password is correct, false if not
     */
    public static boolean validatePassword(char[] password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // Compute the hash of the provided password, using the same salt, 
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * Computes the PBKDF2 hash of a password.
     *
     * @param password the password to hash.
     * @param salt the salt
     * @param iterations the iteration count (slowness factor)
     * @param bytes the length of the hash to compute in bytes
     * @return the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }
}