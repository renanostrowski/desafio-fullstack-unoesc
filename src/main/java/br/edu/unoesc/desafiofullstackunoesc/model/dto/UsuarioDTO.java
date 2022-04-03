package br.edu.unoesc.desafiofullstackunoesc.model.dto;

import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;

public class UsuarioDTO {

    private Long codigoUsuario;
    private String nome;
    private String email;
    private MunicipioDTO municipio;
    private boolean adm;

    public UsuarioDTO(Usuario usuario){
        this.codigoUsuario = usuario.getCodigoUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.municipio = new MunicipioDTO(usuario.getMunicipio());
        this.adm = usuario.getAdm();
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MunicipioDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioDTO municipio) {
        this.municipio = municipio;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
}
