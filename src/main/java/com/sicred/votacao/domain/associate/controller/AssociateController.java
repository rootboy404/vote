package com.sicred.votacao.domain.associate.controller;



import com.sicred.votacao.domain.associate.controller.request.AssociateRequest;
import com.sicred.votacao.domain.associate.controller.response.AssociateResponse;
import com.sicred.votacao.domain.associate.model.Associate;
import com.sicred.votacao.domain.associate.service.AssociateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class AssociateController {

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }


    @PostMapping
    @ApiOperation(value = "Criar um novo associado")
    public AssociateResponse createUser(@Valid @RequestBody  AssociateRequest associateRequest){
        Associate associateSaved = associateService.save(associateRequest.toAssociate());
        return AssociateResponse.of(associateSaved);
    }

    @GetMapping("/{cpf}")
    @ApiOperation(value = "Buscar um novo associado")
    public AssociateResponse findUserByCpf(@PathVariable String cpf){
        Associate associate = associateService.findUserByCpf(cpf);
        return AssociateResponse.of(associate);
    }
}
