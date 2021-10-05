package com.sicred.votacao.domain.vote.exception;

import com.sicred.votacao.infra.exception.TranslatableArguments;

public class AssociateAlreadyVotedException extends IllegalArgumentException implements TranslatableArguments {

    public AssociateAlreadyVotedException() {
        super("vote.associate.already.voted");
    }

    @Override
    public String[] arguments() {
        return new String[0];
    }
}
