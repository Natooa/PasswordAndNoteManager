package passwordmanager.password;

public class Password {
    private String login;
    private String password;
    private String website;

    public Password(String login, String password, String website) {
        this.login = login;
        this.password = password;
        this.website = website;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Login: " + login + "\nPassword: " + password + "\nWebsite: " + website;
    }
}
