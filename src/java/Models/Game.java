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
import javax.naming.NamingException;

/**
 * A model for a game of Mafia
 *
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class Game {

    private int id;
    private String name;
    private int userid;

    public Game(int id, String Name, int UserID) {
        this.id = id;
        this.name = Name;
        this.userid = UserID;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setUserID(int UserID) {
        this.userid = UserID;
    }

    /**
     * Fetches all the games created by a specified user from the database
     *
     * @param userid the identification number of the specified user
     *
     * @return games all the games created by a specified user
     */
    public static List<Game> getGames(int userid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet results = null;
        try {
            sql = "SELECT gameid, gamename, game.userid from game, username where game.userid = username.userid and username.userid = ? ORDER BY gamename";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, userid);
            results = query.executeQuery();

            List<Game> games = new ArrayList<Game>();
            while (results.next()) {
                try {
                    //Luodaan tuloksia vastaava olio ja palautetaan olio:
                    Game game = new Game();
                    game.setId(Integer.parseInt(results.getString("gameid")));
                    game.setName(results.getString("gamename"));
                    game.setUserID(Integer.parseInt(results.getString("userid")));

                    games.add(game);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return games;
        } finally {
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
        }
    }

    /**
     * Fetches a specified game from the database
     *
     * @param id the identification number of the game
     *
     * @throws SQLException if an SQL error occurs
     *
     * @return game the specified game
     */
    public static Game getGame(int id) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT gameid, gamename, userid from game where gameid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, id);
            rs = query.executeQuery();

            Game game = null;
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            if (rs.next()) {
                game = new Game();
                game.setId(Integer.parseInt(rs.getString("gameid")));
                game.setName(rs.getString("gamename"));
                game.setUserID(Integer.parseInt(rs.getString("userid")));
            }
            return game;
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
     * Removes a specified game from the database
     *
     * @param id the identification number of the game
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void deleteGame(int id) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "DELETE FROM game WHERE gameid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, id);
            rs = query.executeQuery();
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
     * Edits a specified game from the database
     *
     * @param id the identification number of the game
     * @param name the new name of the game
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void renameGame(int id, String name) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "UPDATE game SET gamename = ? WHERE gameid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setString(1, name);
            query.setInt(2, id);
            rs = query.executeQuery();
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
     * Inserts a new game into the database
     *
     * @param name the name of the new game
     * @param userid the identification number of the user who creates the game
     *
     * @throws SQLException if an SQL error occurs
     * @throws namingException if a naming error occurs
     */
    public static void createGame(String name, int userid) throws NamingException, SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet ids = null;
        try {
            sql = "INSERT INTO game(gamename, userid) VALUES(?,?) RETURNING gameid";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);

            query.setString(1, name);
            query.setInt(2, userid);

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
}
