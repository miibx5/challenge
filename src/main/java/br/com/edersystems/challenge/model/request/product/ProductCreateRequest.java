/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:47:31
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.request.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCreateRequest {

    private static final long serialVersionUID = 8110793420444796671L;

    private String code;

    private String name;

    private BigDecimal price;

    private ProductStockRequest stock;
}
