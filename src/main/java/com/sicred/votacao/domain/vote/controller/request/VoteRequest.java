package com.sicred.votacao.domain.vote.controller.request;

import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.vote.model.Vote;

import javax.validation.constraints.NotNull;

public class VoteRequest {

    @NotNull
    private final Long idAssociate;

    @NotNull
    private final Long idSession;

    @NotNull
    private final Boolean optionVote;

    public VoteRequest(Long idAssociate, Long idSession, Boolean optionVote) {
        this.idAssociate = idAssociate;
        this.idSession = idSession;
        this.optionVote = optionVote;
    }

    public Vote toVote(){
        return Vote.of(Associate.of(idAssociate), Session.of(idSession),optionVote);
    }



    public Long getIdAssociate() {
        return idAssociate;
    }

    public Long getIdSession() {
        return idSession;
    }

    public Boolean getOptionVote() {
        return optionVote;
    }
}
