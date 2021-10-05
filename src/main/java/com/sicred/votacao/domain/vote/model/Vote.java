package com.sicred.votacao.domain.vote.model;

import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.associate.model.Associate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_associate")
    private Associate associate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_session")
    private Session session;

    @Column
    private Boolean optionVote;

    public Vote() {
    }

    public Vote(Long id, Associate associate, Session session, Boolean vote) {
        this.id = id;
        this.associate = associate;
        this.session = session;
        this.optionVote = vote;
    }

    private Vote(Associate associate, Session session, Boolean optionVote) {
        this.associate = associate;
        this.session = session;
        this.optionVote = optionVote;
    }

    public static Vote of(Associate associate, Session session, Boolean optionVote) {
        return new Vote(associate,session,optionVote);
    }

    public Long getId() {
        return id;
    }

    public Associate getAssociate() {
        return associate;
    }

    public Session getSession() {
        return session;
    }

    public Boolean getOptionVote() {
        return optionVote;
    }
}
