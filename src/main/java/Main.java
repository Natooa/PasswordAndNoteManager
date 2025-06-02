import ControllerOfAllApp.ControllerOfAllApp;
import databaseConf.DB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(var connection = DB.connect()){
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ControllerOfAllApp app = new ControllerOfAllApp();
        app.start();
    }
}
