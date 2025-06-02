package passwordmanager.passwordManagerService;
import passwordmanager.DBpassword.PasswordDB;
import passwordmanager.password.Password;

import java.util.ArrayList;
import java.util.List;

public class PasswordService {
    private final List<Password> passwordList = new ArrayList<>();

    public List<Password> getAllPasswords() {
        return PasswordDB.getAll();
    }

    public boolean updatePassword(Password password) {
        return PasswordDB.update(password, password.getId());
    }
}
