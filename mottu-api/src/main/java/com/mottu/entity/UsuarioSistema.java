package com.mottu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO_SISTEMA")
public class UsuarioSistema {

    @Id
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NOME", length = 50)
    private String nome;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "SENHA", length = 20)
    private String senha;

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
