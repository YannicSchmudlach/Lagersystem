package com.schmudlach.lagersystem.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException{

    String attribut;
    String errorValue;

    public BadRequestException(final String attribut, final String errorValue) {
        super(attribut + " wollte mit der Value '" + errorValue + "' inizialisiert werden, was nicht geht");
        this.attribut=attribut;
        this.errorValue=errorValue;
    }

}
