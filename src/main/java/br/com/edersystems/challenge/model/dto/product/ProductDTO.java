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
package br.com.edersystems.challenge.model.dto.product;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProductDTO implements java.io.Serializable {

    private static final long serialVersionUID = 4016771500960239019L;

    private final UUID id;

    private final String code;

    private final String name;

    private final BigDecimal price;

    private final StockDTO stock;

    public ProductDTO() {
        this(null, null, null, null, null);
    }

    public ProductDTO(UUID id, String code, String name, BigDecimal price, StockDTO stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
