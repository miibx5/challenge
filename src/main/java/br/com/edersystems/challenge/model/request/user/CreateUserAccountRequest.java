/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 11:45:13
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAccountRequest implements java.io.Serializable {

    private static final long serialVersionUID = 3161920962143236347L;

    private String email;

    private String fullName;

    private String password;
}
