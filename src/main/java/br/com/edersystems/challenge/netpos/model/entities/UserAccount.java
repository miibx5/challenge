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
package br.com.edersystems.challenge.netpos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_accounts")
public class UserAccount implements java.io.Serializable
{
    private static final long serialVersionUID = -4417742996824260723L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 70, nullable = false, unique = true)
    private final String email;

    @Column(length = 120, name = "full_name", nullable = false)
    private final String fullName;

    @Column(length = 36, nullable = false)
    private final String password;

    @OneToMany(mappedBy = "owner")
    private List<Product> products;

    public UserAccount()
    {
        this(null, null, null);
    }

    public UserAccount(String email, String fullName, String password)
    {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.products = new ArrayList<>();
    }
}
