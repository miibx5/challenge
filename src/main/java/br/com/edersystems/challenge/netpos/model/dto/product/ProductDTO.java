/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 18:16:40
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO implements java.io.Serializable
{
    private static final long serialVersionUID = -7174814914868601494L;

    private UUID id;

    private String code;

    private String name;

    private BigDecimal price;

    private StockDTO stock;

    public ProductDTO()
    {
        this(null, null, null, null, null);
    }

    public ProductDTO(UUID id, String code, String name, BigDecimal price, StockDTO stock)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
