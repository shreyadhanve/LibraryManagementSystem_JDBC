import java.sql.Connection;
import java.sql.DriverManager;

public class Testconnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/librarydb"; // your DB name
        String user = "root";                                  // your MySQL username
        String password = "CodeKh@123";                     // your MySQL password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
