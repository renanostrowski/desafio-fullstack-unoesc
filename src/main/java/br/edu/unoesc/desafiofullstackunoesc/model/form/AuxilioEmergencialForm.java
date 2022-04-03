package com.springframe.springframe.model.form;


import com.springframe.springframe.model.entity.AuxilioEmergencial;

import java.math.BigDecimal;
import java.util.Date;

public class AuxilioEmergencialForm {

    private Long codigoAuxilio;
    private Date dataConsulta;
    private MunicipioForm municipio;
    private String numeroParcela;
    private BigDecimal valor;
    private String anoMes;

    public AuxilioEmergencialForm(){}

    public AuxilioEmergencialForm(AuxilioEmergencial auxilioEmergencial){
        this.codigoAuxilio = auxilioEmergencial.getCodigoAuxilio();
        this.municipio = new MunicipioForm(auxilioEmergencial.getMunicipio());
        this.numeroParcela = auxilioEmergencial.getNumeroParcela();
        this.dataConsulta = auxilioEmergencial.getDataConsulta();
        this.valor = auxilioEmergencial.getValorTotal();
        this.anoMes = auxilioEmergencial.getAnoMes();
    }

    public String getAnoMes() {
        return anoMes;
    }

    public void setAnoMes(String anoMes) {
        this.anoMes = anoMes;
    }

    public MunicipioForm getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioForm municipio) {
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

    public AuxilioEmergencial convert(){
        return new AuxilioEmergencial();
    }

    public Long getCodigoAuxilio() {
        return codigoAuxilio;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setCodigoAuxilio(Long codigoAuxilio) {
        this.codigoAuxilio = codigoAuxilio;
    }
}
