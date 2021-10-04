package com.sicred.votacao.domain.session.model;

import com.sicred.votacao.domain.user.model.Associate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime endSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_associate")
    private Associate associate;

    public Session() {
    }

    public Session(Long id, String name, LocalDateTime endSession, Associate associate) {
        this.id = id;
        this.name = name;
        this.endSession = endSession;
        this.associate = associate;
    }

    private Session(String name, LocalDateTime endSession, Associate associate) {
        this.name = name;
        this.endSession = endSession;
        this.associate = associate;
    }

    public static Session of(String name, LocalDateTime endSession, Associate associate) {

        LocalDateTime endSessionValue = endSession != null ? endSession :
                LocalDateTime.now().plusMinutes(1);
        return new Session(name, endSessionValue, associate);
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
        if (this.endSession.isBefore(LocalDateTime.now())) {
            return Status.CLOSED;
        }
        return Status.OPENED;

    }

    public Associate getAssociate() {
        return associate;
    }
}
