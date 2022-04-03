package com.springframe.springframe.model.form;

import java.util.Date;

public class ConsultaAuxilioForm {

    private String codigoIBGE;
    private int ano;
    private int mes;

    public ConsultaAuxilioForm(){}

    public ConsultaAuxilioForm(String codigoIBGE, int ano, int mes){
        this.codigoIBGE = codigoIBGE;
        this.ano = ano;
        this.mes = mes;
    }

    public String getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(String codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
