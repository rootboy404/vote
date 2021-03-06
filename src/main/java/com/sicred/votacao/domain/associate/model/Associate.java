package com.sicred.votacao.domain.associate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String cpf;

    @Column
    private String name;


    //default constructor for hibernate
    public Associate() {
    }

    public Associate(Long id, String cpf, String name) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
    }

    private Associate(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    private Associate(Long id) {
        this.id=id;
    }

    public static Associate of(String cpf, String name) {
        return new Associate(cpf,name);
    }

    public static Associate of(Long id) {
        return new Associate(id);
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

//    public List<Session> getSessions() {
//        return sessions;
//    }
}
