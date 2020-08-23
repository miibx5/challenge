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
package br.com.edersystems.challenge.model.service.user;

import br.com.edersystems.challenge.exceptions.NotFoundException;
import br.com.edersystems.challenge.exceptions.UnProcessableEntityException;
import br.com.edersystems.challenge.model.dto.user.UserAccountDTO;
import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.model.repositories.user.UserAccountRepository;
import br.com.edersystems.challenge.model.repositories.user.UserAccountSpecification;
import br.com.edersystems.challenge.model.request.user.CreateUserAccountRequest;
import br.com.edersystems.challenge.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserAccountService {

    @Autowired
    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    public UserAccount create(CreateUserAccountRequest request) {
        UserAccount retriviedUser = repository.findByEmail(request.getEmail());
        if(Objects.nonNull(retriviedUser)) {
            throw new UnProcessableEntityException("email.already_exists");
        }
        return repository.save(builderUserAccount(request));
    }

    public UserAccount getUserById(UUID userId) {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException("user.notfound"));
    }

    public List<UserAccountDTO> getUsers(String name) {
        if(Objects.nonNull(name) && !name.isBlank()) {
            return repository.findAll(getSpecificationByFullName(name))
                    .stream()
                    .map(userAccount -> new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.getFullName()))
                    .collect(Collectors.toList());
        }

        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList()).stream()
                .map(userAccount -> new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.getFullName()))
                .collect(Collectors.toList());
    }

    private UserAccountSpecification getSpecificationByFullName(String name) {
        return new UserAccountSpecification(new SearchCriteria("fullName", SearchOperation.CONTAINS, name));
    }

    private UserAccount builderUserAccount(CreateUserAccountRequest request) {
        return new UserAccount(request.getEmail(), request.getFullName(), request.getPassword());
    }

    private UserAccountDTO builderUserAccountDTO(UserAccount userAccount) {
        return new UserAccountDTO(userAccount.getId(), userAccount.getEmail(), userAccount.getFullName());
    }
}
