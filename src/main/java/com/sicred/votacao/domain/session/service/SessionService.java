package com.sicred.votacao.domain.session.service;

import com.sicred.votacao.domain.session.exception.SessionNotFoundException;
import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.session.model.Status;
import com.sicred.votacao.domain.session.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public List<Session> findOpenSession() {
        return sessionRepository.findAllBySessionOpened();
    }

    public List<Session> findCloseSession() {
        return sessionRepository.findAllBySessionClosed();
    }

    public List<Session> findAllSession() {
        return sessionRepository.findAll();
    }

    public Session findSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new SessionNotFoundException(id));
    }

    public Boolean isSessionOpened(Long id) {
        Session session = findSessionById(id);
        return session.getStatus().equals(Status.OPENED) ? Boolean.TRUE : Boolean.FALSE;
    }
}
