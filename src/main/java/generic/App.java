package generic;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println(PostgresTool.getValue("employee", "id", 1, "name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
