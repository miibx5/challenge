/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:44:26
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

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequest implements java.io.Serializable
{
    private static final long serialVersionUID = 4772182543764856723L;

    private String name;

    private BigDecimal price;
}
