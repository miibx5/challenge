/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 13:31:16
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.UUID;


@Getter
public class UserAccountDTO implements java.io.Serializable {

    private static final long serialVersionUID = -4334560224054869972L;

    private final UUID id;

    private final String email;

    private final String fullName;

    @JsonIgnore
    private final String password;

    public UserAccountDTO() {
        this(null, null, null);
    }

    public UserAccountDTO(UUID id, String email, String fullName) {
        this(id, email, fullName, null);
    }

    public UserAccountDTO(UUID id, String email, String fullName, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
