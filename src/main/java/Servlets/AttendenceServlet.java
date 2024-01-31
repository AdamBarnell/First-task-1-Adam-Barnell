package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/attendence")
public class AttendenceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<header><link rel =stylesheet href=/All.css> </link></header>");
        out.println("<nav class = topnav>");
        out.println("<li><img src='https://gritacademy.se/wp-content/uploads/2021/05/Grit-Academy-logo.png' width='100' height='50' /></li>");
        out.println("<li><a href='http://localhost:9090/students'> Students </a></li>");
        out.println("<li><a href='http://localhost:9090/courses'> Courses </a></li>");
        out.println("<li><a href='http://localhost:9090/attendence'> Attendance </a></li>");
        out.println("</nav>");
        out.println("<head><title>Hello courses</title></head>");
        out.println("<body>");
        out.println("<h1> Attendence</h1>");
        Map<String, List<String>> studentCourses = new HashMap<>();
        //Hämtar info från databasen med sql fråga och skickar ut det till hemsidan
        try {
            String sql = "SELECT students.name AS StudentFirstName, students.lastname AS StudentLastName, courses.name AS CourseName FROM attendance INNER JOIN students ON attendance.`student.id` = students.students_id INNER JOIN courses ON attendance.course_id = courses.courses_id";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gritacademy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String studentFirstName = rs.getString("StudentFirstName");
                String studentLastName = rs.getString("StudentLastName");
                String courseName = rs.getString("CourseName");
                //Lägger till fullständigt namn med förnamn och efter name till en string
                String fullName = studentFirstName + " " + studentLastName;

                studentCourses.putIfAbsent(fullName, new ArrayList<>());
                studentCourses.get(fullName).add(courseName);
            }
            //Skriver ut allt
            for (Map.Entry<String, List<String>> entry : studentCourses.entrySet()) {
                out.println("<p>Student: " + entry.getKey() + ", Courses: " + String.join(", ", entry.getValue()) + "</p>");
            }

        } catch (Exception e) {
            out.println("Error: " + e.toString());
        }
        out.println("<footer><p> Gjord av Adam Barnell </p></footer>");
        out.println("</body>");
        out.println("</html>");
        System.out.println("GET Request");
    }
}


