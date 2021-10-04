package com.sicred.votacao.domain.session.controller.request;

import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.user.model.Associate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class SessionRequest {

    @NotBlank
    private final String name;

    private final LocalDateTime endSession;

    @NotNull
    private final Long idUserCreate;

    public SessionRequest(String name, LocalDateTime endSession, Long idUserCreate) {
        this.name = name;
        this.endSession = endSession;
        this.idUserCreate = idUserCreate;
    }

    public Session toSession(){
        return Session.of(name,endSession, Associate.of(idUserCreate));
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public Long getIdUserCreate() {
        return idUserCreate;
    }

}
