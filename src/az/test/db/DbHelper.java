package az.test.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    public static Connection dbConnection() {
        Connection cn = null;
        try {
            //  p.load(new FileReader("config.properties"));
            String url = "jdbc:postgresql://localhost/azex";
            String driver = "org.postgresql.Driver";
            String un = "postgres";
            String pw = "postgresql";
            Class.forName(driver);
            cn = DriverManager.getConnection(url, un, pw);
            if (!cn.isClosed()) {
                cn.setAutoCommit(false);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cn;
    }
}
