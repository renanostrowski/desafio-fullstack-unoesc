package com.springframe.springframe.model.entity;

import javax.persistence.*;

@Entity
public class Municipio {

    public Municipio(){

    }

    public Municipio(Long codigoIBGE, String nomeIBGE, String codigoRegiao, String pais){
        this.codigoIBGE = codigoIBGE;
        this.nomeIBGE = nomeIBGE;
        this.codigoRegiao = codigoRegiao;
        this.pais = pais;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sq_municipio")
    @Column(name = "codigoMunicipio")
    private Long codigoMunicipio;

    @Column(name = "codigoIBGE")
    private Long codigoIBGE;

    @Column(name = "nomeIBGE")
    private String nomeIBGE;

    @Column(name = "codigoRegiao")
    private String codigoRegiao;

    @Column(name = "pais")
    private String pais;

    @ManyToOne
    @JoinColumn(name = "codigoUF")
    private UnidadeFederativa unidadeFederativa;

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Long getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(Long codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public String getNomeIBGE() {
        return nomeIBGE;
    }

    public void setNomeIBGE(String nomeIBGE) {
        this.nomeIBGE = nomeIBGE;
    }

    public String getCodigoRegiao() {
        return codigoRegiao;
    }

    public void setCodigoRegiao(String codigoRegiao) {
        this.codigoRegiao = codigoRegiao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }
}
