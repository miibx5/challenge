/*
Project .....................: netPos
Creation Date ...............: 22/08/2020 14:48:02
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.repositories.user;

import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.util.SearchOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository repository;

    @Test
    public void saveUserSuccessfully() throws Exception {
        UserAccount account = repository.save(new UserAccount("email1@test.com", "Mary Test", "123456"));
        assertNotNull(account);
        assertNotNull(account.getId());
    }

    @Test
    public void getUserByNameSuccessfully() throws Exception {
        UserAccount mary = repository.save(new UserAccount("mary1@test.com", "Mary Test", "123456"));
        UserAccount peter = repository.save(new UserAccount("peter1@test.com", "Peter Test", "123456"));
        UserAccount victor = repository.save(new UserAccount("victor1@test.com", "Victor Test", "123456"));

        List<UserAccount> retriviedUsers = repository.findAll(new UserAccountSpecification(new SearchCriteria("fullName", SearchOperation.CONTAINS, mary.getFullName())));
        assertNotNull(retriviedUsers);
        assertTrue(retriviedUsers.size() > BigDecimal.ZERO.intValue());
        assertEquals(retriviedUsers.get(0).getFullName(), mary.getFullName());
    }

    @Test
    public void getUserByIdSuccessfully() throws Exception {
        UserAccount account = repository.save(new UserAccount("email1@test.com", "Mary Test", "123456"));
        Optional<UserAccount> retriviedUser = repository.findById(account.getId());
        assertTrue(retriviedUser.isPresent());
        assertNotNull(retriviedUser.get().getId());
    }
}
