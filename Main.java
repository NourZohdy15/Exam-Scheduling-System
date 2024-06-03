import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Student or Teacher?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice) {
            case "s":
                Student s = new Student();
                s.Login();
                break;
            case "t":
                Teacher t = new Teacher();
                t.Login();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }

    }
}