package com.springframe.springframe.model.form;

import com.springframe.springframe.model.entity.UnidadeFederativa;

public class UnidadeFederativaForm {

    private String sigla;
    private String nome;

    public UnidadeFederativaForm(){}

    public UnidadeFederativaForm(UnidadeFederativa unidadeFederativa){
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

    public UnidadeFederativa convert() {
        return new UnidadeFederativa(sigla, nome);
    };
}
