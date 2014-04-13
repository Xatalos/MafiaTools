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
 * A model for a participant of a Mafia game (a single player in a single game)
 * 
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class Participant {
    
    private int gameid;
    private int playerid;
    private int points;
    private String notes;
    private String name;
    private String meta;
    
    public Participant() {
    }

    public String getName() {
        return name;
    }

    public int getGameid() {
        return gameid;
    }

    public int getPlayerid() {
        return playerid;
    }

    public int getPoints() {
        return points;
    }

    public String getNotes() {
        return notes;
    }

    public String getMeta() {
        return meta;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
    
    public static void addParticipant(int gameid, int playerid) throws SQLException {
        String sql = "INSERT INTO participant(gameid, playerid) VALUES(?,?)";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);

        query.setInt(1, gameid);
        query.setInt(2, playerid);

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
    
    public static List<Participant> getParticipants(int gameid) throws SQLException {
        String sql = "SELECT gameid, playerid, points, notes from participant where gameid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setInt(1, gameid);
        ResultSet results = query.executeQuery();
        
        Player player = null;
        List<Participant> participants = new ArrayList<Participant>();
        while (results.next()) {
            try {
                //Luodaan tuloksia vastaava olio ja palautetaan olio:
                player = Player.getPlayer(Integer.parseInt(results.getString("playerid")));
                Participant participant = new Participant();
                participant.setGameid(Integer.parseInt(results.getString("gameid")));
                participant.setPlayerid(Integer.parseInt(results.getString("playerid")));
                participant.setPoints(Integer.parseInt(results.getString("points")));
                participant.setNotes(results.getString("notes"));
                participant.setName(player.getName());
                participant.setMeta(player.getMeta());

                participants.add(participant);
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
        return participants;
    }
    
    public static void removeParticipant(int gameid, int playerid) throws SQLException {
        String sql = "DELETE FROM participant WHERE gameid = ? and playerid = ?";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        query.setInt(1, gameid);
        query.setInt(2, playerid);
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
