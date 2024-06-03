import java.sql.*;
import java.util.Scanner;


public class final_exam implements examoperation {

    JDBC display_exam_connection = new JDBC();
    Connection connection4 = display_exam_connection.OpenConnection();


    public void addexam() {
        int id;
        String subject;
        int mark;
        int grade;

        Scanner x = new Scanner(System.in);

        System.out.println("Enter Exam ID :");
        id = x.nextInt();

        System.out.print("Enter Exam subject Name: ");
        subject = x.next();

        System.out.print("Enter Exam mark: ");
        mark = x.nextInt();

        System.out.print("Enter Exam grade: ");
        grade = x.nextInt();

        JDBC final_exam_connection = new JDBC();
        Connection connection3 = final_exam_connection.OpenConnection();
        String query1 = "INSERT into final_exam(exam_id, subject, mark, grade) Values(?,?,?,?)";

        try {

            PreparedStatement pre = connection3.prepareStatement(query1);
            pre.setInt(1, id);
            pre.setString(2, subject);
            pre.setInt(3, mark);
            pre.setInt(4, grade);
            pre.addBatch();

            int[] results = pre.executeBatch();

            System.out.println("Exam is added successfully, Exam Details: ");
            Display_Exams();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editexam() {


        int id, newGrade, newMark;
        String newSubj;


        Scanner x = new Scanner(System.in);
        System.out.println("Enter the ID of the Exam you want to Edit: ");
        id = x.nextInt();

        System.out.print("Enter New Exam Subject Name: ");
        newSubj = x.next();

        System.out.print("Enter New Mark: ");
        newMark = x.nextInt();

        System.out.print("Enter New Grade: ");
        newGrade = x.nextInt();

        System.out.println("Exam is edited successfully, New Exam Details: ");

        String query2 = "UPDATE final_exam SET subject = ?, mark = ?, grade = ? WHERE exam_id = ?";
        try {

            PreparedStatement pre = connection4.prepareStatement(query2);
            pre.setString(1, newSubj);
            pre.setInt(2, newMark);
            pre.setInt(3, newGrade);
            pre.setInt(4, id);
            pre.addBatch();

            int[] results = pre.executeBatch();

            System.out.println("Exam is updated successfully, Exam Details: ");
            Display_Exams();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteexam() {
        int id;


        Scanner x = new Scanner(System.in);
        System.out.println("Enter the ID of the Exam you want to Delete: ");
        id = x.nextInt();

        System.out.println("Exam is deleted successfully, New Exam Details: ");
        JDBC final_exam_connection = new JDBC();
        Connection connection3 = final_exam_connection.OpenConnection();
        String query2 = "DELETE FROM final_exam WHERE exam_id= ?";
        try {
            PreparedStatement pre = connection3.prepareStatement(query2);
            pre.setInt(1, id);
            pre.execute();


            System.out.println("Exam is deleted successfully, Exam Details: ");
            Display_Exams();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Display_Exams() {
        System.out.println("Final Exam List Details: ");

        String query2 = "SELECT * FROM final_exam";
        try {
            Statement statement = connection4.createStatement();

            ResultSet resultSet = statement.executeQuery(query2);
            while (resultSet.next()) {
                int ID = resultSet.getInt(1);
                String Subject = resultSet.getString("subject");
                int Mark = resultSet.getInt("mark");
                int Grade = resultSet.getInt("grade");

                System.out.println("id:" + ID + " subject:" + Subject + " mark:" + Mark + " grade:" + Grade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

