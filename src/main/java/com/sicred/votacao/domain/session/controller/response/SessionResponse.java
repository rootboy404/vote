package com.sicred.votacao.domain.session.controller.response;

import com.sicred.votacao.domain.associate.controller.response.AssociateResponse;
import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.session.model.Status;

import java.time.LocalDateTime;

public class SessionResponse {

    private final Long id;
    private final String name;
    private final LocalDateTime endSession;
    private final Status status;
    private final Boolean winner;
    private final Integer yesVotes;
    private final Integer noVotes;
    private final AssociateResponse associate;

    public SessionResponse(Long id, String name, LocalDateTime endSession, Status status, Boolean winner, Integer yesVotes, Integer no, AssociateResponse associate) {
        this.id = id;
        this.name = name;
        this.endSession = endSession;
        this.status = status;
        this.winner = winner;
        this.yesVotes = yesVotes;
        this.noVotes = no;
        this.associate = associate;
    }

    public static SessionResponse of(Session session) {
        if (session == null) return null;
        return new SessionResponse(session.getId(), session.getName(), session.getEndSession(), session.getStatus(), session.getWinner(), session.getTotalYesVotes(), session.getTotalNoVotes(), AssociateResponse.of(session.getAssociate()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public Status getStatus() {
        return status;
    }

    public Boolean getWinner() {
        return winner;
    }

    public AssociateResponse getAssociate() {
        return associate;
    }

    public Integer getYesVotes() {
        return yesVotes;
    }

    public Integer getNoVotes() {
        return noVotes;
    }
}
