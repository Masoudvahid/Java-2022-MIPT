package ru.maxima.servlet;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.maxima.dao.UsersDao;
import ru.maxima.dao.UsersDaoJdbcImpl;
import ru.maxima.dao.UsersDaoJdbcTemplateImpl;
import ru.maxima.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

@WebServlet("/users")
public class UsersServletWithDao extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        DriverManagerDataSource source = new DriverManagerDataSource();
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            source.setUsername(dbUsername);
            source.setPassword(dbPassword);
            source.setUrl(dbUrl);
            source.setDriverClassName(dbDriverClassName);
            usersDao = new UsersDaoJdbcTemplateImpl(source);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersDao.findAll();
        request.setAttribute("usersFromServer", users);
        request
                .getServletContext()
                .getRequestDispatcher("/jsp/users.jsp")
                .forward(request, response);
    }
}
