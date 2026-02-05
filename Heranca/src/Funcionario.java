/**
 * Classe Funcionario - Superclasse para o Sistema de Gestão de Funcionários
 * 
 * Representa um funcionário genérico com informações sobre nome, salário, 
 * departamento e horas de trabalho.
 * Serve como base para outras classes especializadas (Vendedor, Gerente).
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Funcionario {
    // Atributos privados
    private String nome;
    private double salario;
    private String departamento;
    private int horas_trabalho;

    /**
     * Construtor da classe Funcionario.
     * Inicializa o funcionário com nome, salário e departamento.
     * As horas de trabalho começam em 0.
     * 
     * @param nome          Nome do funcionário.
     * @param salario       Salário mensal em euros.
     * @param departamento  Departamento onde trabalha.
     */
    public Funcionario(String nome, double salario, String departamento) {
        this.nome = nome;
        this.salario = salario;
        this.departamento = departamento;
        this.horas_trabalho = 0;
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o nome do funcionário.
     * @return O nome do funcionário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o salário do funcionário.
     * @return O salário em euros.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Obtém o departamento do funcionário.
     * @return O departamento.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Obtém as horas de trabalho do funcionário.
     * @return O número de horas trabalhadas.
     */
    public int getHoras_trabalho() {
        return horas_trabalho;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o nome do funcionário.
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o salário do funcionário.
     * @param salario Novo salário em euros.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Define o departamento do funcionário.
     * @param departamento Novo departamento.
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Registar as horas trabalhadas pelo funcionário.
     * Adiciona as horas ao contador total.
     * 
     * @param horas Número de horas a adicionar.
     */
    public void trabalhar(int horas) {
        if (horas > 0) {
            this.horas_trabalho += horas;
            System.out.println(nome + " trabalhou " + horas + " horas.");
        }
    }

    /**
     * Calcula o bónus do funcionário.
     * Na classe base, o bónus é o salário + 100 euros.
     * Este método pode ser sobrescrito pelas subclasses.
     * 
     * @return O valor do bónus em euros.
     */
    public double bonus() {
        return salario + 100;
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual do funcionário.
     * 
     * @return String com as informações do funcionário no formato:
     *         "Nome: X - Departamento: Y - Salário: Z euros - Horas: W"
     */
    @Override
    public String toString() {
        return "Nome: " + nome + " - Departamento: " + departamento + " - Salário: " + salario + " euros - Horas: " + horas_trabalho;
    }
}
