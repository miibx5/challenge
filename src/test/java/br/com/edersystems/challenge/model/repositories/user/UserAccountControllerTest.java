/*
Project .....................: netPos
Creation Date ...............: 20/08/2020 17:22:40
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.repositories.user;

import br.com.edersystems.challenge.netpos.ChallengeApplication;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.netpos.model.service.user.UserAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(UserAccountController.class)
@SpringBootTest(classes = ChallengeApplication.class)
@AutoConfigureMockMvc
public class UserAccountControllerTest {

    @MockBean
    private UserAccountRepository repository;

    @MockBean
    private UserAccountService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldBeCreatedUser() throws Exception {
        CreateUserAccountRequest userAccountRequest = new CreateUserAccountRequest();
        userAccountRequest.setEmail("email1@test.com");
        userAccountRequest.setFullName("Mary Test");
        userAccountRequest.setPassword("123456");
        UserAccount user = new UserAccount("email1@test.com", "Mary Test", "123456");
        user.setId(UUID.randomUUID());
        when(service.create(any(CreateUserAccountRequest.class))).thenReturn(user);
        when(repository.save(any(UserAccount.class))).thenReturn(user);
        mockMvc.perform(post("/users")
                .content(mapper.writeValueAsString(userAccountRequest))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
