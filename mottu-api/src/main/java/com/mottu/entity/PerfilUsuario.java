package com.mottu.entity;

public enum PerfilUsuario {
    ADMIN("Administrador"),
    OPERADOR("Operador"),
    MANUTENCAO("Manutenção");
    
    private final String descricao;
    
    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}