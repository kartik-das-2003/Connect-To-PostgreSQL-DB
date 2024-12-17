import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDBConnection {
    public static Connection getConnection() {
    	String URL = "jdbc:postgresql://localhost:5432/<enter db name>";
        String USER = "<enter unsername>";
        String PASSWORD = "<enter passcode>";
        
    	Connection conn = null;
    	try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Established Successfully");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    	
    	return conn;
    }   
}
