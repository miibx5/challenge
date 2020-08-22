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
package br.com.edersystems.challenge.model.repositories.product;


import br.com.edersystems.challenge.model.entities.Product;
import br.com.edersystems.challenge.model.entities.Stock;
import br.com.edersystems.challenge.model.entities.UserAccount;
import br.com.edersystems.challenge.model.entities.enums.ProductSort;
import br.com.edersystems.challenge.model.repositories.criteria.SearchCriteria;
import br.com.edersystems.challenge.model.repositories.user.UserAccountRepository;
import br.com.edersystems.challenge.util.SearchOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserAccountRepository accountRepository;

    private UserAccount mary;

    private UserAccount john;

    public ProductRepositoryTest() {
    }

    @Before
    public void init() {
        mary = accountRepository.save(new UserAccount("email1@test.com", "Mary Test", "123456"));
        john = accountRepository.save(new UserAccount("email2@test.com", "John Test", "123456"));

        Product productOne = new Product("0001", "Product One", BigDecimal.valueOf(10), new Stock(1), mary);
        repository.save(productOne);
        Product productTwo = new Product("0002", "Product Two", BigDecimal.valueOf(10), new Stock(1), mary);
        repository.save(productTwo);
        Product productThree = new Product("0003", "Product Three", BigDecimal.valueOf(10), new Stock(1), john);
        repository.save(productThree);
    }

    @Test
    public void saveProductSuccessfully() throws Exception {
        Product product = new Product("0001", "Product Zero", BigDecimal.valueOf(10), new Stock(1), mary);
        repository.save(product);
        assertNotNull(product.getId());
    }

    @Test
    public void getProductByIdSuccessfully() throws Exception {
        Product product = repository.save(new Product("0001", "Product Zero", BigDecimal.valueOf(10), new Stock(1), mary));
        Product productRetrivied = repository.findById(product.getId());
        assertNotNull(productRetrivied);
    }

    @Test
    public void getProductByFilteringTheNameSuccessfully() throws Exception {
        final String code = "";
        final String name = "Product One";
        ProductSpeficationBuilder builder = getProductSpeficationBuilder(mary.getId(), name, code);
        System.out.println(builder.build().toString());

        List<Product> products = repository.findAll(builder.build(), PageRequest.of(0, 100)).getContent();
        assertNotNull(products);
        assertEquals(products.size(), BigDecimal.ONE.intValue());
    }

    @Test
    public void getProductByFilteringTheCodeSuccessfully() throws Exception {
        final String order = "ASC";
        final String code = "0001";
        final String name = "";
        ProductSpeficationBuilder builder = getProductSpeficationBuilder(mary.getId(), name, code);
        System.out.println(builder.build().toString());

        List<Product> products = repository.findAll(builder.build(), PageRequest.of(0, 100, getProductSortByOrder(order))).getContent();
        assertNotNull(products);
        assertEquals(products.size(), BigDecimal.ONE.intValue());
    }

    @Test
    public void getProductByFilteringThePartialNameSuccessfully() throws Exception {
        final String order = "ASC";
        final String code = "";
        final String name = "Product";
        ProductSpeficationBuilder builder = getProductSpeficationBuilder(mary.getId(), name, code);
        System.out.println(builder.build().toString());

        List<Product> products = repository.findAll(builder.build(), PageRequest.of(0, 100, getProductSortByOrder(order))).getContent();
        assertNotNull(products);
        assertEquals(products.size(), 2);
    }

    @Test
    public void changeProductuccessfully() throws Exception {
        final String productNameChanged = "Product Zero Changeed";
        final BigDecimal priceChanged = BigDecimal.valueOf(100);
        Product product = repository.save(new Product("0001", "Product Zero", BigDecimal.valueOf(10), new Stock(1), mary));
        product.setName(productNameChanged);
        product.setPrice(priceChanged);
        product = repository.save(product);
        assertNotNull(product.getId());
        assertEquals(product.getName(), productNameChanged);
        assertEquals(product.getPrice(), priceChanged);
    }

    @Test
    public void getProductByIduccessfully() throws Exception {
        Product product = repository.save(new Product("0001", "Product Zero", BigDecimal.valueOf(10), new Stock(1), mary));
        Product productRetrivied = repository.findById(product.getId());
        assertNotNull(productRetrivied);
        assertEquals(product.getPrice(), productRetrivied.getPrice());
    }

    @Test
    public void deleteProductSuccessfully() throws Exception {
        Product product = repository.save(new Product("0001", "Product Zero", BigDecimal.valueOf(10), new Stock(1), mary));
        product.setActive(Boolean.FALSE);
        assertSame(product.isActive(), Boolean.FALSE);
    }


    private ProductSpeficationBuilder getProductSpeficationBuilder(UUID ownerId, String name, String code) {
        ProductSpeficationBuilder builder = new ProductSpeficationBuilder();
        UserAccount owner = accountRepository.findById(ownerId);
        builder.getParams().add(new SearchCriteria("owner", SearchOperation.EQUALITY, owner));
        if(Objects.nonNull(name) && !name.isBlank()) {
            builder.getParams().add(new SearchCriteria("name", SearchOperation.CONTAINS, name));
        }
        if(Objects.nonNull(code) && !code.isBlank()) {
            builder.getParams().add(new SearchCriteria("code", SearchOperation.CONTAINS, code));
        }
        return builder;
    }

    private Sort getProductSortByOrder(String order) {
        if(order.equals(ProductSort.ASC.name())) {
            return Sort.by(Sort.Order.asc("code"), Sort.Order.asc("name"), Sort.Order.asc("price"));
        }
        return Sort.by(Sort.Order.desc("code"), Sort.Order.desc("name"), Sort.Order.desc("price"));
    }
}
