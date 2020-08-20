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
package br.com.edersystems.challenge.netpos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Stock implements java.io.Serializable
{
    private static final long serialVersionUID = -1680066181620972469L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Integer quantity;

    public Stock()
    {
    }

    public Stock(Integer quantity)
    {
        this.quantity = quantity;
    }
}
