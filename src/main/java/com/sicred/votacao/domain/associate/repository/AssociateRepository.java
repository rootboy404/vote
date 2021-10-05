package com.sicred.votacao.domain.associate.repository;

import com.sicred.votacao.domain.associate.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Optional<Associate> findByCpf(String cpf);
}
