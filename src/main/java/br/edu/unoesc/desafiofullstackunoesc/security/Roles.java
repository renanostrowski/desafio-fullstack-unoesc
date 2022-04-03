package com.springframe.springframe.security;

public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");

    private String nome;

    private Roles(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
