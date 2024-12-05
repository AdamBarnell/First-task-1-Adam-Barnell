package Models;

import javax.resource.spi.Connector;
import java.sql.*;

public class MysqlConnector {
    public static Connector connect() {
        try {
            Class.forName();

            Connection con = DriverManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from students");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
