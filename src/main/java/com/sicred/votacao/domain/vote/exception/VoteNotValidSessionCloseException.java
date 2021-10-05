package com.sicred.votacao.domain.vote.exception;

import com.sicred.votacao.infra.exception.TranslatableArguments;

public class VoteNotValidSessionCloseException extends IllegalArgumentException implements TranslatableArguments {

    public VoteNotValidSessionCloseException() {
        super("vote.not.valid.session.close");
    }

    @Override
    public String[] arguments() {
        return new String[0];
    }
}
