import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student implements Login {

    boolean verify = false;

    int current_id;

    public void Welcome() {
        System.out.println("Welcome to  Class");
        Login();
    }

    public void Login() {
        System.out.println("Enter your Username and Password: ");
        Scanner sc = new Scanner(System.in);   //to access input
        System.out.println("Username: ");
        String username = sc.next();        //read name
        System.out.println("Password: ");
        String password = sc.next();      //read pass
        StudentLoginProxy sp = new StudentLoginProxy();
        sp.check(username, password);
    }

    @Override
    public void check(String username, String password) {
        System.out.println("Successful Login");
        displayoptions(current_id);
    }

    public void displayoptions(int current_id) {
        System.out.println("Please Select the operation you want to Perform");
        System.out.println("1. Take an Exam");
        System.out.println("2. Display Grades");
        System.out.println("3. Write a Note to the Principal");
        System.out.print("Enter your choice: ");

        JDBC student_connection = new JDBC();
        Connection connection2 = student_connection.OpenConnection();

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        switch (num) {
            case 1:
                take_exam(current_id);
                break;
            case 2:
                published_grades();
                break;
            case 3:
                WriteNoteCommand command = new WriteNoteCommand();
                writeNote(command, student_connection, connection2);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }


    public void take_exam(int current_id)
    {
        do {
            System.out.println("Choose your grade");
            System.out.println("1. Grade 1");
            System.out.println("2. Grade 2");
            System.out.println("3. Grade 3");
            Scanner s = new Scanner(System.in);
            int num = s.nextInt();

            switch (num) {
                case 1:
                    grade1_function(current_id);
                    break;
                case 2:
                    grade2_function(current_id);
                    break;
                case 3:
                    grade3_function(current_id);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;

            }
            System.out.println("Press [Y/y] to operate another exam or [N/n] to go to main menu");
            verify = Check_choice();
        }while(verify);
    }

    public void grade1_function(int current_id)
    {
        System.out.println("Choose Exam Type");
        System.out.println("1. Weekly Exams ");
        System.out.println("2. Monthly Exams");
        System.out.println("3. Final Exams ");
        Scanner sc = new Scanner(System.in);
        int num2 = sc.nextInt();

        switch(num2)
        {
            case 1:
                student_grade s1 = new student_grade1(new weekly_test());
                s1.grade_num(current_id);
                break;
            case 2:
                student_grade s2 = new student_grade1(new monthly_test());
                s2.grade_num(current_id);
                break;
            case 3:
                student_grade s3 = new student_grade1(new final_test());
                s3.grade_num(current_id);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void grade2_function(int current_id)
    {
        System.out.println("Choose Exam Type");
        System.out.println("1. Weekly Exams ");
        System.out.println("2. Monthly Exams");
        System.out.println("3. Final Exams ");
        Scanner sc = new Scanner(System.in);
        int num2 = sc.nextInt();

        switch(num2)
        {
            case 1:
                student_grade s1 = new student_grade2(new weekly_test());
                s1.grade_num(current_id);
                break;
            case 2:
                student_grade s2 = new student_grade2(new monthly_test());
                s2.grade_num(current_id);
                break;
            case 3:
                student_grade s3 = new student_grade2(new final_test());
                s3.grade_num(current_id);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void grade3_function(int current_id)
    {
        System.out.println("Choose Exam Type");
        System.out.println("1. Weekly Exams ");
        System.out.println("2. Monthly Exams");
        System.out.println("3. Final Exams ");
        Scanner sc = new Scanner(System.in);
        int num2 = sc.nextInt();

        switch(num2)
        {
            case 1:
                student_grade s1 = new student_grade3(new weekly_test());
                s1.grade_num(current_id);
                break;
            case 2:
                student_grade s2 = new student_grade3(new monthly_test());
                s2.grade_num(current_id);
                break;
            case 3:
                student_grade s3 = new student_grade3(new final_test());
                s3.grade_num(current_id);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void published_grades()
    {
        System.out.println("Choose your grade");
        System.out.println("1. Grade 1");
        System.out.println("2. Grade 2");
        System.out.println("3. Grade 3");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        assign_factory fact = new assign_factory();

        switch (num) {
            case 1:
                assign_assignment a1 = fact.assign_func("grade one");
                a1.check_notification(current_id);
                break;
            case 2:
                assign_assignment a2 = fact.assign_func("grade two");
                a2.check_notification(current_id);
                break;
            case 3:
                assign_assignment a3 = fact.assign_func("grade three");
                a3.check_notification(current_id);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }

    }
    public void writeNote(WriteNoteCommand command, JDBC student_connection, Connection connection2) {
        Scanner s = new Scanner(System.in);
        String note = s.next();
        command.execute(note);
        String query = "INSERT into principal(note) Values(?)";
        ;

        try {
            PreparedStatement pre = connection2.prepareStatement(query);
            pre.setString(1, note);
            pre.addBatch();
            int result = pre.executeUpdate();
            System.out.println("A Note has been Sent :) ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean Check_choice() {
        Scanner sc = new Scanner(System.in);
        char choice = sc.next().charAt(0);
        while (choice != 'Y' && choice != 'y' && choice != 'N' && choice != 'n') {
            System.out.println(" Invalid character, Please choose character from the options: ");
            choice = sc.next().charAt(0);
        }
        if (choice == 'Y' || choice == 'y') {
            verify = true;
        } else if (choice == 'n' || choice == 'N') {
            verify = false;
            displayoptions(current_id);
        }
        return verify;
    }





}




