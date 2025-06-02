package passwordmanager.password;

public class Password {
    private String login;
    private String password;
    private String website;
    private int id;

    public Password(String login, String password, String website) {
        this.login = login;
        this.password = password;
        this.website = website;
    }

    public Password(int id, String login, String password, String website) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.website = website;
    }

    @Override
    public String toString() {
        return "Login: " + login + "\nPassword: " + password + "\nWebsite: " + website;
    }

    //change Password methods
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getWebsite() {
        return website;
    }

    public int getId() {
        return id;
    }
}
