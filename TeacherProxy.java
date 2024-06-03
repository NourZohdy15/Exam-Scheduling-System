import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class TeacherProxy implements Login {

//    private String username;
//    private String password;
    Teacher teacher = new Teacher();


//    public String getPassword() {
//        return this.password;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }


    @Override
    public void check(String username, String password){
//        if(logindata.containsKey(name) && pass.equals(logindata.get(name)))
//        {
//            System.out.println("Login success");
//            teacher.displayouteroptions();
//        }
//        else
//        {
//            System.out.println("Login Failed");
//        }

        JDBC login_connection = new JDBC();
        Connection connection1= login_connection.OpenConnection();
        String query = "SELECT username FROM teacher WHERE username ='" + username + "' AND password ='" + password + "'";
        try
        {
            Statement statement = connection1.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("You are Logged in");
                teacher.displayouteroptions();
            } else {
                System.out.println("Invalid Info");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
