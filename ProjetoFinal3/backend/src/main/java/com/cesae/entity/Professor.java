package com.cesae.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Professor - Subclasse de Pessoa
 * 
 * Representa um professor do CESAE Digital.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@Entity
@Table(name = "professores")
public class Professor extends Pessoa {

    /** Número máximo de UCs que um professor pode lecionar */
    private static final int MAX_UCS = 5;

    /** Contador estático para geração automática do número de professor */
    private static int contadorProfessores = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Número de identificação único do professor */
    @Column(unique = true)
    private int numeroProfessor;

    /** Área de especialização */
    private String especialidade;

    /** Salário mensal em euros */
    private double salario;

    /** Lista de Unidades Curriculares que leciona */
    @OneToMany(mappedBy = "professor")
    @JsonIgnoreProperties({ "professor", "turmas" })
    private List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();

    // ========== CONSTRUTORES ==========

    public Professor() {
        this.numeroProfessor = ++contadorProfessores;
    }

    public Professor(String nome, String email, String telefone, int idade,
            String especialidade, double salario) {
        super(nome, email, telefone, idade);
        this.numeroProfessor = ++contadorProfessores;
        this.especialidade = especialidade;
        this.salario = salario >= 0 ? salario : 0;
        this.unidadesCurriculares = new ArrayList<>();
    }

    public Professor(String nome, String email, int idade, String especialidade) {
        this(nome, email, "", idade, especialidade, 0);
    }

    // ========== MÉTODOS GETTERS ==========

    public Long getId() {
        return id;
    }

    public int getNumeroProfessor() {
        return numeroProfessor;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public double getSalario() {
        return salario;
    }

    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    public int getNumeroUCs() {
        return unidadesCurriculares != null ? unidadesCurriculares.size() : 0;
    }

    public static int getMaxUcs() {
        return MAX_UCS;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroProfessor(int numeroProfessor) {
        this.numeroProfessor = numeroProfessor;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setSalario(double salario) {
        this.salario = salario >= 0 ? salario : 0;
    }

    public void setUnidadesCurriculares(List<UnidadeCurricular> unidadesCurriculares) {
        this.unidadesCurriculares = unidadesCurriculares;
    }

    /**
     * Verifica se o professor pode lecionar mais UCs.
     * 
     * @return true se ainda pode adicionar UCs
     */
    public boolean podeAdicionarUC() {
        return unidadesCurriculares.size() < MAX_UCS;
    }

    @Override
    public String toString() {
        return "[" + numeroProfessor + "] " + getNome() + " | Especialidade: " + especialidade +
                " | UCs: " + getNumeroUCs();
    }
}
