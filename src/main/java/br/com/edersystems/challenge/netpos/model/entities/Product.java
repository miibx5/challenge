/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:07:56
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
import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements java.io.Serializable
{
    private static final long serialVersionUID = 7721564587814124538L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "owner_Id", nullable = false)
    @ManyToOne
    private UserAccount owner;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 100, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @OneToOne
    private Stock stock;

    public Product()
    {
        this(null, null, null, null, null);
    }

    public Product(String code, String name, BigDecimal price, Stock stock, UserAccount owner)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.active = Boolean.TRUE;
        this.owner = owner;
    }
}
