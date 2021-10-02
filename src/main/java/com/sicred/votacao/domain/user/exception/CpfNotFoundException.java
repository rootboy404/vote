package com.sicred.votacao.domain.user.exception;

import com.sicred.votacao.infra.exception.TranslatableArguments;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CpfNotFoundException extends IllegalArgumentException implements TranslatableArguments {

    public CpfNotFoundException() {
        super("associate.cpf.not.found");
    }

    @Override
    public String[] arguments() {
        return new String[0];
    }
}
