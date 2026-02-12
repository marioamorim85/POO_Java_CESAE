package com.cesae.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Aluno - Subclasse de Pessoa
 * 
 * Representa um aluno inscrito no sistema académico do CESAE Digital.
 * 
 * @author Mário Amorim
 * @version 2.0
 */
@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Número de identificação único do aluno */
    @Column(unique = true)
    private int numeroAluno;

    /** Turma à qual o aluno pertence */
    @ManyToOne
    @JoinColumn(name = "turma_id")
    @JsonIgnoreProperties({ "alunos", "unidadesCurriculares", "curso" })
    private Turma turma;

    /** Lista de notas do aluno */
    @ElementCollection
    @CollectionTable(name = "aluno_notas", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "nota")
    private List<Double> notas = new ArrayList<>();

    /** Estado atual do aluno */
    @Enumerated(EnumType.STRING)
    private EstadoAluno estado = EstadoAluno.ATIVO;

    // ========== VARIÁVEL STATIC (CONTADOR) ==========
    private static int contadorAlunos = 1000;

    // ========== CONSTRUTORES ==========

    public Aluno() {
        this.numeroAluno = ++contadorAlunos;
    }

    public Aluno(String nome, String email, String telefone, int idade) {
        super(nome, email, telefone, idade);
        this.numeroAluno = ++contadorAlunos;
        this.notas = new ArrayList<>();
        this.estado = EstadoAluno.ATIVO;
    }

    public Aluno(String nome, String email, int idade) {
        this(nome, email, "", idade);
    }

    // ========== MÉTODOS GETTERS ==========

    public Long getId() {
        return id;
    }

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public EstadoAluno getEstado() {
        return estado;
    }

    // ========== MÉTODOS SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroAluno(int numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public void setEstado(EstadoAluno estado) {
        this.estado = estado;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========

    /**
     * Adiciona uma nota à lista de notas do aluno.
     * 
     * @param nota Nota a adicionar (0-20).
     * @return true se a nota foi adicionada com sucesso
     */
    public boolean adicionarNota(double nota) {
        if (nota >= 0 && nota <= 20) {
            notas.add(nota);
            return true;
        }
        return false;
    }

    /**
     * Calcula a média aritmética das notas do aluno.
     * 
     * @return A média das notas ou 0 se não houver notas.
     */
    public double calcularMedia() {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (Double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    @Override
    public String toString() {
        return "[" + numeroAluno + "] " + getNome() + " | Estado: " + estado +
                " | Média: " + String.format("%.2f", calcularMedia());
    }
}
