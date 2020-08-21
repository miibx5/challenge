/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 11:37:18
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.repositories.user;


import br.com.edersystems.challenge.model.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, String>, JpaSpecificationExecutor<UserAccount> {

    UserAccount findById(UUID userId);
}
