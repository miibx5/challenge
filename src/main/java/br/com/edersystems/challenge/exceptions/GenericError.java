/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 12:35:50
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.exceptions;

import lombok.Getter;

@Getter
public class GenericError implements java.io.Serializable {

    private static final long serialVersionUID = 835387558821498202L;

    private final String code;

    private final String message;

    public GenericError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
