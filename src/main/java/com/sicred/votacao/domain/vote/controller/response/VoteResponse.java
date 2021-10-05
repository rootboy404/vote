package com.sicred.votacao.domain.vote.controller.response;

import com.sicred.votacao.domain.vote.model.Vote;

public class VoteResponse {

    private final Long id;

    private final Long idAssociate;

    private final Long idSession;

    private final Boolean voteOption;


    public VoteResponse(Long id, Long idAssociate, Long idSession, Boolean voteOption) {
        this.id = id;
        this.idAssociate = idAssociate;
        this.idSession = idSession;
        this.voteOption = voteOption;
    }

    public static VoteResponse of(Vote vote) {
        return new VoteResponse(vote.getId(),
                vote.getAssociate().getId(),
                vote.getSession().getId(),
                vote.getOptionVote());
    }

    public Long getId() {
        return id;
    }

    public Long getIdAssociate() {
        return idAssociate;
    }

    public Long getIdSession() {
        return idSession;
    }

    public Boolean getVoteOption() {
        return voteOption;
    }
}
