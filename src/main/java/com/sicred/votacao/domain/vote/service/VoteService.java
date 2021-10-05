package com.sicred.votacao.domain.vote.service;

import com.sicred.votacao.domain.session.service.SessionService;
import com.sicred.votacao.domain.vote.exception.AssociateAlreadyVotedException;
import com.sicred.votacao.domain.vote.exception.VoteNotValidSessionCloseException;
import com.sicred.votacao.domain.vote.model.Vote;
import com.sicred.votacao.domain.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final SessionService sessionService;

    public VoteService(VoteRepository voteRepository, SessionService sessionService) {
        this.voteRepository = voteRepository;
        this.sessionService = sessionService;
    }

    public Vote saveVote(Vote vote) {
        isVoteValid(vote);
        return voteRepository.save(vote);
    }

    private void isVoteValid(Vote vote) {
        isVoteInSessionValid(vote.getSession().getId());
        isAssociateAlreadyVoted(vote);
    }

    private void isAssociateAlreadyVoted(Vote vote) {
        if(voteRepository.existsByAssociateAndSession(vote.getAssociate(),vote.getSession()).equals(Boolean.TRUE)){
            throw new AssociateAlreadyVotedException();
        }

    }


    private void isVoteInSessionValid(Long idSession) {
        if(sessionService.isSessionOpened(idSession).equals(Boolean.FALSE)){
            throw  new VoteNotValidSessionCloseException();
        }
    }
}
