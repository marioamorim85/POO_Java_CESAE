package com.cesae.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe Turma
 * 
 * Representa uma turma de alunos associada a um curso.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome da turma (ex: SD-2025-A) */
    private String nome;

    /** Capacidade máxima de alunos */
    private int capacidadeMaxima;

    /** Lista de alunos inscritos */
    @OneToMany(mappedBy = "turma")
    @JsonIgnoreProperties({ "turma", "notas" })
    private List<Aluno> alunos = new ArrayList<>();

    /** Lista de UCs da turma */
    @ManyToMany
    @JoinTable(name = "turma_ucs", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "uc_id"))
    @JsonIgnoreProperties({ "professor" })
    private List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();

    /** Curso ao qual a turma pertence */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({ "turmas", "unidadesCurriculares" })
    private Curso curso;

    // ========== CONSTRUTORES ==========

    public Turma() {
    }

    public Turma(String nome, int capacidadeMaxima, Curso curso) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima > 0 ? capacidadeMaxima : 1;
        this.alunos = new ArrayList<>();
        this.unidadesCurriculares = new ArrayList<>();
        this.curso = curso;
    }

    public Turma(String nome, int capacidadeMaxima) {
        this(nome, capacidadeMaxima, null);
    }

    // ========== MÉTODOS GETTERS ==========

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    public Curso getCurso() {
        return curso;
    }

    public int getNumeroAlunos() {
        return alunos != null ? alunos.size() : 0;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima > 0 ? capacidadeMaxima : 1;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setUnidadesCurriculares(List<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========

    /**
     * Verifica se a turma tem vagas disponíveis.
     * 
     * @return true se ainda há vagas
     */
    public boolean temVagas() {
        return getNumeroAlunos() < capacidadeMaxima;
    }

    /**
     * Calcula a taxa de ocupação da turma.
     * 
     * @return Percentagem de ocupação (0-100).
     */
    public double getTaxaOcupacao() {
        return (double) getNumeroAlunos() / capacidadeMaxima * 100;
    }

    /**
     * Calcula a média de notas de todos os alunos da turma.
     * 
     * @return A média geral ou 0 se não houver alunos com notas
     */
    public double calcularMediaTurma() {
        if (alunos == null || alunos.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        int count = 0;
        for (Aluno aluno : alunos) {
            double media = aluno.calcularMedia();
            if (media > 0) {
                soma += media;
                count++;
            }
        }
        return (count > 0) ? soma / count : 0.0;
    }

    @Override
    public String toString() {
        String cursoNome = (curso != null) ? curso.getNome() : "Sem curso";
        return nome + " | Alunos: " + getNumeroAlunos() + "/" + capacidadeMaxima +
                " | Curso: " + cursoNome;
    }
}
