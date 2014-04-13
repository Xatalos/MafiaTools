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

    public String getName() {
        return name;
    }

    public Participant() {
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
    
    public static List<Participant> getParticipants(Game game) throws SQLException {
        String sql = "SELECT gameid, playerid, points, notes from participant";
        Connection connection = Database.getConnection();
        PreparedStatement query = connection.prepareStatement(sql);
        ResultSet results = query.executeQuery();

        List<Participant> participants = new ArrayList<Participant>();
        while (results.next()) {
            try {
                //Luodaan tuloksia vastaava olio ja palautetaan olio:
                Participant participant = new Participant();
                participant.setGameid(Integer.parseInt(results.getString("gameid")));
                participant.setPlayerid(Integer.parseInt(results.getString("playerid")));
                participant.setPoints(Integer.parseInt(results.getString("points")));
                participant.setNotes(results.getString("notes"));

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
    
    
    
}
