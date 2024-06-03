import java.sql.*;


public class student_grade1 extends student_grade{

    JDBC student_connection = new JDBC();
    Connection conn = student_connection.OpenConnection();
    public student_grade1(exam_type ex)
    {
        super(ex);
    }


    public void grade_num(int current_id)
    {
        ex.display_test(current_id);

    }


}
