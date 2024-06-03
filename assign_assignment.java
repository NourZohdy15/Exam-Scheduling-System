import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class assign_assignment {
    JDBC teacher_connection = new JDBC();
    Connection conn = teacher_connection.OpenConnection();
    public abstract void assign_function(int x);
    public abstract void student_grade();
    public abstract void report_function();
    public void Display_Assignments() {
        System.out.println("Assignment List Details: ");
        String query2 = "SELECT * FROM assignment";
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query2);
            while (resultSet.next()) {
                int number = resultSet.getInt(1);
                String subj = resultSet.getString("subject");
                int mark = resultSet.getInt("mark");
                int grade = resultSet.getInt("grade");


                System.out.println("number:" + number + " subject:" + subj + " mark:" + mark + "grade:" + grade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public abstract void publish_notification(String x);

    public abstract void check_notification(int current_id);

}
