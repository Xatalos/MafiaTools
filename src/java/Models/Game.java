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
 *
 * @author Teemu
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

    // TODO!!!!!! (count(jne.))
    public static int countGames(User user) {
        try {
            int count = 0;
            String sql = "SELECT gameid, gamename, userid from game ORDER BY gamename";
            Connection connection = Database.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet results = query.executeQuery();

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
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("problems at the server side");
        }
    }

    public static List<Game> getGames() {
        try {
            String sql = "SELECT gameid, gamename, userid from game ORDER BY gamename";
            Connection connection = Database.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet results = query.executeQuery();

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

    public static Game getGame(int id) throws SQLException {
        String sql = "SELECT gameid, gamename, userid from game where gameid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setInt(1, id);
        ResultSet rs = query.executeQuery();

        Game game = null;
        //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
        //ja asetetaan palautettava olio:
        if (rs.next()) {
            game = new Game();
            game.setId(Integer.parseInt(rs.getString("gameid")));
            game.setName(rs.getString("gamename"));
            game.setUserID(Integer.parseInt(rs.getString("userid")));
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
        return game;
    }

    public static void deleteGame(int id) throws SQLException {
        String sql = "DELETE FROM game WHERE gameid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setInt(1, id);
        ResultSet rs = query.executeQuery();

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

    public static void renameGame(int id, String newName) throws SQLException {
        String sql = "UPDATE game SET gamename = ? WHERE gameid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setString(1, newName);
        query.setInt(2, id);
        ResultSet rs = query.executeQuery();

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

    /**
     *
     * @throws NamingException
     * @throws SQLException
     */
    public static void createGame(String name) throws NamingException, SQLException {
        String sql = "INSERT INTO game(gamename, userid) VALUES(?,?) RETURNING gameid";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1, name);
        query.setInt(2, 1);

        ResultSet ids = query.executeQuery();
        ids.next();

        //Haetaan RETURNING-määreen palauttama id.
        //HUOM! Tämä toimii ainoastaan PostgreSQL-kannalla!
        int id = ids.getInt(1);

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

//        return Game.getGame(id);

    }
}
