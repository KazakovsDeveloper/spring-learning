package org.example.event;

import org.springframework.context.ApplicationEvent;

/**
 * класс для описания события, реализуем абстрактный класс
 */
public class UserRegisteredEvent extends ApplicationEvent {
    private String username;

    public UserRegisteredEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
