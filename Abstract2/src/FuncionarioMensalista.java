/**
 * Classe FuncionarioMensalista - Subclasse de Funcionario
 * 
 * Representa um funcionário com salário fixo mensal.
 * O salário é composto pelo salário base mais subsídio de alimentação.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class FuncionarioMensalista extends Funcionario {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Salário base mensal em euros */
    private double salarioBase;
    /** Subsídio de alimentação mensal em euros */
    private double subsidioAlimentacao;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do FuncionarioMensalista.
     * 
     * @param nome               Nome completo.
     * @param email              Endereço de email.
     * @param dataAdmissao       Data de admissão.
     * @param salarioBase        Salário base mensal.
     * @param subsidioAlimentacao Subsídio de alimentação.
     */
    public FuncionarioMensalista(String nome, String email, String dataAdmissao,
                                  double salarioBase, double subsidioAlimentacao) {
        super(nome, email, dataAdmissao);
        this.salarioBase = (salarioBase >= 0) ? salarioBase : 0;
        this.subsidioAlimentacao = (subsidioAlimentacao >= 0) ? subsidioAlimentacao : 0;
    }

    /**
     * Construtor simplificado (sem subsídio de alimentação).
     * 
     * @param nome         Nome completo.
     * @param email        Endereço de email.
     * @param dataAdmissao Data de admissão.
     * @param salarioBase  Salário base mensal.
     */
    public FuncionarioMensalista(String nome, String email, String dataAdmissao,
                                  double salarioBase) {
        this(nome, email, dataAdmissao, salarioBase, 0);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o salário base.
     * 
     * @return Salário base em euros.
     */
    public double getSalarioBase() {
        return salarioBase;
    }

    /**
     * Obtém o subsídio de alimentação.
     * 
     * @return Subsídio em euros.
     */
    public double getSubsidioAlimentacao() {
        return subsidioAlimentacao;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o salário base.
     * Valida se o valor não é negativo.
     * 
     * @param salarioBase Novo salário base.
     */
    public void setSalarioBase(double salarioBase) {
        if (salarioBase >= 0) {
            this.salarioBase = salarioBase;
        } else {
            System.out.println("Erro: Salário base não pode ser negativo.");
            this.salarioBase = 0;
        }
    }

    /**
     * Define o subsídio de alimentação.
     * Valida se o valor não é negativo.
     * 
     * @param subsidioAlimentacao Novo subsídio.
     */
    public void setSubsidioAlimentacao(double subsidioAlimentacao) {
        if (subsidioAlimentacao >= 0) {
            this.subsidioAlimentacao = subsidioAlimentacao;
        } else {
            System.out.println("Erro: Subsídio não pode ser negativo.");
            this.subsidioAlimentacao = 0;
        }
    }

    // ========== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS ==========
    /**
     * Calcula o salário do funcionário mensalista.
     * 
     * Fórmula: salárioBase + subsídioAlimentação
     * 
     * @return O salário total calculado.
     */
    @Override
    public double calcularSalario() {
        return salarioBase + subsidioAlimentacao;
    }

    /**
     * Retorna o tipo de funcionário.
     * 
     * @return "Mensalista".
     */
    @Override
    public String getTipo() {
        return "Mensalista";
    }

    /**
     * Mostra os detalhes completos do funcionário mensalista.
     */
    @Override
    public void mostrarDetalhes() {
        mostrarInfoBase();
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  DETALHES MENSALISTA:");
        System.out.println("  Salario Base: " + String.format("%.2f EUR", salarioBase));
        System.out.println("  Subsidio Alimentacao: " + String.format("%.2f EUR", subsidioAlimentacao));
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  CÁLCULO DO SALÁRIO:");
        System.out.println("    Base: " + String.format("%.2f EUR", salarioBase));
        System.out.println("    + Subsidio: " + String.format("%.2f EUR", subsidioAlimentacao));
        System.out.println("  SALARIO TOTAL: " + String.format("%.2f EUR", calcularSalario()));
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
