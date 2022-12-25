package ru.maxima;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();

        try {
            properties.load
                    (new FileInputStream(
                            getServletContext()
                                    .getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            Class.forName(dbDriverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request
                .getServletContext()
                .getRequestDispatcher("/jsp/addUser.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        try {
//            Statement statement = connection.createStatement();
//            String sqlInsert = "INSERT INTO spring_user(first_name, last_name) " +
//                    "VALUES('" + firstName + "','" + lastName + "');";
//            System.out.println(sqlInsert);
//            statement.execute(sqlInsert);
            PreparedStatement preparedStatement = connection.prepareStatement
                    (
                            "INSERT INTO spring_user(first_name, last_name) VALUES (?, ?)"
                    );
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
