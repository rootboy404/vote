package com.sicred.votacao.domain.vote.repository;

import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository  extends JpaRepository<Vote, Long> {
    Boolean existsByAssociateAndSession(Associate associate, Session session);
}
