package com.sicred.votacao.domain.associate.controller.request;

import com.sicred.votacao.domain.associate.model.Associate;

import javax.validation.constraints.NotBlank;

public class AssociateRequest {

    @NotBlank
    private final String cpf;

    @NotBlank
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
