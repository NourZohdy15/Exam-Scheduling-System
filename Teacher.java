import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher implements Login {

    assign_factory fact = new assign_factory();     //call factory

    boolean verify = false;
    int x;

    public void Welcome() {
        System.out.println("Welcome to teacher Class");
        Login();
    }

    public void Login() {
        System.out.println("Enter your Username and Password: ");
        Scanner sc = new Scanner(System.in);   //to access input
        System.out.println("Username: ");
        String username = sc.next();        //read name
        System.out.println("Password: ");
        String password = sc.next();      //read pass
        TeacherProxy tp = new TeacherProxy();
        tp.check(username, password);
    }

    @Override
    public void check(String username, String password) {
        System.out.println("Successful Login");
        displayouteroptions();
    }

    public void displayouteroptions() {
        System.out.println("Please enter number of operation you want to Perform");
        System.out.println("1. Make Exam operation");
        System.out.println("2. Check Student's grades");
        System.out.println("3. Issuing Student's report");
        System.out.println("4. Assigning assignment");
        System.out.println("5. Sending Notification");
        System.out.print("Enter your choice: ");

        JDBC teacher_connection = new JDBC();
        Connection conn = teacher_connection.OpenConnection();

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        switch (num) {
            case 1:
                displayinneroptions();
                break;
            case 2:
                check_grades(teacher_connection, conn);
                break;
            case 3:
                issuing_report();
                break;
            case 4:
                assignment_function();
                break;
            case 5:
                send_notification();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void displayinneroptions() {
        do {
            System.out.println("Welcome to Exam Page Operation");
            System.out.println("Please choose type of exam you want to Perform operation in:");
            System.out.println("1. Add Exam");
            System.out.println("2. Edit Exam");
            System.out.println("3. Delete Exam");
            System.out.print("Enter your choice: ");

            Scanner input1 = new Scanner(System.in);
            int num1 = input1.nextInt();

            switch (num1) {
                case 1:
                    add_exam_function();
                    break;
                case 2:
                    edit_exam_function();
                    break;
                case 3:
                    delete_exam_function();
                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
            System.out.println("Press [Y/y] to operate another exam or [N/n] to go to main menu");
            verify = Check_choice();
        } while (verify);
    }

    public void add_exam_function() {
        printing_func();

        Scanner input1 = new Scanner(System.in);
        int num1 = input1.nextInt();

        switch (num1) {
            case 1:

                Exam week = new add_exam(new weekly_exam());
                week.exam_func();

                break;
            case 2:
                Exam month = new add_exam(new monthly_exam());
                month.exam_func();
                break;
            case 3:

                Exam final_exam = new add_exam(new final_exam());
                final_exam.exam_func();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void edit_exam_function() {
        printing_func();

        Scanner input1 = new Scanner(System.in);
        int num1 = input1.nextInt();

        switch (num1) {
            case 1:

                Exam week = new edit_exam(new weekly_exam());
                week.exam_func();

                break;
            case 2:
                Exam month = new edit_exam(new monthly_exam());
                month.exam_func();
                break;
            case 3:
                Exam final_exam = new edit_exam(new final_exam());
                final_exam.exam_func();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void delete_exam_function() {
        printing_func();

        Scanner input1 = new Scanner(System.in);
        int num1 = input1.nextInt();

        switch (num1) {
            case 1:

                Exam week = new delete_exam(new weekly_exam());
                week.exam_func();

                break;
            case 2:
                Exam month = new delete_exam(new monthly_exam());
                month.exam_func();
                break;
            case 3:
                Exam final_exam = new delete_exam(new final_exam());
                final_exam.exam_func();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }

    }

    public void printing_func() {
        System.out.println("Please choose the type of operation you want to Perform:");
        System.out.println("1. weekly Exam");
        System.out.println("2. monthly Exam");
        System.out.println("3. final Exam");
        System.out.print("Enter your choice: ");
    }

    public void check_grades(JDBC teacher_connection, Connection conn) {
        do {
            System.out.println("Enter the grade");
            System.out.println("1. Grade 1");
            System.out.println("2. Grade 2");
            System.out.println("3. Grade 3");

            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            assign_factory factory = new assign_factory();

            switch (num) {
                case 1:
                    assign_assignment a1 = factory.assign_func("grade one");
                    a1.student_grade();
                    break;
                case 2:
                    assign_assignment a2 = factory.assign_func("grade two");
                    a2.student_grade();
                    break;
                case 3:
                    assign_assignment a3 = factory.assign_func("grade three");
                    a3.student_grade();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

            System.out.println("Do you want to Check Another Grade?");
            verify = Check_choice();
        } while (verify);
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
            displayouteroptions();
        }
        return verify;
    }

    public void issuing_report() {
        do {
            System.out.print("Enter the Student's ID: ");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            JDBC teacher_connection = new JDBC();
            Connection conn = teacher_connection.OpenConnection();

            String query = "SELECT subject, date, mark, type FROM taken_exam WHERE student_id= '" + num + "' ";
            try {
                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String Subject = resultSet.getString("subject");
                    String Date = resultSet.getString("date");
                    int Mark = resultSet.getInt("mark");
                    String Type = resultSet.getString("type");
                    System.out.println(Subject + " " + Date + " " + Mark + " " + Type);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Press [Y/y] to operate another exam or [N/n] to go to main menu");
            verify = Check_choice();
        }while (verify);

    }

    public void assignment_function() {
        do {
            System.out.println("Enter the grade you want to assign assignment to: ");
            System.out.println("1. To assign to grade one");
            System.out.println("2. To assign to grade two");
            System.out.println("3. To assign to grade three");
            System.out.print("Enter your choice: ");

            Scanner input1 = new Scanner(System.in);
            int grade = input1.nextInt();


            switch (grade) {
                case 1:
                    assign_assignment a1 = fact.assign_func("grade one");
                    a1.assign_function(grade);
                    break;
                case 2:
                    assign_assignment a2 = fact.assign_func("grade two");
                    a2.assign_function(grade);
                    break;
                case 3:
                    assign_assignment a3 = fact.assign_func("grade three");
                    a3.assign_function(grade);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
            System.out.println("Press [Y/y] to operate another exam or [N/n] to go to main menu");
            verify = Check_choice();
        } while (verify);
    }

    public void send_notification() {
        do {
            System.out.println("Please choose type of notification you want to send with");
            System.out.println("1. SMS notification");
            System.out.println("2. Email notification");
            System.out.println("3. Whatsapp notification");
            System.out.print("Enter your choice: ");

            Scanner input5 = new Scanner(System.in);
            int num5 = input5.nextInt();

            notification_factory notify = new notification_factory();     //call notification factory
            switch (num5) {
                case 1:
                    String x1 ="SMS";
                    Notification s1 = notify.getnotification("SMS");
                    s1.notification_type();
                    publish_function(x1);
                    break;
                case 2:
                    String x2 ="Email";
                    Notification s2 = notify.getnotification("Email");
                    s2.notification_type();
                    publish_function(x2);
                    break;
                case 3:
                    String x3 ="WhatsApp";
                    Notification s3 = notify.getnotification("WhatsApp");
                    s3.notification_type();
                    publish_function(x3);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
            System.out.println("Press [Y/y] to operate another exam or [N/n] to go to main menu");
            verify = Check_choice();
        } while (verify);
    }

    public void publish_function(String x) {
        System.out.println("enter grade you want to send the message to: ");      //teacher
        System.out.println("1.grade one");
        System.out.println("2.grade two");
        System.out.println("3.grade three");
        System.out.print("enter your choice: ");

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        switch (num) {
            case 1:
                assign_assignment a1 = fact.assign_func("grade one");
                a1.publish_notification(x);
                break;
            case 2:
                assign_assignment a2 = fact.assign_func("grade two");
                a2.publish_notification(x);
                break;
            case 3:
                assign_assignment a3 = fact.assign_func("grade three");
                a3.publish_notification(x);
                break;
            default:
                System.out.println("Invalid Option");
                break;

        }

    }
}

