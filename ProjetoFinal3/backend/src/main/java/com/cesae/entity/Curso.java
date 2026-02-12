package com.cesae.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe Curso
 * 
 * Representa um curso do CESAE Digital.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome do curso */
    private String nome;

    /** Tipo de curso (enum) */
    @Enumerated(EnumType.STRING)
    private TipoCurso tipo;

    /** Duração total em meses */
    private int duracaoMeses;

    /** Lista de turmas do curso */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "curso", "alunos", "unidadesCurriculares" })
    private List<Turma> turmas = new ArrayList<>();

    /** Lista de UCs do plano curricular */
    @ManyToMany
    @JoinTable(name = "curso_ucs", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "uc_id"))
    @JsonIgnoreProperties({ "professor" })
    private List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();

    // ========== CONSTRUTORES ==========

    public Curso() {
    }

    public Curso(String nome, TipoCurso tipo, int duracaoMeses) {
        this.nome = nome;
        this.tipo = tipo;
        this.duracaoMeses = duracaoMeses > 0 ? duracaoMeses : 1;
        this.turmas = new ArrayList<>();
        this.unidadesCurriculares = new ArrayList<>();
    }

    public Curso(String nome, int duracaoMeses) {
        this(nome, TipoCurso.FORMACAO, duracaoMeses);
    }

    // ========== MÉTODOS GETTERS ==========

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses > 0 ? duracaoMeses : 1;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void setUnidadesCurriculares(List<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========

    /**
     * Obtém o número total de alunos em todas as turmas.
     * 
     * @return Total de alunos.
     */
    public int getTotalAlunos() {
        int total = 0;
        if (turmas != null) {
            for (Turma turma : turmas) {
                total += turma.getNumeroAlunos();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return nome + " | Tipo: " + tipo + " | " + duracaoMeses + " meses | Turmas: " +
                (turmas != null ? turmas.size() : 0);
    }
}
