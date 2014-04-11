package Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This model is used to connect to the SQL database
 * 
 * @author Teemu Salminen <teemujsalminen@gmail.com>
 */
public class Database {
    
    private static InitialContext cxt;
    private static DataSource connectionPool;
    private static Database database;
    
     /**
     * Creates the initial connection pool to the database
     */
    private Database() {
        try {
            cxt = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connectionPool = (DataSource) cxt.lookup("java:/comp/env/jdbc/teesalmi");
        } catch (NamingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Gets a single connection to the database
     */
    public static Connection getConnection() {
        try {
            if (database == null){
                database = new Database();
            }
            return connectionPool.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
