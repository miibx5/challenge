/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 18:17:37
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.model.dto.product;

import lombok.Getter;

@Getter
public class StockDTO implements java.io.Serializable {

    private static final long serialVersionUID = -1563252113821201743L;

    private Integer quantity;

    public StockDTO() {
    }

    public StockDTO(Integer quantity) {
        this.quantity = quantity;
    }
}
