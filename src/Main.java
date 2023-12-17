import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**
     * The Main class represents the main entry point of the Application.
     * This application allows users to manage through a menu-driven console interface.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        // Initialize employees
        Employee employee1 = new Employee("rositta", "2956");
        Employee employee2 = new Employee("justin", "3221");
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.add(employee1);
        employeeArrayList.add(employee2);
        String grade;
        String username = null;
        String password = null;

        int choice;
        do {
            // Display the main menu to the user.
            System.out.println("------Welcome to Fuel Management POS Application -------");
            System.out.println("1.Management");
            System.out.println("2.POS");
            System.out.println("3.Exit");


                choice = Integer.parseInt(scanner.next());


            // Based on the user's choice, perform the corresponding operation.
            switch (choice) {
                case 1:
                    System.out.println("1. Add Fuel to Inventory");
                    System.out.println("2. Start Shift");
                    System.out.println("3. End Shift ");
                    try {
                        switch (Integer.parseInt(scanner.next())) {
                            case 1:
                                controller.fuelDelivery();
                                break;
                            case 2:
                                try {
                                    System.out.println("Enter username");
                                    username = scanner.next();
                                    System.out.println("Enter password");
                                    password = scanner.next();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid username and password.");
                                    scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                                    break; // Exit the case and go back to the main menu
                                }

                                boolean userFound = false;
                                for (int i = 0; i < employeeArrayList.size(); i++) {
                                    if (employeeArrayList.get(i).getUsers(username, password)) {
                                        userFound = true;
                                        employeeArrayList.get(i).startShift();
                                    }
                                }

                                if (!userFound) {
                                    System.out.println("Invalid username or password. Please try again.");
                                }
                                break;


                            case 3:
                                for (int i = 0; i < employeeArrayList.size(); i++) {
                                    if (employeeArrayList.get(i).getUsers(username, password)) {
                                        employeeArrayList.get(i).endShift();
                                    }
                                }
                                System.out.println("Shift Ended");
                                break;

                            case 4:
                                System.out.println("Exiting the management options");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case 2:
                    System.out.println("1. Approve Petrol");
                    System.out.println("2. Approve Diesel");
                    System.out.println("3. Refund");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice (1-4): ");
                    try {
                        switch (Integer.parseInt(scanner.next())) {
                            case 1:
                                grade = controller.selectGrade("petrol");
                                controller.approveFuel(grade + "petrol");
                                break;
                            case 2:
                                grade = controller.selectGrade("diesel");
                                controller.approveFuel(grade + "diesel");
                                break;
                            case 3:
                                controller.refundMoney();
                                break;
                            case 4:
                                System.out.println("Exiting POS!");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        } while (choice != 3);

        // Close the scanner to release system resources.
        scanner.close();

    }
}
