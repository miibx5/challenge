/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 16:16:16
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.repositories.product;

import br.com.edersystems.challenge.netpos.model.entities.Product;
import br.com.edersystems.challenge.netpos.model.entities.Stock;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import br.com.edersystems.challenge.netpos.model.repositories.user.UserAccountRepository;
import br.com.edersystems.challenge.netpos.model.request.user.CreateUserAccountRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest
{
    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserAccountRepository accountRepository;

    private Product product;

    private UserAccount mary;

    @Before
    public void init()
    {
        CreateUserAccountRequest userAccountRequest = new CreateUserAccountRequest();
        userAccountRequest.setEmail("email1@test.com");
        userAccountRequest.setFullName("Mary Test");
        userAccountRequest.setPassword("123456");
        mary = accountRepository.save(new UserAccount("email1@test.com", "Mary Test", "123456"));
    }

    @Test
    public void saveProductSuccessfully() throws Exception
    {
        product = new Product("0001", "Product One", BigDecimal.valueOf(10), new Stock(1), mary);
        repository.save(product);
        assertNotNull(product.getId());
    }
}
