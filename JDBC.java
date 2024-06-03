import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;



public class JDBC {
    public Connection conn;

    public Connection OpenConnection()
    {

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/exam_management_system",
                    "root",
                    "password123"
            );

        }

        catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return conn;
    }


}
