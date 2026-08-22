package lk.aurelia.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static Connection connection;
    private static String path = "jdbc:mysql://localhost:3306/aurelia_db";
    private static String USER =  "root";
    private static String PASSWORD = "akila@2005";
    
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path, USER, PASSWORD);
        } catch (Exception e) {
            
        }
    }
    
    public static ResultSet search(String query) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    
    public static void iud(String query) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    
}
