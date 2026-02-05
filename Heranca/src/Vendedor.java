/**
 * Classe Vendedor - Subclasse de Funcionario
 * 
 * Representa um vendedor que herda de Funcionario e adiciona
 * funcionalidades específicas de vendas e comissões.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Vendedor extends Funcionario {
    // Atributo privado adicional
    private double vendas;

    /**
     * Construtor da classe Vendedor.
     * Inicializa o vendedor com nome, salário e departamento.
     * As vendas começam em 0.
     * Usa super() para inicializar a superclasse.
     * 
     * @param nome          Nome do vendedor.
     * @param salario       Salário mensal em euros.
     * @param departamento  Departamento onde trabalha.
     */
    public Vendedor(String nome, double salario, String departamento) {
        // Chama o construtor da superclasse
        super(nome, salario, departamento);
        this.vendas = 0;
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o valor total de vendas do vendedor.
     * @return O valor das vendas em euros.
     */
    public double getVendas() {
        return vendas;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Realiza o registo de uma venda e adiciona o valor ao total.
     * 
     * @param valor Valor da venda em euros.
     */
    public void registarVenda(double valor) {
        if (valor > 0) {
            this.vendas += valor;
            System.out.println(this.getNome() + " registou uma venda de " + valor + " euros.");
        }
    }

    /**
     * Calcula o bónus do vendedor.
     * Sobrescreve o método da superclasse.
     * Bónus = Salário + (Vendas * 0.05) - 5% de comissão nas vendas
     * 
     * @return O valor do bónus em euros.
     */
    @Override
    public double bonus() {
        double bonusVendas = (vendas * 0.05);
        return this.getSalario() + bonusVendas;
    }

    /**
     * Apresenta um relatório das vendas realizadas pelo vendedor.
     */
    public void relatorioVendas() {
        System.out.println("Vendedor " + this.getNome() + " realizou " + vendas + " euros em vendas este mês");
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual do vendedor.
     * Chama o toString() da superclasse.
     * 
     * @return String com as informações do vendedor.
     */
    @Override
    public String toString() {
        return super.toString() + " - Vendas: " + vendas + " euros";
    }
}
