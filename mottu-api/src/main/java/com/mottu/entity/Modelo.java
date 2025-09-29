package com.mottu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MODELO")
public class Modelo {

    @Id
    @Column(name = "ID_MODELO")
    private Integer idModelo;

    @Column(name = "FABRICANTE", length = 50)
    private String fabricante;

    @Column(name = "NOME_MODELO", length = 50)
    private String nomeModelo;

    @Column(name = "CILINDRADA")
    private Integer cilindrada;

    @Column(name = "TIPO", length = 30)
    private String tipo;

    public Integer getIdModelo() { return idModelo; }
    public void setIdModelo(Integer idModelo) { this.idModelo = idModelo; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public String getNomeModelo() { return nomeModelo; }
    public void setNomeModelo(String nomeModelo) { this.nomeModelo = nomeModelo; }
    public Integer getCilindrada() { return cilindrada; }
    public void setCilindrada(Integer cilindrada) { this.cilindrada = cilindrada; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
