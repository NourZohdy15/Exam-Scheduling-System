import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class assign_grade2 extends assign_assignment {
    JDBC teacher_connection = new JDBC();
    Connection conn = teacher_connection.OpenConnection();
    String subj;
    int number;
    int degree;
//    assignment_data data = new assignment_data();

    public void assign_function(int grade)
    {
        Scanner x = new Scanner(System.in);

        System.out.println("assign assignment to grade two");

        System.out.println("enter assignment number: ");
        number = x.nextInt();
        //data.setNumber(number);

        System.out.print("enter subject name: ");
        subj = x.next();
//        data.setSubj(subj);

        System.out.print("enter mark: ");
        degree = x.nextInt();
//        data.setDegree(degree);

        String query1 = "INSERT into assignment(number, subject, mark, grade) Values(?,?,?,?)";

        try {

            PreparedStatement pre = conn.prepareStatement(query1);
            pre.setInt(1, number);
            pre.setString(2, subj);
            pre.setInt(3, degree);
            pre.setInt(4, grade);


            pre.addBatch();

            int[] results = pre.executeBatch();
            System.out.println("assignment assigned to grade two successfully");
//            System.out.println(ex1.toString());
            Display_Assignments();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void student_grade()
    {

        JDBC teacher_connection = new JDBC();
        Connection conn = teacher_connection.OpenConnection();
        String query1 = "SELECT name, mark FROM student WHERE grade = 2";
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                int Mark = resultSet.getInt("mark");
                String Name = resultSet.getString("name");
                System.out.println(  Name + " "+ Mark );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void report_function()
    {}

    public void publish_notification(String x)
    {
        String query1 = "INSERT into grade2(notification, type) Values(1,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(query1);
            pre.setString(1, x);
            pre.addBatch();
            int result = pre.executeUpdate();


            System.out.println("Notification is sent successfully to grade 2");
//            Display_Exams();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void check_notification(int current_id) {

        String query = "SELECT notification FROM grade2";
        try {
            Statement statement = conn.createStatement();
            PreparedStatement pre = conn.prepareStatement(query);
            pre.executeUpdate();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next())
            {
                int Notification = resultSet.getInt("notification");
                if(Notification==1) {
                    String query2 = "SELECT subject, mark, type FROM take_exam WHERE id= '" + current_id + "'";
                    try {
                        Statement statement2 = conn.createStatement();

                        ResultSet resultSet2 = statement.executeQuery(query2);
                        while (resultSet2.next()) {
                            String Subject = resultSet.getString("subject");
                            int Mark = resultSet2.getInt("mark");
                            String Type = resultSet.getString("type");
                            System.out.println(Subject + " " + Mark + " " + Type);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Grades are not published Yet");
                }

            }

        }catch(SQLException e) {
            e.printStackTrace();
        }


    }

}
