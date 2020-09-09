package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class contains the settings for Database connection
 */
public class DBConnection {

    private final static String HOST = "localhost";//Database host address
    private final static String DATABASE = "exams";//Database name
    private final static String USERNAME = "root";//Database username
    private final static String PASSWORD = "rootroot";//Databse password
    private static Connection Connection;//A singleton object for DBConnection

    static {//initialize the Database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE + "?useUnicode=true&characterEncoding=utf-8", USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private DBConnection() {

    }

    public static Connection getConnection() {//return a singleton object of DBConnection
        return Connection;
    }

}
