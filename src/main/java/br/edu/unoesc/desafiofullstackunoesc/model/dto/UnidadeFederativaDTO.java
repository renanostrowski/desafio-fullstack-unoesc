package com.springframe.springframe.model.dto;

import com.springframe.springframe.model.entity.UnidadeFederativa;

public class UnidadeFederativaDTO {
    private String sigla;
    private String nome;

    public UnidadeFederativaDTO(){}

    public UnidadeFederativaDTO(UnidadeFederativa unidadeFederativa){
        this.nome = unidadeFederativa.getNome();
        this.sigla = unidadeFederativa.getSigla();
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
