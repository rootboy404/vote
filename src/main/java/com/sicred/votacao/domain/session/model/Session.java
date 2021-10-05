package com.sicred.votacao.domain.session.model;

import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.vote.model.Vote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class    Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime endSession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_associate")
    private Associate associate;

    @OneToMany(mappedBy="session")
    private List<Vote> votes;

    public Session() {
    }

    public Session(Long id, String name, LocalDateTime endSession, Associate associate, List<Vote> votes) {
        this.id = id;
        this.name = name;
        this.endSession = endSession;
        this.associate = associate;
        this.votes = votes;
    }

    private Session(String name, LocalDateTime endSession, Associate associate) {
        this.name = name;
        this.endSession = endSession;
        this.associate = associate;
    }

    public Session(Long id) {
        this.id = id;
    }

    public static Session of(String name, LocalDateTime endSession, Associate associate) {

        LocalDateTime endSessionValue = endSession != null ? endSession :
                LocalDateTime.now().plusMinutes(1);
        return new Session(name, endSessionValue, associate);
    }

    public static Session of(Long id) {
        return new Session(id);
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

    public Boolean getWinner(){
        if(votes.isEmpty() || getTotalNoVotes().equals(getTotalYesVotes()) || getStatus().equals(Status.OPENED)){
            return null;
        }
        return getTotalYesVotes()>getTotalNoVotes()?Boolean.TRUE:Boolean.FALSE;
    }

    public Integer getTotalYesVotes(){
        return votes.stream().filter(vote -> vote.getOptionVote().equals(Boolean.TRUE)).collect(Collectors.toList()).size();
    }

    public Integer getTotalNoVotes(){
        return votes.stream().filter(vote -> vote.getOptionVote().equals(Boolean.FALSE)).collect(Collectors.toList()).size();
    }


    public Associate getAssociate() {
        return associate;
    }

    public List<Vote> getVotes() {
        return votes;
    }
}
