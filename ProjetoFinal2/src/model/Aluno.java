package model;

import java.util.ArrayList;

/**
 * Classe Aluno - Subclasse de Pessoa
 * 
 * Representa um aluno inscrito no sistema académico do CESAE Digital.
 * Herda atributos de Pessoa e adiciona informações específicas de aluno.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Aluno extends Pessoa {
    // ========== VARIÁVEL STATIC (CONTADOR) ==========
    /** Contador estático para geração automática do número de aluno */
    private static int contadorAlunos = 1000;

    // ========== ATRIBUTOS PRIVADOS ==========
    /** Número de identificação único do aluno */
    private int numeroAluno;
    /** Turma à qual o aluno pertence (pode ser null) */
    private Turma turma;
    /** Lista de notas do aluno */
    private ArrayList<Double> notas;
    /** Estado atual do aluno */
    private EstadoAluno estado;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do Aluno.
     * O número de aluno é gerado automaticamente.
     * 
     * @param nome     Nome completo do aluno.
     * @param email    Endereço de email.
     * @param telefone Número de telefone.
     * @param idade    Idade do aluno.
     * @param turma    Turma à qual pertence.
     */
    public Aluno(String nome, String email, String telefone, int idade, Turma turma) {
        super(nome, email, telefone, idade);
        this.numeroAluno = ++contadorAlunos;
        this.turma = turma;
        this.notas = new ArrayList<>();
        this.estado = EstadoAluno.ATIVO;
    }

    /**
     * Construtor sem turma (sobrecarga).
     * Útil para criar aluno antes de o associar a uma turma.
     * 
     * @param nome     Nome completo do aluno.
     * @param email    Endereço de email.
     * @param telefone Número de telefone.
     * @param idade    Idade do aluno.
     */
    public Aluno(String nome, String email, String telefone, int idade) {
        this(nome, email, telefone, idade, null);
    }

    /**
     * Construtor simplificado.
     * 
     * @param nome  Nome completo do aluno.
     * @param email Endereço de email.
     * @param idade Idade do aluno.
     */
    public Aluno(String nome, String email, int idade) {
        this(nome, email, "", idade, null);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o número de aluno.
     * 
     * @return O número de identificação.
     */
    public int getNumeroAluno() {
        return numeroAluno;
    }

    /**
     * Obtém a turma do aluno.
     * 
     * @return A turma ou null se não estiver inscrito.
     */
    public Turma getTurma() {
        return turma;
    }

    /**
     * Obtém a lista de notas do aluno.
     * 
     * @return ArrayList com as notas.
     */
    public ArrayList<Double> getNotas() {
        return notas;
    }

    /**
     * Obtém o estado atual do aluno.
     * 
     * @return O estado (ATIVO, SUSPENSO, CONCLUIDO, DESISTENTE).
     */
    public EstadoAluno getEstado() {
        return estado;
    }

    /**
     * Obtém o contador atual de alunos (static).
     * 
     * @return O valor do contador.
     */
    public static int getContadorAlunos() {
        return contadorAlunos;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define a turma do aluno.
     * 
     * @param turma Nova turma.
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * Define o estado do aluno.
     * 
     * @param estado Novo estado.
     */
    public void setEstado(EstadoAluno estado) {
        this.estado = estado;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Adiciona uma nota à lista de notas do aluno.
     * Valida se a nota está entre 0 e 20.
     * 
     * @param nota Nota a adicionar (0-20).
     */
    public void adicionarNota(double nota) {
        if (nota >= 0 && nota <= 20) {
            notas.add(nota);
            System.out.println("Nota " + nota + " adicionada ao aluno " + getNome());
        } else {
            System.out.println("Erro: Nota deve estar entre 0 e 20.");
        }
    }

    /**
     * Calcula a média aritmética das notas do aluno.
     * 
     * @return A média das notas ou 0 se não houver notas.
     */
    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (Double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    /**
     * Implementação do método abstracto de Pessoa.
     * Apresenta os detalhes completos do aluno.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("========== DETALHES DO ALUNO ==========");
        System.out.println("Número: " + numeroAluno);
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Idade: " + getIdade() + " anos");
        System.out.println("Estado: " + estado);
        System.out.println("Turma: " + (turma != null ? turma.getNome() : "Não inscrito"));
        System.out.println("Número de notas: " + notas.size());
        System.out.println("Média: " + String.format("%.2f", calcularMedia()));
        System.out.println("========================================");
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual resumida do aluno.
     * 
     * @return String formatada com os dados principais.
     */
    @Override
    public String toString() {
        return "[" + numeroAluno + "] " + getNome() + " | Estado: " + estado +
                " | Média: " + String.format("%.2f", calcularMedia());
    }
}
