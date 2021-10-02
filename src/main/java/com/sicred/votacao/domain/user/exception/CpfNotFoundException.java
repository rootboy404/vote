package com.sicred.votacao.domain.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CpfNotFoundException extends IllegalArgumentException {

    public CpfNotFoundException() {
        super("associate.cpf.not.found");
    }
}
