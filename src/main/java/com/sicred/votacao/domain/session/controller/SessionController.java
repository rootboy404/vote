package com.sicred.votacao.domain.session.controller;


import com.sicred.votacao.domain.session.controller.request.SessionRequest;
import com.sicred.votacao.domain.session.controller.response.SessionResponse;
import com.sicred.votacao.domain.session.model.Session;
import com.sicred.votacao.domain.session.service.SessionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping()
    @ApiOperation(value = "Criar uma nova sessão")
    public SessionResponse create(@RequestBody @Valid SessionRequest sessionRequest) {
        Session sessionSaved = sessionService.save(sessionRequest.toSession());
        return SessionResponse.of(sessionSaved);
    }

    @GetMapping("/open")
    @ApiOperation(value = "Buscar todas as sessão abertas")
    public List<SessionResponse> findOpenSession() {
        List<Session> openSession = sessionService.findOpenSession();
        return openSession.stream().map(SessionResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/close")
    @ApiOperation(value = "Buscar todas as sessão fechadas")
    public List<SessionResponse> findCloseSession() {
        List<Session> closeSession = sessionService.findCloseSession();
        return closeSession.stream().map(SessionResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Buscar todas as sessão")
    public List<SessionResponse> findAllSession() {
        List<Session> allSession = sessionService.findAllSession();
        return allSession.stream().map(SessionResponse::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar uma sessão por um id")
    public SessionResponse findSession(@PathVariable Long id) {
        Session session = sessionService.findSessionById(id);
        return SessionResponse.of(session);
    }

}
