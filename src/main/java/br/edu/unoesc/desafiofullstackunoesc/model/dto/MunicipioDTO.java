package br.edu.unoesc.desafiofullstackunoesc.model.dto;

import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.UnidadeFederativa;

public class MunicipioDTO {
    private Long codigoIBGE;
    private String nomeIBGE;
    private String codigoRegiao;
    private String pais;
    private UnidadeFederativaDTO unidadeFederativaDTO;

    public MunicipioDTO(Municipio municipio) {
        this.codigoIBGE = municipio.getCodigoIBGE();
        this.nomeIBGE = municipio.getNomeIBGE();
        this.codigoRegiao = municipio.getCodigoRegiao();
        this.pais = municipio.getPais();
        this.unidadeFederativaDTO = new UnidadeFederativaDTO(municipio.getUnidadeFederativa());
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

    public UnidadeFederativaDTO getUnidadeFederativaDTO() {
        return unidadeFederativaDTO;
    }

    public void setUnidadeFederativaDTO(UnidadeFederativaDTO unidadeFederativaDTO) {
        this.unidadeFederativaDTO = unidadeFederativaDTO;
    }
}
