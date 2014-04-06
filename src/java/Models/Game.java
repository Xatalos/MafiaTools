/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Game {
    
    private String id;
    private String Name;
    private String UserID;

    public Game(String id, String Name, String UserID) {
        this.id = id;
        this.Name = Name;
        this.UserID = UserID;
    }

    private Game() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getUserID() {
        return UserID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    
    public static List<Game> getGames() {
        try {
            String sql = "SELECT gamename from game";
            Connection connection = Database.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet results = query.executeQuery();

            List<Game> games = new ArrayList<Game>();
            while (results.next()) {
                try {
                    //Luodaan tuloksia vastaava olio ja palautetaan olio:
                    Game game = new Game();
                    game.setName(results.getString("gamename"));

                    games.add(game);
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

            return games;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("problems at the server side");
        }

    }
    
}
