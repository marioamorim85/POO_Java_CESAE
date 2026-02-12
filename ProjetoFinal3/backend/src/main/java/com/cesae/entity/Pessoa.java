package com.cesae.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe Abstracta Pessoa
 * 
 * Representa uma pessoa genérica no sistema académico.
 * Serve como base para as classes Aluno e Professor.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@MappedSuperclass
public abstract class Pessoa {

    /** Nome completo da pessoa */
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    /** Endereço de email */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    /** Número de telefone */
    private String telefone;

    /** Idade da pessoa */
    @Min(value = 0, message = "Idade não pode ser negativa")
    private int idade;

    // ========== CONSTRUTORES ==========

    public Pessoa() {
    }

    public Pessoa(String nome, String email, String telefone, int idade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public Pessoa(String nome, String email, int idade) {
        this(nome, email, "", idade);
    }

    // ========== MÉTODOS GETTERS ==========

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade() {
        return idade;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
