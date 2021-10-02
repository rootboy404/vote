package com.sicred.votacao.domain.user.repository;

import com.sicred.votacao.domain.user.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Optional<Associate> findByCpf(String cpf);
}
