/**
 * Classe Gerente - Subclasse de Funcionario
 * 
 * Representa um gerente que herda de Funcionario e adiciona
 * funcionalidades específicas de gestão de equipas.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Gerente extends Funcionario {
    // Atributo privado adicional
    private int equipa;

    /**
     * Construtor da classe Gerente.
     * Inicializa o gerente com nome, salário, departamento e tamanho da equipa.
     * Usa super() para inicializar a superclasse.
     * 
     * @param nome          Nome do gerente.
     * @param salario       Salário mensal em euros.
     * @param departamento  Departamento onde trabalha.
     * @param equipa        Número de pessoas na equipa.
     */
    public Gerente(String nome, double salario, String departamento, int equipa) {
        // Chama o construtor da superclasse
        super(nome, salario, departamento);
        this.equipa = equipa;
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o número de pessoas na equipa.
     * @return O número de pessoas.
     */
    public int getEquipa() {
        return equipa;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Adiciona uma pessoa à equipa do gerente.
     * Aumenta o tamanho da equipa em 1.
     */
    public void adicionarAEquipa() {
        this.equipa += 1;
        System.out.println("Uma nova pessoa foi adicionada à equipa de " + this.getNome() + ".");
    }

    /**
     * Calcula o bónus do gerente.
     * Sobrescreve o método da superclasse.
     * Bónus = Salário + (Equipa * 50) - 50 euros por pessoa da equipa
     * 
     * @return O valor do bónus em euros.
     */
    @Override
    public double bonus() {
        double bonusEquipa = (equipa * 50);
        return this.getSalario() + bonusEquipa;
    }

    /**
     * Apresenta um relatório sobre a equipa do gerente.
     */
    public void relatorioEquipa() {
        System.out.println("Gerente " + this.getNome() + " lidera " + equipa + " pessoas");
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual do gerente.
     * Chama o toString() da superclasse.
     * 
     * @return String com as informações do gerente.
     */
    @Override
    public String toString() {
        return super.toString() + " - Equipa: " + equipa + " pessoas";
    }
}
