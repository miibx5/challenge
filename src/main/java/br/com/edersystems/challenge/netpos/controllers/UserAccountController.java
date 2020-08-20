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
package br.com.edersystems.challenge.netpos.controllers;

import br.com.edersystems.challenge.netpos.model.dto.user.UserAccountDTO;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.netpos.model.service.user.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "USERS")
@RequestMapping("/users")
public class UserAccountController
{
    private final UserAccountService service;

    @Autowired
    public UserAccountController(UserAccountService service)
    {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create A user")
    public ResponseEntity<UserAccount> createUserAccount(@Valid @RequestBody CreateUserAccountRequest request)
    {
        return service.create(request);
    }

    @GetMapping(value = "/{user_Id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Retrieve a user by id")
    public ResponseEntity<UserAccount> getUserById(@PathVariable("user_id") UUID userId)
    {
        return service.getUserById(userId);
    }


    @GetMapping(value = "{name}")
    public ResponseEntity<List<UserAccountDTO>> getUsers(@RequestHeader("name") String name)
    {
        return service.getUsers(name);
    }


}
