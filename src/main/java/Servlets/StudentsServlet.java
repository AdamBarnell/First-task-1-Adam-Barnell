package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<header><link rel =stylesheet href=/All.css> </link></header>");
        out.println("<nav class= 'topnav'>");
        out.println("<li><img src='https://gritacademy.se/wp-content/uploads/2021/05/Grit-Academy-logo.png' width='100' height='50' /></li>");
        out.println("<li><a href='index.html'> Home </a></li>");
        out.println("<li><a href='http://localhost:9090/students'> Students </a></li>");
        out.println("<li><a href='http://localhost:9090/courses'> Courses </a></li>");
        out.println("<li><a href='http://localhost:9090/attendence'> Attendance </a></li>");
        out.println("</nav>");
        out.println("<head><title>Hello Students</title></head>");
        out.println("<body>");
        out.println("<h1 class=h1> Students</h1>");
        try {
            String sql = "SELECT * FROM students";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gritacademy", "usergrit", "usergrit");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Starta tabellen och lägger till kolumnnamn
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Namn</th><th>Efternamn</th><th>Hobby</th><th>Stad</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("students_id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String hobby = rs.getString("hobby");
                String town = rs.getString("town");

                // Lägger till en rad för varje elev
                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + lastname + "</td><td>" + hobby + "</td><td>" + town + "</td></tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            out.println("Error: " + e);
        }

        out.println("<footer><p> Gjord av Adam Barnell </p></footer>");
        out.println("</body>");
        out.println("</html>");
        System.out.println("GET Request");

    }
}
