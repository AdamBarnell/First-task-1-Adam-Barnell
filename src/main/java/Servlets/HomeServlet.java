package Servlets;

import Models.MysqlConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out= resp.getWriter();

        out.println("<html>");
        out.println("<style>");
        out.println(".h1 { color:blue;} " +
                ".li{ display:flex; flex-wrap:wrap;}" +
                ".topnav {overflow: hidden; background-color: #333;}" +
                ".topnav a{ float: left;\n" +
                "  display: block;\n" +
                "  color: #f2f2f2;\n" +
                "  text-align: center;\n" +
                "  padding: 14px 16px;\n" +
                "  text-decoration: none; }");
        out.println("</style>");
        out.println("<nav class= topnav>");
        out.println("<li class= li><a href='http://localhost:9090/students'> Students </a></li>");
        out.println("<li class= li><a href='http://localhost:9090/courses'> Courses </a></li>");
        out.println("<li class= li><a href='http://localhost:9090/attendence'> Attendance </a></li>");
        out.println("</nav>");
        out.println("<head><title>Hello Servlet</title>");
        out.println("</head)");
        out.println("<body>");
        out.println("<h1 class = h1 > Home</h1>");
        out.println("</body>");
        out.println("</html>");
        System.out.println("GET Request");
        MysqlConnector.connect();
    }
}
