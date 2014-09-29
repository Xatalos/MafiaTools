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
 * A model for a Mafia player
 *
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class Player {

    private int id;
    private String name;
    private int points;
    private String notes;
    private String rolenotes;
    private int gameid;

    public Player(int id, String name, int points, String notes, String rolenotes, int gameid) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.notes = notes;
        this.rolenotes = rolenotes;
        this.gameid = gameid;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getNotes() {
        return notes;
    }

    public String getRolenotes() {
        return rolenotes;
    }

    public int getGameid() {
        return gameid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRolenotes(String rolenotes) {
        this.rolenotes = rolenotes;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    /**
     * Fetches all the players in a specified game from the database
     *
     * @param gameid the identification number of the specified game
     *
     * @return players all the players in a specified game
     */
    public static List<Player> getPlayers(int gameid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet results = null;
        try {
            sql = "SELECT playerid, playername, points, notes, rolenotes, gameid from player where gameid = ? order by points, playername";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, gameid);
            results = query.executeQuery();

            Player player = null;
            List<Player> players = new ArrayList<Player>();
            while (results.next()) {
                try {
                    player = new Player();
                    player.setId(Integer.parseInt(results.getString("playerid")));
                    if (results.getString("points") != null) {
                        player.setPoints(Integer.parseInt(results.getString("points")));
                    }
                    player.setNotes(results.getString("notes"));
                    player.setName(results.getString("playername"));
                    player.setRolenotes(results.getString("rolenotes"));
                    player.setGameid(Integer.parseInt(results.getString("gameid")));

                    players.add(player);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return players;
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
     * Inserts a new player into the database
     *
     * @param name the name of the new player
     * @param meta the meta information for the new player
     * @param gameid the identification number of the game
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void createPlayer(String name, int gameid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet ids = null;
        try {
            sql = "INSERT INTO player(playername, points, gameid) VALUES(?,?,?) RETURNING playerid";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);

            query.setString(1, name);
            query.setInt(2, 0);
            query.setInt(3, gameid);

            ids = query.executeQuery();
            ids.next();

            //Haetaan RETURNING-määreen palauttama id.
            //HUOM! Tämä toimii ainoastaan PostgreSQL-kannalla!
            int id = ids.getInt(1);
            //editPlayer(id, name, 0, "", "", true);
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
     * Fetches a specified player from the database
     *
     * @param id the identification number of the player
     *
     * @throws SQLException if an SQL error occurs
     *
     * @return player the specified player
     */
    public static Player getPlayer(int id) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT playerid, playername, points, notes, rolenotes, gameid from player where playerid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, id);
            rs = query.executeQuery();

            Player player = null;
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            if (rs.next()) {
                player = new Player();
                player.setId(Integer.parseInt(rs.getString("playerid")));
                if (rs.getString("points") != null) {
                    player.setPoints(Integer.parseInt(rs.getString("points")));
                }
                player.setNotes(rs.getString("notes"));
                player.setName(rs.getString("playername"));
                player.setRolenotes(rs.getString("rolenotes"));
                player.setGameid(Integer.parseInt(rs.getString("gameid")));
            }
            return player;
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
     * Edits the information of a specific player
     */
    public static void editPlayer(int id, String name, int points, String notes, String rolenotes, boolean success) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            // if the user has entered a valid number into the points field, update points, 
            // notes and meta information - otherwise only notes and meta information!
            if (success == true) {
                sql = "UPDATE player SET playername = ?, points = ?, notes = ?, rolenotes = ? WHERE playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setString(1, name);
                query.setInt(2, points);
                query.setString(3, notes);
                query.setString(4, rolenotes);
                query.setInt(5, id);
                rs = query.executeQuery();
            } else {
                sql = "UPDATE player SET playername = ?, notes = ?, rolenotes = ? WHERE playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setString(1, name);
                query.setString(2, notes);
                query.setString(3, rolenotes);
                query.setInt(4, id);
                rs = query.executeQuery();
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

    /**
     * Removes a specified player from the database
     *
     * @param id the identification number of the player
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void deletePlayer(int id) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "DELETE FROM player WHERE playerid = ?";
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
     * Checks if the specified name is available for a new or renamed player
     *
     * @param name the searched player name
     * @param gameid the identification number of game will be checked
     *
     * @throws SQLException if an SQL error occurs
     */
    public static boolean isNameAvailable(String name, int gameid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT playerid, playername from player where playername = ? and gameid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setString(1, name);
            query.setInt(2, gameid);
            rs = query.executeQuery();

            Player player = null;
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            if (rs.next()) {
                player = new Player();
                player.setId(Integer.parseInt(rs.getString("playerid")));
                player.setName(rs.getString("playername"));
                player.setGameid(gameid);
            }
            if (player == null) {
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
}
