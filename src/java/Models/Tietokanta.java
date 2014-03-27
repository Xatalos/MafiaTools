package Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Teemu
 */
public class Tietokanta {
    
    private static InitialContext cxt;
    private static DataSource connectionPool;
    private static Tietokanta tietokanta;

    private Tietokanta() {
        try {
            this.cxt = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.connectionPool = (DataSource) cxt.lookup("java:/comp/env/jdbc/teesalmi");
        } catch (NamingException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getYhteys() {
        try {
            if (tietokanta == null){
                tietokanta = new Tietokanta();
            }
            return connectionPool.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void closeYhteys() {
//        try { yhteys.close(); } catch (Exception e) {  }
    }
}
