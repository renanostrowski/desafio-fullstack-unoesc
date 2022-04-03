package com.springframe.springframe.model.dto;

import com.springframe.springframe.model.entity.AuxilioEmergencial;
import com.springframe.springframe.model.entity.Municipio;

import java.math.BigDecimal;
import java.util.Date;

public class AuxilioEmergencialDTO {

    private Long codigoAuxilio;
    private Date dataConsulta;
    private MunicipioDTO municipio;
    private String numeroParcela;
    private BigDecimal valor;
    private String anoMes;

    public AuxilioEmergencialDTO(){}

    public AuxilioEmergencialDTO(AuxilioEmergencial auxilioEmergencial){
        this.codigoAuxilio = auxilioEmergencial.getCodigoAuxilio();
        this.municipio = new MunicipioDTO(auxilioEmergencial.getMunicipio());
        this.dataConsulta = auxilioEmergencial.getDataConsulta();
        this.numeroParcela = auxilioEmergencial.getNumeroParcela();
        this.valor = auxilioEmergencial.getValorTotal();
        this.anoMes = auxilioEmergencial.getAnoMes();
    }

    public String getAnoMes() {
        return anoMes;
    }

    public void setAnoMes(String anoMes) {
        this.anoMes = anoMes;
    }

    public Long getCodigoAuxilio() {
        return codigoAuxilio;
    }

    public void setCodigoAuxilio(Long codigoAuxilio) {
        this.codigoAuxilio = codigoAuxilio;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public MunicipioDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
