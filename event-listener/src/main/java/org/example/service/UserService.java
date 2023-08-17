package org.example.service;

import org.example.event.UserRegisteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * инициируем событие
 */
@Service
public class UserService {

    /**
     * интерфейс для публикации событий
     */
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username) {
        // Выполняйте действия по регистрации пользователя

        // Инициируем событие
        UserRegisteredEvent event = new UserRegisteredEvent(this, username);
        eventPublisher.publishEvent(event);
    }
}
