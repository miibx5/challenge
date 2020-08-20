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
package br.com.edersystems.challenge.netpos.exceptions;

import lombok.Getter;

@Getter
public class GenericError implements java.io.Serializable
{
    private final String code;

    private final String message;

    public GenericError(String code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
