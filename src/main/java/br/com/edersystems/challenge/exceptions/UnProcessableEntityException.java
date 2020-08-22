/*
Project .....................: netPos
Creation Date ...............: 21/08/2020 18:58:37
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnProcessableEntityException extends RuntimeException {

    private static final long serialVersionUID = 2834153981685457196L;

    private String message;

    public UnProcessableEntityException(String message) {
        super(message);
        this.message = message;
    }
}
