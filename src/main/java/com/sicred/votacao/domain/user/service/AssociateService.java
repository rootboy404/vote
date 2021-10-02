package com.sicred.votacao.domain.user.service;

import com.sicred.votacao.domain.user.exception.CpfNotFoundException;
import com.sicred.votacao.domain.user.model.Associate;
import com.sicred.votacao.domain.user.repository.AssociateRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public Associate save(Associate user){
        return associateRepository.save(user);
    }

    public Associate findUserByCpf(String cpf){
       return associateRepository.findByCpf(cpf).orElseThrow(CpfNotFoundException::new);
    }


}
