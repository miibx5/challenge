/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 12:01:41
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.repositories.user;

import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.netpos.util.SearchOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserAccountRepositoryTest
{
    @Autowired
    private UserAccountRepository repository;

    private UserAccount mary;

    private UserAccount john;

    @Before
    public void init()
    {
        mary = new UserAccount("email1@test.com", "Mary Test", "123456");
        repository.save(mary);
        john = new UserAccount("email2@test.com", "John Test", "123456");
        repository.save(john);
    }

    @Test
    public void createUserAccountSucces() throws Exception
    {
        repository.save(mary);
        assertNotNull(mary.getId());
    }

    @Test
    public void getUserById() throws Exception
    {
        UserAccount userAccount = repository.findById(mary.getId());
        assertNotNull(userAccount);
        assertNotNull(userAccount.getId());
        assertEquals(mary.getFullName(), userAccount.getFullName());
    }

    @Test
    public void givenUsersByNameWhenTheFullNameContainsCharactersCorrect()
    {
        UserAccountSpecification spec = new UserAccountSpecification(new SearchCriteria("fullName", SearchOperation.CONTAINS, "Ma"));
        List<UserAccount> results = repository.findAll(spec);
        assertTrue(results.contains(mary));
        assertFalse(results.contains(john));
    }
}