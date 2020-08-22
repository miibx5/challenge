/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 12:25:16
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.controllers;

import br.com.edersystems.challenge.model.dto.user.UserAccountDTO;
import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.model.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserAccountController {

    private final UserAccountService service;

    @Autowired
    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<UserAccount> createUserAccount(@Valid @RequestBody CreateUserAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getUsers(@RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok(service.getUsers(name));
    }

    @GetMapping(value = "/{user_Id}")
    public ResponseEntity<UserAccount> getUserById(@PathVariable UUID user_Id) {
        return ResponseEntity.ok(service.getUserById(user_Id));
    }
}
