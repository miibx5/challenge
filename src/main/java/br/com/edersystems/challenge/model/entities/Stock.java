/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:10:04
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Stock implements java.io.Serializable {

    private static final long serialVersionUID = -4648378817770592792L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "required.quantity")
    @Min(value = 1, message = "required.min_quantity")
    @Max(value = 1000, message = "required.max_quantity")
    @Column(nullable = false)
    private Integer quantity;

    public Stock() {
    }

    public Stock(Integer quantity) {
        this.quantity = quantity;
    }
}
