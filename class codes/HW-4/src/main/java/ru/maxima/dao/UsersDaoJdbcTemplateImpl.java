package ru.maxima.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.maxima.model.Car;
import ru.maxima.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersDaoJdbcTemplateImpl implements UsersDao {

    private final JdbcTemplate template;

    // language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM spring_user";

    private Map<Integer, User> userMap = new HashMap<>();

    //language=SQL
    private final String SQL_SELECT_USER_WITH_CARS =
            "SELECT spring_user.*, spring_car.id as car_id, spring_car.model FROM spring_user" +
                    " LEFT JOIN spring_car ON spring_user.id = spring_car.owner_id " +
                    " WHERE spring_user.id = ?";

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");

        if (!userMap.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            User user = new User(id, firstName, lastName, new ArrayList<>());
            userMap.put(id, user);
        }

        Car car = new Car(resultSet.getInt("id"), resultSet.getString("model"), userMap.get(id));
        userMap.get(id).getCars().add(car);

        return userMap.get(id);
    };

    @Override
    public Optional<User> find(Integer id) {
        template.query(SQL_SELECT_USER_WITH_CARS, userRowMapper, id);
        if (userMap.containsKey(id)) {
            Optional.of(userMap);
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByFirstName(Integer id) {
        return null;
    }
}
