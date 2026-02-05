/**
 * Classe FuncionarioComissionado - Subclasse de Funcionario
 * 
 * Representa um funcionário que recebe salário base mais comissão sobre vendas.
 * Ideal para comerciais e vendedores.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class FuncionarioComissionado extends Funcionario {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Salário base mensal em euros */
    private double salarioBase;
    /** Total de vendas realizadas no mês em euros */
    private double vendasMes;
    /** Percentagem de comissão sobre as vendas (ex: 5 = 5%) */
    private double percentagemComissao;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do FuncionarioComissionado.
     * 
     * @param nome                Nome completo.
     * @param email               Endereço de email.
     * @param dataAdmissao        Data de admissão.
     * @param salarioBase         Salário base mensal.
     * @param vendasMes           Vendas do mês atual.
     * @param percentagemComissao Percentagem de comissão (0-100).
     */
    public FuncionarioComissionado(String nome, String email, String dataAdmissao,
                                    double salarioBase, double vendasMes, 
                                    double percentagemComissao) {
        super(nome, email, dataAdmissao);
        this.salarioBase = (salarioBase >= 0) ? salarioBase : 0;
        this.vendasMes = (vendasMes >= 0) ? vendasMes : 0;
        this.percentagemComissao = (percentagemComissao >= 0 && percentagemComissao <= 100) ? percentagemComissao : 0;
    }

    /**
     * Construtor simplificado (vendas iniciais a zero).
     * 
     * @param nome                Nome completo.
     * @param email               Endereço de email.
     * @param dataAdmissao        Data de admissão.
     * @param salarioBase         Salário base mensal.
     * @param percentagemComissao Percentagem de comissão.
     */
    public FuncionarioComissionado(String nome, String email, String dataAdmissao,
                                    double salarioBase, double percentagemComissao) {
        this(nome, email, dataAdmissao, salarioBase, 0, percentagemComissao);
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
     * Obtém as vendas do mês.
     * 
     * @return Valor das vendas em euros.
     */
    public double getVendasMes() {
        return vendasMes;
    }

    /**
     * Obtém a percentagem de comissão.
     * 
     * @return Percentagem (ex: 5.0 para 5%).
     */
    public double getPercentagemComissao() {
        return percentagemComissao;
    }

    /**
     * Calcula o valor da comissão.
     * 
     * @return Comissão em euros.
     */
    public double getValorComissao() {
        return vendasMes * percentagemComissao / 100;
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
     * Define as vendas do mês.
     * Valida se o valor não é negativo.
     * 
     * @param vendasMes Novo valor de vendas.
     */
    public void setVendasMes(double vendasMes) {
        if (vendasMes >= 0) {
            this.vendasMes = vendasMes;
        } else {
            System.out.println("Erro: Vendas não podem ser negativas.");
            this.vendasMes = 0;
        }
    }

    /**
     * Define a percentagem de comissão.
     * Valida se o valor está entre 0 e 100.
     * 
     * @param percentagemComissao Nova percentagem.
     */
    public void setPercentagemComissao(double percentagemComissao) {
        if (percentagemComissao >= 0 && percentagemComissao <= 100) {
            this.percentagemComissao = percentagemComissao;
        } else {
            System.out.println("Erro: Percentagem deve estar entre 0 e 100.");
            this.percentagemComissao = 0;
        }
    }

    // ========== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS ==========
    /**
     * Calcula o salário do funcionário comissionado.
     * 
     * Fórmula: salárioBase + (vendasMes × percentagemComissão / 100)
     * 
     * @return O salário total calculado.
     */
    @Override
    public double calcularSalario() {
        return salarioBase + getValorComissao();
    }

    /**
     * Retorna o tipo de funcionário.
     * 
     * @return "Comissionado".
     */
    @Override
    public String getTipo() {
        return "Comissionado";
    }

    /**
     * Mostra os detalhes completos do funcionário comissionado.
     */
    @Override
    public void mostrarDetalhes() {
        mostrarInfoBase();
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  DETALHES COMISSIONADO:");
        System.out.println("  Salario Base: " + String.format("%.2f EUR", salarioBase));
        System.out.println("  Vendas do Mes: " + String.format("%.2f EUR", vendasMes));
        System.out.println("  Percentagem Comissao: " + String.format("%.1f%%", percentagemComissao));
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  CÁLCULO DO SALÁRIO:");
        System.out.println("    Base: " + String.format("%.2f EUR", salarioBase));
        System.out.println("    + Comissao: " + String.format("%.2f EUR", vendasMes) + 
                          " x " + String.format("%.1f%%", percentagemComissao) + 
                          " = " + String.format("%.2f EUR", getValorComissao()));
        System.out.println("  SALARIO TOTAL: " + String.format("%.2f EUR", calcularSalario()));
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
