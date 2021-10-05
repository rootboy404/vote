package com.sicred.votacao.domain.associate.service;

import com.sicred.votacao.domain.associate.exception.CpfNotFoundException;
import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.associate.repository.AssociateRepository;
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
       return associateRepository.findByCpf(cpf).orElseThrow(()-> new CpfNotFoundException(cpf));
    }


}
