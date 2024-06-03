import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class StudentLoginProxy implements Login{

    Student student = new Student();


    @Override
    public void check(String username, String password){


        JDBC login_connection = new JDBC();
        Connection connection1= login_connection.OpenConnection();
        String query = "SELECT id FROM student WHERE username ='" + username + "' AND password ='" + password + "'";

        try
        {
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("You are Logged in");
                 int current_id = resultSet.getInt("id");
                student.displayoptions(current_id);
            } else {
                System.out.println("Invalid Info");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
