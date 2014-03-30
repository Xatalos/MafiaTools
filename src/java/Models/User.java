package Models;

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
 *
 * @author Teemu
 */
public class User {
    
    // eli passwordin paikalle tallennetaan hash ja kun tarkistetaan salasanaa, niin verrataan vain annetun salasanan muodostamaa hashia ja 
    // tietokannassa olevaa hashia...

    private String id;
    private String Name;
    private String Password;
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public User(String id, String Name, String Password) {
        this.id = id;
        this.Name = Name;
        this.Password = Password;
    }

    public User() {
    }

//    public boolean removeUser(id) throws Exception {
//        Connection connection = null;
//        PreparedStatement query = null;
//
//        try {
//            String sql = "DELETE FROM username where UserID = id";
//            connection = Database.getConnection();
//            query = connection.getKysely(sql);
//            query.setInteger(1, id);
//            return query.execute();
//        } finally {
//            try {
//                query.close();
//            } catch (Exception e) {
//            }
//            try {
//                connection.close();
//            } catch (Exception e) {
//            }
//        }
//    }
//    public boolean saveUser() throws Exception {
//        try {
//            String sql = "INSERT INTO username(name, password) VALUES('testi', 'testi')";
//            Connection connection = Database.getConnection();
//            PreparedStatement query = connection.prepareStatement(sql);
//            ResultSet results = query.executeQuery();
//
//            List<User> users = new ArrayList<User>();
//            while (results.next()) {
//                try {
//                    //Luodaan tuloksia vastaava olio ja palautetaan olio:
//                    User user = new User();
//                    user.setName(results.getString("name"));
//
//                    users.add(user);
//                } catch (SQLException ex) {
//                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            //Suljetaan kaikki resuresultssit:
//            try {
//                results.close();
//            } catch (Exception e) {
//            }
//            try {
//                query.close();
//            } catch (Exception e) {
//            }
//            try {
//                connection.close();
//            } catch (Exception e) {
//            }
//
//            return users;
//        } catch (SQLException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            throw new IllegalStateException("userssissa ongelmia");
//        }
//        try {
//            String sql = "INSERT INTO username(name, password) VALUES('testi', 'testi')";
//            Connection connection = Database.getConnection();
//            PreparedStatement query = connection.prepareStatement(sql);
//            ResultSet results = query.executeQuery();
//            query = connection.getKysely(sql);
//            query.setString(1, nimi);
//            query.setString(2, turkinVari);
//            query.setInteger(3, rotuId);
//            results = query.executeQuery();
//
//            if (results.next()) {
//                id = results.getInteger("id");
//                return true;
//            } else {
//                return false;
//            }
//            try {
//                results.close();
//            } catch (Exception e) {
//            }
//            try {
//                query.close();
//            } catch (Exception e) {
//            }
//            try {
//                connection.close();
//            } catch (Exception e) {
//            }
//        }
//    }
    public static User getUser(String username, String password) throws SQLException {
        String sql = "SELECT userid, name, password from username where name = ? and password = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setString(1, username);
        query.setString(2, password);
        ResultSet rs = query.executeQuery();

        //Alustetaan muuttuja, joka sisältää löydetyn käyttäjän
        User loggedIn = null;

        //next-metodia on kutsuttava aina, kun käsitellään 
        //vasta kannasta saatuja ResultSet-olioita.
        //ResultSet on oletuksena ensimmäistä edeltävällä -1:llä rivillä.
        //Kun sitä kutsuu ensimmäisen kerran siirtyy se ensimmäiselle riville 0.
        //Samalla metodi myös palauttaa tiedon siitä onko seuraavaa riviä olemassa.
        if (rs.next()) {
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            loggedIn = new User();
            loggedIn.setID(rs.getString("userid"));
            loggedIn.setName(rs.getString("name"));
            loggedIn.setPassword(rs.getString("password"));
        }

        //Jos query ei tuottanut tuloksia käyttäjä on nyt vielä null.

        //Suljetaan kaikki resurssit:
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

        //Käyttäjä palautetaan vasta täällä, kun resurssit on suljettu onnistuneesti.
        return loggedIn;
    }

    public static List<User> getUsers() {
        try {
            String sql = "SELECT name from username;";
            Connection connection = Database.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet results = query.executeQuery();

            List<User> users = new ArrayList<User>();
            while (results.next()) {
                try {
                    //Luodaan tuloksia vastaava olio ja palautetaan olio:
                    User user = new User();
                    user.setName(results.getString("name"));

                    users.add(user);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Suljetaan kaikki resuresultssit:
            try {
                results.close();
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

            return users;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("problems at the server side");
        }

    }

    public String getID() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setID(String id) {
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
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(String password)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return createHash(password.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     */
    public static String createHash(char[] password)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password        the password to check
     * @param   correctHash     the hash of the valid password
     * @return                  true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String correctHash)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return validatePassword(password.toCharArray(), correctHash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password        the password to check
     * @param   correctHash     the hash of the valid password
     * @return                  true if the password is correct, false if not
     */
    public static boolean validatePassword(char[] password, String correctHash)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
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
     * @param   a       the first byte array
     * @param   b       the second byte array 
     * @return          true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    /**
     *  Computes the PBKDF2 hash of a password.
     *
     * @param   password    the password to hash.
     * @param   salt        the salt
     * @param   iterations  the iteration count (slowness factor)
     * @param   bytes       the length of the hash to compute in bytes
     * @return              the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param   hex         the hex string
     * @return              the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param   array       the byte array to convert
     * @return              a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
}