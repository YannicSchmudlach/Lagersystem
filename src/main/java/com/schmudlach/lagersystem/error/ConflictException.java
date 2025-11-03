package com.schmudlach.lagersystem.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class ConflictException extends RuntimeException {

    String firstValue;
    String secondValue;

    public ConflictException(String firstValue, String secondValue) {
        super("Obejekt mit firstValue '" + firstValue + "' und secoundValue "+ secondValue +" wurde bereits in die db geschrieben");
        this.firstValue=firstValue;
        this.secondValue=secondValue;
    }
    public ConflictException(String firstValue) {
        super("Obejekt mit firstValue '" + firstValue + "'  wurde bereits in die db geschrieben");
        this.firstValue=firstValue;
    }
}
