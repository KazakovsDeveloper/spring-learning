package org.example;

import org.example.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EventListenerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventListenerApplication.class, args);

        // Получаем экземпляр UserService из контекста Spring
        UserService userService = context.getBean(UserService.class);

        // Вызываем метод для регистрации пользователя и инициирования события
        userService.registerUser("exampleUser");

        // Закрываем контекст после выполнения проверки
        context.close();
    }
}