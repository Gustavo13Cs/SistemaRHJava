package com.AppRH.AppRH.Models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vaga implements Serializable {

    //faz o controle do versionamento,
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //vai gerar automaticamente toda vez q criar uma vaga
    private long codigo;

    //n aceita vazio
    @NotEmpty
    private String nome;

    //n aceita vazio
    @NotEmpty
    private String descricao;

    //n aceita vazio
    @NotEmpty 
    private String data;

    //n aceita vazio
    @NotEmpty
    private String salario;

    //quando deletar a vaga, vai deletar os candidatos tbm
    @OneToMany(mappedBy = "vaga",cascade = CascadeType.REMOVE)
    private List<Candidato> candidatos;


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    

}
