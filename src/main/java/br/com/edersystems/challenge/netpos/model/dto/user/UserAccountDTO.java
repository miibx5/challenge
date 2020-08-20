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
package br.com.edersystems.challenge.netpos.model.dto.user;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserAccountDTO implements java.io.Serializable
{
    private static final long serialVersionUID = -4993851773590471337L;

    private final UUID id;

    private final String email;

    private final String fullName;

    private String password;

    public UserAccountDTO()
    {
        this(null, null, null);
    }

    public UserAccountDTO(UUID id, String email, String fullName)
    {
        this(id, email, fullName, null);
    }

    public UserAccountDTO(UUID id, String email, String fullName, String password)
    {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
