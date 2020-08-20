/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:37:33
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.repositories.product;

import br.com.edersystems.challenge.netpos.model.entities.Product;
import br.com.edersystems.challenge.netpos.model.entities.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String>, JpaSpecificationExecutor<Product>
{
    Product findById(UUID productId);

    Product findProductByOwnerAndId(UserAccount owner, UUID productId);

    List<Product> findProductByOwnerId(UUID ownerId, Specification<Product> specification, Pageable pageable);
}
