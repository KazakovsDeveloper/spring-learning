package ru.otus.book.service.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.book.service.model.User;

public interface UserDao extends CrudRepository<User, Long> {

    User findUserByLogin(String login);

}
