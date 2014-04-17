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
    private String meta;
    private int userid;

    public Player(int id, String name, String meta, int userid) {
        this.id = id;
        this.name = name;
        this.meta = meta;
        this.userid = userid;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMeta() {
        return meta;
    }

    public int getUserid() {
        return userid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Fetches all the players created by a specified user from the database
     *
     * @param userid the identification number of the specified user
     *
     * @return players all the players created by a specified user
     */
    public static List<Player> getPlayers(int userid) throws SQLException {
        List<Player> players = null;
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet results = null;
        try {
            sql = "SELECT playerid, playername, meta, player.userid from player, username where username.userid = player.userid and username.userid = ? ORDER BY playername";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, userid);
            results = query.executeQuery();

            players = new ArrayList<Player>();
            while (results.next()) {
                try {
                    Player player = new Player();
                    player.setId(Integer.parseInt(results.getString("playerid")));
                    player.setName(results.getString("playername"));
                    player.setMeta(results.getString("meta"));
                    player.setUserid(Integer.parseInt(results.getString("userid")));

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
     * @param userid the identification number of the user who creates the
     * player
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void createPlayer(String name, String meta, int userid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet ids = null;
        try {
            sql = "INSERT INTO player(playername, meta, userid) VALUES(?,?,?) RETURNING playerid";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);

            query.setString(1, name);
            query.setString(2, meta);
            query.setInt(3, userid);

            ids = query.executeQuery();
            ids.next();

            //Haetaan RETURNING-määreen palauttama id.
            //HUOM! Tämä toimii ainoastaan PostgreSQL-kannalla!
            int id = ids.getInt(1);
            editPlayer(id, name, meta);
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
            sql = "SELECT playerid, playername, meta, userid from player where playerid = ?";
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
                player.setName(rs.getString("playername"));
                player.setMeta(rs.getString("meta"));
                player.setUserid(Integer.parseInt(rs.getString("userid")));
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
     * Edits the name and/or meta information of a specific player
     *
     * @param id the identification number of the player
     * @param name the new name of the player
     * @param meta the new meta information for the player
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void editPlayer(int id, String name, String meta) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            // if the user hasn't entered anything into the name field, update only the 
            // meta information - otherwise both name and meta!
            if (name.equals("") || name.isEmpty()) {
                sql = "UPDATE player SET meta = ? WHERE playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setString(1, meta);
                query.setInt(2, id);
                rs = query.executeQuery();
            } else {
                sql = "UPDATE player SET playername = ?, meta = ? WHERE playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setString(1, name);
                query.setString(2, meta);
                query.setInt(3, id);
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
     * @param userid the identification number of the user whose player names
     * will be checked
     *
     * @throws SQLException if an SQL error occurs
     */
    public static boolean isNameAvailable(String name, int userid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "SELECT playerid, playername, meta, userid from player where playername = ? and userid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setString(1, name);
            query.setInt(2, userid);
            rs = query.executeQuery();

            Player player = null;
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            if (rs.next()) {
                player = new Player();
                player.setId(Integer.parseInt(rs.getString("playerid")));
                player.setName(rs.getString("playername"));
                player.setMeta(rs.getString("meta"));
                player.setUserid(Integer.parseInt(rs.getString("userid")));
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
