package passwordmanager.controllerUI;
import java.util.Scanner;
import java.util.List;

import passwordmanager.DBpassword.PasswordDB;
import passwordmanager.password.Password;
import passwordmanager.passwordManagerService.PasswordService;

public class ControllerUIPassword {
    private final PasswordService passwordService = new PasswordService();
    private final Scanner scanner = new Scanner(System.in);

    public void startPasswordMenu(){
        while(true) {
            System.out.println("\n1. Add Password \n2. Show All Passwords \n3. Change Password \n4. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1" -> addPassword();
                case "2" -> showAllPassword();
                case "3" -> changePassword();
                case "4" -> {
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
        Password newPass = new Password(login, password, website);
        int row = PasswordDB.add(newPass);
    }

    private void showAllPassword(){
        List<Password> passwords = passwordService.getAllPasswords();
        if(passwords.isEmpty()) {
            System.out.println("No passwords found");
            return;
        }
        for(int i = 0; i < passwords.size(); i++) {
            System.out.println(i + ": " + passwords.get(i));
        }
    }

    private void changePassword(){
        List<Password> passwords = passwordService.getAllPasswords();
        if(passwords.isEmpty()) {
            System.out.println("No passwords found");
            return;
        }

        showAllPassword();

        System.out.println("Enter the index password you want to change: ");

        int index;

        try {
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("invalid index");
            return;
        }

        if(index < 0 || index >= passwords.size()) {
            System.out.println("\ninvalid index");
            return;
        }


        Password selectedPassword = passwords.get(index);
        System.out.println("What you would like to change?\n1. Login\n2. Password\n3. Website\n4. Cancel");

        String choice = scanner.nextLine();

        switch(choice) {
            case "1" -> selectedPassword.setLogin(scanner.nextLine());
            case "2" -> selectedPassword.setPassword(scanner.nextLine());
            case"3" -> selectedPassword.setWebsite(scanner.nextLine());
            case "4" -> {
                return;
            }
            default -> System.out.println("invalid choice");
        }

        boolean success = passwordService.updatePassword(selectedPassword);
        if(success) {
            System.out.println("Password changed");
        } else {
            System.out.println("Password not updated");
        }

    }
}

