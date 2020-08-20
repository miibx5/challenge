/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 15:57:02
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.netpos.model.request.product;

import br.com.edersystems.challenge.netpos.model.entities.enums.ProductUpdateOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockUpdateRequest implements java.io.Serializable
{
    private static final long serialVersionUID = 4881166267223412024L;

    private ProductUpdateOperation operation;

    private Integer quantity;
}
