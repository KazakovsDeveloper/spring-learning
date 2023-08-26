package ru.otus.book.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.dao.UserDao;
import ru.otus.book.service.model.User;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) {
        log.debug("Ищем пользователя " + login);
        User user = userDao.findUserByLogin(login);
        log.debug("Пользователь " + user);
        if (isNull(user)) {
            throw new RuntimeException("Нет такого user " + login);
        }
        return new CustomUserDetails(user.getLogin(), user.getPassword(), user.getRole());
    }
}
