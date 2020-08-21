/*
Project .....................: netPos
Creation Date ...............: 20/08/2020 17:02:54
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.controllers;


import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.model.service.user.UserAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAccountController.class)
class UserAccountControllerTest {

    @MockBean
    private UserAccountService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldBeCreatedUser() throws Exception {
        CreateUserAccountRequest userAccountRequest = new CreateUserAccountRequest();
        userAccountRequest.setEmail("email1@test.com");
        userAccountRequest.setFullName("Mary Test");
        userAccountRequest.setPassword("123456");
        UserAccount user = new UserAccount("email1@test.com", "Mary Test", "123456");
        when(service.create(any(CreateUserAccountRequest.class))).thenReturn(user);
        mockMvc.perform(post("/users")
                .content(mapper.writeValueAsString(userAccountRequest))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


}