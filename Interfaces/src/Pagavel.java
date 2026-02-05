/**
 * Interface Pagavel - Define o contrato para métodos de pagamento.
 *
 * Qualquer classe que implemente esta interface deve fornecer
 * funcionalidades básicas de processamento de pagamentos.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public interface Pagavel {

    /**
     * Processa um pagamento.
     *
     * @param valor Valor do pagamento a processar.
     * @return true se o pagamento foi bem-sucedido, false caso contrário.
     */
    boolean processarPagamento(double valor);

    /**
     * Consulta o saldo ou limite disponível.
     *
     * @return O saldo/limite disponível em euros.
     */
    double consultarSaldo();

    /**
     * Obtém o tipo de método de pagamento.
     *
     * @return String identificando o tipo (ex: "Cartão de Crédito", "Conta Bancária").
     */
    String getTipoPagamento();

    // ========== MÉTODO DEFAULT (Java 8+) ==========
    /**
     * Verifica se é possível realizar um pagamento de determinado valor.
     * Método default - já tem implementação, mas pode ser sobrescrito.
     *
     * @param valor Valor a verificar.
     * @return true se o pagamento é possível, false caso contrário.
     */
    default boolean podePagar(double valor) {
        return valor > 0 && valor <= consultarSaldo();
    }

    /**
     * Exibe informação resumida do método de pagamento.
     * Método default com implementação padrão.
     */
    default void mostrarResumo() {
        System.out.println("Tipo: " + getTipoPagamento() +
                          " - Saldo: " + String.format("%.2f", consultarSaldo()) + "€");
    }
}
