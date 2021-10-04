package com.sicred.votacao.domain.session.exception;

import com.sicred.votacao.infra.exception.TranslatableArguments;

public class SessionNotFoundException extends IllegalArgumentException implements TranslatableArguments {

    private final Long id;

    public SessionNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String[] arguments() {
        return new String[]{id.toString()};
    }
}
