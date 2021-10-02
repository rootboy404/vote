package com.sicred.votacao.domain.user.controller.request;

import com.sicred.votacao.domain.user.model.Associate;

public class AssociateRequest {
    private final String cpf;

    private final String name;


    public AssociateRequest(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public Associate toAssociate() {
       return Associate.of(cpf,name);
    }


    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}
