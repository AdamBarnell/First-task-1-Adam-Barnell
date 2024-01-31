package Models;

import javax.resource.spi.Connector;
import java.sql.*;

public class MysqlConnector {
    public static Connector connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gritacademy", "usergrit", "usergrit");
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
