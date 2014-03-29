package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teemu
 */
public class User {

    private int id;
    private String Name;
    private String Password;

    public User(int id, String Name, String Password) {
        this.id = id;
        this.Name = Name;
        this.Password = Password;
    }

    public User() {
    }

//    public boolean removeUser() throws Exception {
//        Connection connection = null;
//        PreparedStatement query = null;
//
//        try {
//            String sql = "DELETE FROM kissat where id = ?";
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
            throw new IllegalStateException("userssissa ongelmia");
        }

    }
    
    public int getID() {
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
    
    public void setID(int id) {
        this.id = id;
    }
}