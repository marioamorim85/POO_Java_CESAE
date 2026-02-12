package model;

import java.util.ArrayList;

/**
 * Classe Professor - Subclasse de Pessoa
 * 
 * Representa um professor do CESAE Digital.
 * Herda atributos de Pessoa e adiciona informações específicas de professor.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Professor extends Pessoa {
    // ========== CONSTANTES ==========
    /** Número máximo de UCs que um professor pode lecionar */
    private static final int MAX_UCS = 5;

    // ========== VARIÁVEL STATIC (CONTADOR) ==========
    /** Contador estático para geração automática do número de professor */
    private static int contadorProfessores = 100;

    // ========== ATRIBUTOS PRIVADOS ==========
    /** Número de identificação único do professor */
    private int numeroProfessor;
    /** Área de especialização */
    private String especialidade;
    /** Salário mensal em euros */
    private double salario;
    /** Lista de Unidades Curriculares que leciona */
    private ArrayList<UnidadeCurricular> unidadesCurriculares;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do Professor.
     * O número de professor é gerado automaticamente.
     * 
     * @param nome          Nome completo do professor.
     * @param email         Endereço de email.
     * @param telefone      Número de telefone.
     * @param idade         Idade do professor.
     * @param especialidade Área de especialização.
     * @param salario       Salário mensal em euros.
     */
    public Professor(String nome, String email, String telefone, int idade,
            String especialidade, double salario) {
        super(nome, email, telefone, idade);
        this.numeroProfessor = ++contadorProfessores;
        this.especialidade = especialidade;
        if (salario >= 0) {
            this.salario = salario;
        } else {
            System.out.println("Erro: Salário não pode ser negativo.");
            this.salario = 0;
        }
        this.unidadesCurriculares = new ArrayList<>();
    }

    /**
     * Construtor simplificado do Professor.
     * 
     * @param nome          Nome completo do professor.
     * @param email         Endereço de email.
     * @param idade         Idade do professor.
     * @param especialidade Área de especialização.
     */
    public Professor(String nome, String email, int idade, String especialidade) {
        this(nome, email, "", idade, especialidade, 0);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o número de professor.
     * 
     * @return O número de identificação.
     */
    public int getNumeroProfessor() {
        return numeroProfessor;
    }

    /**
     * Obtém a especialidade do professor.
     * 
     * @return A área de especialização.
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Obtém o salário do professor.
     * 
     * @return O salário mensal em euros.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Obtém a lista de UCs que leciona.
     * 
     * @return ArrayList de UnidadesCurriculares.
     */
    public ArrayList<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    /**
     * Obtém o contador atual de professores (static).
     * 
     * @return O valor do contador.
     */
    public static int getContadorProfessores() {
        return contadorProfessores;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define a especialidade do professor.
     * 
     * @param especialidade Nova especialidade.
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * Define o salário do professor.
     * Valida se o salário é >= 0.
     * 
     * @param salario Novo salário.
     */
    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        } else {
            System.out.println("Erro: Salário não pode ser negativo.");
            this.salario = 0;
        }
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Adiciona uma Unidade Curricular ao professor.
     * Valida o limite máximo de 5 UCs.
     * 
     * @param uc Unidade Curricular a adicionar.
     * @return true se adicionou com sucesso, false caso contrário.
     */
    public boolean adicionarUC(UnidadeCurricular uc) {
        if (uc == null) {
            System.out.println("Erro: UC inválida.");
            return false;
        }
        if (unidadesCurriculares.size() >= MAX_UCS) {
            System.out.println("Erro: Professor " + getNome() + " já leciona o máximo de " + MAX_UCS + " UCs.");
            return false;
        }
        if (unidadesCurriculares.contains(uc)) {
            System.out.println("Erro: UC já associada a este professor.");
            return false;
        }
        unidadesCurriculares.add(uc);
        System.out.println("UC '" + uc.getNome() + "' adicionada ao professor " + getNome());
        return true;
    }

    /**
     * Remove uma Unidade Curricular do professor.
     * 
     * @param uc Unidade Curricular a remover.
     * @return true se removeu com sucesso, false caso contrário.
     */
    public boolean removerUC(UnidadeCurricular uc) {
        if (unidadesCurriculares.remove(uc)) {
            System.out.println("UC '" + uc.getNome() + "' removida do professor " + getNome());
            return true;
        } else {
            System.out.println("Erro: UC não encontrada.");
            return false;
        }
    }

    /**
     * Lista todas as Unidades Curriculares que o professor leciona.
     */
    public void listarUCs() {
        System.out.println("=== UCs do Professor " + getNome() + " ===");
        if (unidadesCurriculares.isEmpty()) {
            System.out.println("Nenhuma UC atribuída.");
        } else {
            for (int i = 0; i < unidadesCurriculares.size(); i++) {
                System.out.println((i + 1) + ". " + unidadesCurriculares.get(i));
            }
        }
    }

    /**
     * Obtém o número de UCs que leciona.
     * 
     * @return Número de UCs.
     */
    public int getNumeroUCs() {
        return unidadesCurriculares.size();
    }

    /**
     * Implementação do método abstracto de Pessoa.
     * Apresenta os detalhes completos do professor.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("========== DETALHES DO PROFESSOR ==========");
        System.out.println("Número: " + numeroProfessor);
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Idade: " + getIdade() + " anos");
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Salário: " + String.format("%.2f", salario) + " €");
        System.out.println("Número de UCs: " + unidadesCurriculares.size() + "/" + MAX_UCS);
        System.out.println("============================================");
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual resumida do professor.
     * 
     * @return String formatada com os dados principais.
     */
    @Override
    public String toString() {
        return "[" + numeroProfessor + "] " + getNome() + " | Especialidade: " + especialidade +
                " | UCs: " + unidadesCurriculares.size();
    }
}
