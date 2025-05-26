package passwordmanager.passwordManagerService;
import passwordmanager.password.Password;

import java.util.ArrayList;
import java.util.List;

public class PasswordService {
    private final List<Password> passwordList = new ArrayList<>();

    public void addPassword(String login, String password, String website) {
        passwordList.add(new Password(login, password, website));
    }

    public List<Password> getAllPasswords() {
        return passwordList;
    }
}
