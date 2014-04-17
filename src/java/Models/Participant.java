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

    /**
     * Inserts a new participant (game<->player connection) into the database
     *
     * @param gameid the identification number of the game
     * @param playerid the identification number of the game
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void addParticipant(int gameid, int playerid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet ids = null;
        try {
            sql = "INSERT INTO participant(gameid, playerid, points) VALUES(?,?,?)";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);

            query.setInt(1, gameid);
            query.setInt(2, playerid);
            query.setInt(3, 0);

            ids = query.executeQuery();
            ids.next();

            int id = ids.getInt(1);
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
     * Fetches all the participants (players) of a single game
     *
     * @param gameid the identification number of the game
     *
     * @throws SQLException if an SQL error occurs
     * 
     * @return participants a list of the participating players
     */
    public static List<Participant> getParticipants(int gameid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet results = null;
        try {
            sql = "SELECT gameid, participant.playerid, playername, points, notes from participant, player where gameid = ? and participant.playerid = player.playerid order by points, playername";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, gameid);
            results = query.executeQuery();

            Player player = null;
            List<Participant> participants = new ArrayList<Participant>();
            while (results.next()) {
                try {
                    player = Player.getPlayer(Integer.parseInt(results.getString("playerid")));
                    Participant participant = new Participant();
                    participant.setGameid(Integer.parseInt(results.getString("gameid")));
                    participant.setPlayerid(Integer.parseInt(results.getString("playerid")));
                    if (results.getString("points") != null) {
                        participant.setPoints(Integer.parseInt(results.getString("points")));
                    }
                    participant.setNotes(results.getString("notes"));
                    participant.setName(player.getName());
                    participant.setMeta(player.getMeta());

                    participants.add(participant);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return participants;
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
     * Fetches a since participant (game<->player connection)
     *
     * @param gameid the identification number of the game
     * @param playerid the identification number of the player
     *
     * @throws SQLException if an SQL error occurs
     * 
     * @return participant a single participant
     */
    public static Participant getParticipant(int gameid, int playerid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet results = null;
        try {
            sql = "SELECT points, notes from participant where gameid = ? and playerid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, gameid);
            query.setInt(2, playerid);
            results = query.executeQuery();

            Player player = null;
            Participant participant = null;
            //Kutsutaan sopivat tiedot vastaanottavaa konstruktoria 
            //ja asetetaan palautettava olio:
            if (results.next()) {
                player = Player.getPlayer(playerid);
                participant = new Participant();
                participant.setGameid(gameid);
                participant.setPlayerid(playerid);
                if (results.getString("points") != null) {
                    participant.setPoints(Integer.parseInt(results.getString("points")));
                }
                participant.setNotes(results.getString("notes"));
                participant.setName(player.getName());
                participant.setMeta(player.getMeta());
            }
            return participant;
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
     * Edits the information of a single participant
     *
     * @param gameid the identification number of the game
     * @param playerid the identification number of the player
     * @param points the points related to the participant
     * @param notes the notes related to the participant
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void editParticipant(int gameid, int playerid, int points, String notes, boolean success) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            if (success == true) {
                sql = "UPDATE participant SET points = ?, notes = ? WHERE gameid = ? and playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setInt(1, points);
                query.setString(2, notes);
                query.setInt(3, gameid);
                query.setInt(4, playerid);
                rs = query.executeQuery();
            } else {
                sql = "UPDATE participant SET notes = ? WHERE gameid = ? and playerid = ?";
                connection = Database.getConnection();
                query = connection.prepareStatement(sql);
                query.setString(1, notes);
                query.setInt(2, gameid);
                query.setInt(3, playerid);
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
     * Removes a single participant (game<->player connection) from the database
     *
     * @param gameid the identification number of the game
     * @param playerid the identification number of the player
     *
     * @throws SQLException if an SQL error occurs
     */
    public static void removeParticipant(int gameid, int playerid) throws SQLException {
        String sql = null;
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try {
            sql = "DELETE FROM participant WHERE gameid = ? and playerid = ?";
            connection = Database.getConnection();
            query = connection.prepareStatement(sql);
            query.setInt(1, gameid);
            query.setInt(2, playerid);
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
}
