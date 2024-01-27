package uhang.uhang;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConfigTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try (Connection con =
                     DriverManager.getConnection(
                             "jdbc:mysql://db-uhang.c9862quyunza.ap-northeast-2.rds.amazonaws.com:3306/UhangTest?serverTimezone=Asia/Seoul",
                             "chennie",
                             "chennie!")) {
            System.out.println("DB Connection => " + con);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
