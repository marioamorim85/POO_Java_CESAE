package com.cesae.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe UnidadeCurricular
 * 
 * Representa uma unidade curricular (disciplina) do plano de estudos.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@Entity
@Table(name = "unidades_curriculares")
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome da unidade curricular */
    private String nome;

    /** Código identificador (ex: POO-101) */
    @Column(unique = true)
    private String codigo;

    /** Carga horária total em horas */
    private int cargaHoraria;

    /** Professor responsável pela UC */
    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonIgnoreProperties({ "unidadesCurriculares" })
    private Professor professor;

    // ========== CONSTRUTORES ==========

    public UnidadeCurricular() {
    }

    public UnidadeCurricular(String nome, String codigo, int cargaHoraria, Professor professor) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria > 0 ? cargaHoraria : 1;
        this.professor = professor;
    }

    public UnidadeCurricular(String nome, String codigo, int cargaHoraria) {
        this(nome, codigo, cargaHoraria, null);
    }

    // ========== MÉTODOS GETTERS ==========

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria > 0 ? cargaHoraria : 1;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        String profNome = (professor != null) ? professor.getNome() : "Sem professor";
        return codigo + " - " + nome + " | " + cargaHoraria + "h | Prof: " + profNome;
    }
}
