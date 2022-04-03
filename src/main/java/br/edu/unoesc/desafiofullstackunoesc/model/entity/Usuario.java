package com.springframe.springframe.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Usuario implements Serializable, UserDetails {

    public Usuario(String nome, String email, String senha, Boolean adm){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.adm = adm;
    }

    public Usuario() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sq_usuario")
    private Long codigoUsuario;

    @NotBlank(message = "Nome do usuário deve ser informado!")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Senha deve ser informada!")
    @Column(name = "senha")
    private String senha;

    @NotBlank(message = "E-mail dev ser informado!")
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "codigoMunicipio")
    private Municipio municipio;

    @Column(name = "role")
    private String role;

    @Column(name = "adm")
    private Boolean adm = false;

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

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
