package com.sicred.votacao.domain.associate.controller.response;

import com.sicred.votacao.domain.associate.model.Associate;

public class AssociateResponse {

    private Long id;

    private String cpf;

    private String name;

    public static AssociateResponse of(Associate associate) {
        if(associate == null) return null;
        return new AssociateResponse(associate);
    }

    public AssociateResponse(Associate associate) {
        this.id = associate.getId();
        this.cpf = associate.getCpf();
        this.name = associate.getName();
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}
