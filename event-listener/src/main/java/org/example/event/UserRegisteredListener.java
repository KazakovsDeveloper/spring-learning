package org.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * создаем класс слушателя, который подписывается на событие
 */
@Component
public class UserRegisteredListener implements ApplicationListener<UserRegisteredEvent> {

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        String username = event.getUsername();
        System.out.println("Пользователь зарегистрирован: " + username);
        // Здесь можно выполнить дополнительные действия по обработке события
    }
}
