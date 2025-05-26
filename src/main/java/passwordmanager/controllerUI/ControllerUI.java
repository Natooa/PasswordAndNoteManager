package passwordmanager.controllerUI;
import java.util.Scanner;
import java.util.List;
import passwordmanager.password.Password;
import passwordmanager.passwordManagerService.PasswordService;

public class ControllerUI {
    private final PasswordService passwordService = new PasswordService();
    private final Scanner scanner = new Scanner(System.in);

    public void start(){
        while(true) {
            System.out.println("\n1. Add Password \n2. Show All Passwords \n3. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1" -> addPassword();
                case "2" -> showAllPassword();
                case "3" -> {
                    System.out.println("Bye");
                    return;
                }
                default -> {
                    System.out.println("invalid choice");
                }
            }
        }
    }

    private void addPassword(){
        System.out.println("Enter your login: ");
        String login = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        System.out.println("Enter website: ");
        String website = scanner.nextLine();
        passwordService.addPassword(login, password, website);
    }

    private void showAllPassword(){
        List<Password> passwords = passwordService.getAllPasswords();
        if(passwords.isEmpty()) {
            System.out.println("No passwords found");
        }
        for(int i = 0; i < passwords.size(); i++) {
            System.out.println(i + ": " + passwords.get(i));
        }
    }
}
