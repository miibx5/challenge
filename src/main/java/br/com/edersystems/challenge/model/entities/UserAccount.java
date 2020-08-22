/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 11:36:56
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccount implements java.io.Serializable {

    private static final long serialVersionUID = 3420946827490225854L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "required.email")
    @Column(length = 70, nullable = false, unique = true)
    private final String email;

    @NotBlank(message = "required.fullName")
    @Column(length = 120, name = "full_name", nullable = false)
    private final String fullName;

    @NotBlank(message = "required.password")
    @Column(length = 36, nullable = false)
    private final String password;

    public UserAccount() {
        this(null, null, null);
    }

    public UserAccount(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
