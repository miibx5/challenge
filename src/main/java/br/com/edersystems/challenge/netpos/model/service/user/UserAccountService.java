/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 11:37:45
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.service.user;

import br.com.edersystems.challenge.netpos.model.dto.user.UserAccountDTO;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.netpos.model.repositories.user.UserAccountRepository;
import br.com.edersystems.challenge.netpos.model.repositories.user.UserAccountSpecification;
import br.com.edersystems.challenge.netpos.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.netpos.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserAccountService
{

    private final UserAccountRepository repository;

    @Autowired
    public UserAccountService(UserAccountRepository repository)
    {
        this.repository = repository;
    }

    public ResponseEntity<UserAccount> create(CreateUserAccountRequest request)
    {
        UserAccount userAccount = repository.save(builderUserAccount(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(userAccount);
    }

    public ResponseEntity<UserAccount> getUserById(UUID userId)
    {
        UserAccount userAccount = repository.findById(userId);
        return ResponseEntity.ok(userAccount);
    }

    public ResponseEntity<List<UserAccountDTO>> getUsers(String name)
    {
        List<UserAccountDTO> users = repository.findAll(getSpecificationByFullName(name))
                .stream()
                .map(userAccount -> new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.getFullName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    private UserAccountSpecification getSpecificationByFullName(String name)
    {
        return new UserAccountSpecification(new SearchCriteria("fullName", SearchOperation.CONTAINS, name));
    }

    private UserAccount builderUserAccount(CreateUserAccountRequest request)
    {
        return new UserAccount(request.getEmail(), request.getFullName(), request.getPassword());
    }

    private UserAccountDTO builderUserAccountDTO(UserAccount userAccount)
    {
        return new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.getFullName());
    }
}
