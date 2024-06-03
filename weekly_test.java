import javax.security.auth.Subject;
import java.sql.*;
import java.util.Scanner;
import java.math.*;

public class weekly_test implements exam_type
{

    String type = "weekly";
    JDBC student_connection = new JDBC();
    Connection conn = student_connection.OpenConnection();

public void display_test(int current_id) {
    String query = "SELECT * FROM weekly_test";
    try {
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Pending Weekly Exams:");
        while (resultSet.next()) {
            int Exam_id = resultSet.getInt("exam_id");
            String Subject = resultSet.getString("subject");
            String Duration = resultSet.getString("duration");
            System.out.println(Exam_id + " " + Subject + " " + Duration);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    take_exam_choice(current_id);

}

public void take_exam_choice(int current_id) {
    System.out.println("Enter the id of exam you want to take: ");
    System.out.print("enter choice: ");
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    //The user enters the id of the exam the want to take
    // query selects test according to the id

    String query = "SELECT * FROM weekly_test WHERE exam_id='" + num + "' ";
    try {
        Statement statement = conn.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int Exam_id = resultSet.getInt("exam_id");
            String Subject = resultSet.getString("subject");
            String Duration = resultSet.getString("duration");
            String Date = resultSet.getString("date");
            System.out.println(Exam_id + " " + Subject + " " + Duration + " " + Date);
            String query1 = "INSERT into taken_exam(student_id, subject, date, mark, type) Values(?,?,?,20,?)";

            try {

                PreparedStatement pre = conn.prepareStatement(query1);
                pre.setInt(1, current_id);
                pre.setString(2, Subject);
                pre.setString(3, Date);
                pre.setString(4,type);
                pre.addBatch();

                int[] results = pre.executeBatch();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    System.out.println("exam was taken successfully");

}

}
