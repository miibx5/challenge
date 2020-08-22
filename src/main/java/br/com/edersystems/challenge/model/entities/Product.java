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
package br.com.edersystems.challenge.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

    private static final long serialVersionUID = 8065388088241555253L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "owner_Id", nullable = false)
    @ManyToOne
    private UserAccount owner;

    @Column(nullable = false)
    private Boolean active;

    @NotBlank(message = "required.code")
    @Column(length = 100, nullable = false)
    private String code;

    @NotBlank(message = "required.name")
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull(message = "required.price")
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    public Product() {
        this(null, null, null, null, null);
    }

    public Product(String code, String name, BigDecimal price, Stock stock, UserAccount owner) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.active = Boolean.TRUE;
        this.owner = owner;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
