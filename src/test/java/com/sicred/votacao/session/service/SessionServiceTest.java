package com.sicred.votacao.session.service;

import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.session.exception.SessionNotFoundException;
import com.sicred.votacao.domain.session.repository.SessionRepository;
import com.sicred.votacao.domain.session.model.Session;

import com.sicred.votacao.domain.session.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    private SessionService sessionService;

    @BeforeEach
    public void setup(){
        openMocks(this);
        sessionService = new SessionService(sessionRepository);
    }


    @Nested
    @DisplayName("Find session by id")
    class FindSessionById{

        @Test
        @DisplayName("should return a session when search by id")
        void shouldFindSessionByIdAndReturnSession(){
            Session sessionReturned = createSession();
            given(sessionRepository.findById(1L)).willReturn(Optional.of(sessionReturned));
            Session session = sessionService.findSessionById(1L);
            assertThat(session).isEqualTo(sessionReturned);
        }

        @Test
        @DisplayName("should return a session when search by id not Exist")
        void shouldFindSessionByIdAndReturnException(){
            given(sessionRepository.findById(1L)).willReturn(Optional.empty());
            assertThatThrownBy(() -> {
                sessionService.findSessionById(1L);
            }).isInstanceOf(SessionNotFoundException.class);

            SessionNotFoundException exception = assertThrows(SessionNotFoundException.class, () -> {
                sessionService.findSessionById(1L);
                fail("should not pass here!");
            });
        }

    }

    @Nested
    @DisplayName("Find status session")
    class FindStatusBySessionBy{

        @Test
        @DisplayName("should return a is opened session")
        void shouldFindStatusBySessionAndReturnStatusOpened(){
            Session sessionOpened = createSessionOpened();
            given(sessionRepository.findById(1L)).willReturn(Optional.of(sessionOpened));
            Boolean isOpened = sessionService.isSessionOpened(1L);
            assertTrue(isOpened);
        }


        @Test
        @DisplayName("should return a is closed session")
        void shouldFindStatusBySessionAndReturnStatusClosed(){
            Session sessionOpened = createSessionClosed();
            given(sessionRepository.findById(1L)).willReturn(Optional.of(sessionOpened));
            Boolean isOpened = sessionService.isSessionOpened(1L);
            assertFalse(isOpened);
        }
    }

    private Session createSessionClosed() {
        return new Session(1L,"Sessão", LocalDateTime.now().minusMinutes(10), Associate.of(1L), List.of());
    }

    private Session createSessionOpened() {
        return new Session(1L,"Sessão", LocalDateTime.now().plusMinutes(10), Associate.of(1L), List.of());
    }

    private Session createSession() {
        return new Session(1L,"Sessão", LocalDateTime.now(), Associate.of(1L), List.of());
    }


}
