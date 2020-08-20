/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:48:23
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.request.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockRequest implements java.io.Serializable
{
    private static final long serialVersionUID = -6074148390360679282L;

    private Integer quantity;
}

