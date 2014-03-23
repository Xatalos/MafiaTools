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

    private String Name;
    private String Password;

    public User(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }

    private User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public static List<User> getKayttajat() {
        try {
            String sql = "SELECT id, tunnus, salasana from kayttajat";
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            ResultSet tulokset = kysely.executeQuery();

            ArrayList<User> kayttajat = new ArrayList<User>();
            while (tulokset.next()) {
                try {
                    //Luodaan tuloksia vastaava olio ja palautetaan olio:
                    User user = new User();
                    user.setName(tulokset.getString("tunnus"));
                    user.setPassword(tulokset.getString("salasana"));

                    kayttajat.add(user);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Suljetaan kaikki resutuloksetsit:
            try {
                tulokset.close();
            } catch (Exception e) {
            }
            try {
                kysely.close();
            } catch (Exception e) {
            }
            try {
                yhteys.close();
            } catch (Exception e) {
            }

            return kayttajat;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}