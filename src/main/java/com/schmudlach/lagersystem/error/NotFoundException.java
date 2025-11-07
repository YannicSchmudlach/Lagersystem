package com.schmudlach.lagersystem.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException{

    String resource;
    String identifier;

    public NotFoundException(String resource, String identifier) {
        super(resource + " mit Id '" + identifier + "' nicht gefunden");
        this.identifier=identifier;
        this.resource=resource;
    }
    public NotFoundException(String resource) {
        super(resource + " kontne nicht gefunden werden '");
        this.resource=resource;
    }
}
