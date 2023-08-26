package ru.otus.book.service.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "user1",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("доступ есть для аутентифицированного пользователя")
    public void testAuthenticatedOnAdmin() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("редирект на логин")
    public void testAuthenticatedOnAdminIsFailure() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection());
    }

}