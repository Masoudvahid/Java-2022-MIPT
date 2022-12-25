package ru.maxima.dao;

import ru.maxima.model.User;

import java.util.List;

public interface UsersDao extends CrudDao<User>{
    List<User> findAllByFirstName(Integer id);
}
