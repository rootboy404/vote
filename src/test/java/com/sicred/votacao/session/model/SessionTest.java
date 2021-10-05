package com.sicred.votacao.session.model;

import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.session.model.Session;

import com.sicred.votacao.domain.session.model.Status;
import com.sicred.votacao.domain.vote.model.Vote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    @Nested
    @DisplayName("Find status")
    class FindStatusSession{

        @Test
        @DisplayName("should return a a status opened")
        void findOpenedStatus(){
            Session session = createSessionOpened();
            assertThat(session.getStatus()).isEqualTo(Status.OPENED);
        }
        @Test
        @DisplayName("should return a a status closed")
        void findOpenedClosed(){
            Session session = createSessionClosed();
            assertThat(session.getStatus()).isEqualTo(Status.CLOSED);
        }
    }

    @Nested
    @DisplayName("Find winner")
    class FindWinner{

        @Test
        @DisplayName("should return a yes winner")
        void findYesWinner(){
            Session session = createSessionClosedWithWinnerYes();
            assertTrue(session.getWinner());
        }

        @Test
        @DisplayName("should return a no winner")
        void findNoWinner(){
            Session session = createSessionClosedWithWinnerNo();
            assertFalse(session.getWinner());
        }

        @Test
        @DisplayName("should draw and return null")
        void findDraw(){
            Session session = createSessionClosedWithDraw();
            assertNull(session.getWinner());
        }

        @Test
        @DisplayName("should closed session and return null")
        void findNullInClosedSession(){
            Session session = createSessionOpenedWithVote();
            assertNull(session.getWinner());
        }

    }

    private Session createSessionClosedWithWinnerYes() {
        return new Session(1L,"Sessão", LocalDateTime.now().minusMinutes(10),
                Associate.of(1L), Arrays.asList(new Vote(1L,Associate.of(1L),Session.of(1L),Boolean.TRUE),
                new Vote(2L,Associate.of(2L),Session.of(1L),Boolean.TRUE),
                new Vote(3L,Associate.of(3L),Session.of(1L),Boolean.FALSE)));
    }

    private Session createSessionClosedWithWinnerNo() {
        return new Session(1L,"Sessão", LocalDateTime.now().minusMinutes(10),
                Associate.of(1L), Arrays.asList(new Vote(1L,Associate.of(1L),Session.of(1L),Boolean.TRUE),
                new Vote(2L,Associate.of(2L),Session.of(1L),Boolean.FALSE),
                new Vote(3L,Associate.of(3L),Session.of(1L),Boolean.FALSE)));
    }


    private Session createSessionClosedWithDraw() {
        return new Session(1L,"Sessão", LocalDateTime.now().minusMinutes(10),
                Associate.of(1L), Arrays.asList(new Vote(1L,Associate.of(1L),Session.of(1L),Boolean.TRUE),
                new Vote(2L,Associate.of(2L),Session.of(1L),Boolean.FALSE)));
    }

    private Session createSessionOpenedWithVote() {
        return new Session(1L,"Sessão", LocalDateTime.now().plusMinutes(10),
                Associate.of(1L), Arrays.asList(new Vote(1L,Associate.of(1L),Session.of(1L),Boolean.TRUE),
                new Vote(2L,Associate.of(2L),Session.of(1L),Boolean.FALSE),
                new Vote(3L,Associate.of(3L),Session.of(1L),Boolean.FALSE)));
    }

    private Session createSessionOpened() {
        return new Session(1L,"Sessão", LocalDateTime.now().plusMinutes(10), Associate.of(1L), List.of());
    }

    private Session createSessionClosed() {
        return new Session(1L,"Sessão", LocalDateTime.now().minusMinutes(10), Associate.of(1L), List.of());
    }
}
