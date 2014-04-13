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

    public static List<Player> getPlayers(User user) throws SQLException {
        String sql = "SELECT playerid, playername, meta, userid from player ORDER BY playername";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        ResultSet results = query.executeQuery();

        List<Player> players = new ArrayList<Player>();
        while (results.next()) {
            try {
                //Luodaan tuloksia vastaava olio ja palautetaan olio:
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
        return players;
    }

    public static void createPlayer(String name, String meta) throws SQLException {
        String sql = "INSERT INTO player(playername, meta, userid) VALUES(?,?,?) RETURNING playerid";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1, name);
        query.setString(2, meta);
        query.setInt(3, 1);

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
    }

    public static Player getPlayer(int id) throws SQLException {
        String sql = "SELECT playerid, playername, meta, userid from player where playerid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setInt(1, id);
        ResultSet rs = query.executeQuery();

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
        return player;
    }

    public static void renamePlayer(int id, String name) throws SQLException {
        String sql = "UPDATE player SET playername = ? WHERE playerid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setString(1, name);
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

    public static void editMeta(int id, String meta) throws SQLException {
        String sql = "UPDATE player SET meta = ? WHERE playerid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setString(1, meta);
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
    
    public static void deletePlayer(int id) throws SQLException {
        String sql = "DELETE FROM player WHERE playerid = ?";
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
}
