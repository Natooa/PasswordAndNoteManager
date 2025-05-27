package ControllerOfAllApp;
import passwordmanager.controllerUI.ControllerUIPassword;
import securenote.controllerUI.ControllerUI;
import java.util.Scanner;

public class ControllerOfAllApp {
    private final ControllerUI controllerUI = new ControllerUI();
    private final ControllerUIPassword controllerUIPassword = new ControllerUIPassword();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1. Password Service\n2. Notes Service\n3. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    controllerUIPassword.startPasswordMenu();
                }
                case "2" -> {
                    controllerUI.startNotesMenu();
                }
                case "3" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}
