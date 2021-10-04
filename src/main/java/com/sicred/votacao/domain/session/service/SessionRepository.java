package com.sicred.votacao.domain.session.service;

import com.sicred.votacao.domain.session.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {


    @Query("FROM Session session Where session.endSession > CURRENT_TIMESTAMP")
    List<Session> findAllBySessionOpened();

    @Query("FROM Session session Where session.endSession < CURRENT_TIMESTAMP")
    List<Session> findAllBySessionClosed();
}
