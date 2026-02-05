/**
 * Classe ContaBancaria - Implementa Pagavel e Transferivel.
 *
 * Representa uma conta bancária que permite fazer pagamentos
 * e transferências para outras contas/cartões.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class ContaBancaria implements Pagavel, Transferivel {

    // ========== ATRIBUTOS PRIVADOS ==========
    private String iban;
    private String titular;
    private double saldo;

    /**
     * Construtor da classe ContaBancaria.
     *
     * @param iban    IBAN da conta.
     * @param titular Nome do titular.
     * @param saldo   Saldo inicial.
     */
    public ContaBancaria(String iban, String titular, double saldo) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = saldo;
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE PAGAVEL ==========

    /**
     * Processa um pagamento debitando da conta.
     *
     * @param valor Valor do pagamento.
     * @return true se o pagamento foi processado, false caso contrário.
     */
    @Override
    public boolean processarPagamento(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: Valor de pagamento inválido.");
            return false;
        }

        if (valor > saldo) {
            System.out.println("ERRO: Saldo insuficiente. Disponível: " +
                              String.format("%.2f", saldo) + " euros");
            return false;
        }

        this.saldo -= valor;
        System.out.println("Pagamento de " + String.format("%.2f", valor) +
                          " euros processado com " + getTipoPagamento() + ".");
        return true;
    }

    /**
     * Consulta o saldo da conta.
     *
     * @return O saldo atual em euros.
     */
    @Override
    public double consultarSaldo() {
        return saldo;
    }

    /**
     * Retorna o tipo de pagamento.
     *
     * @return "Conta Bancária".
     */
    @Override
    public String getTipoPagamento() {
        return "Conta Bancária";
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE TRANSFERIVEL ==========

    /**
     * Transfere dinheiro para outro destino transferível.
     *
     * @param valor   Valor a transferir.
     * @param destino Destino da transferência.
     * @return true se a transferência foi bem-sucedida.
     */
    @Override
    public boolean transferir(double valor, Transferivel destino) {
        if (valor <= 0) {
            System.out.println("ERRO: Valor de transferência inválido.");
            return false;
        }

        if (valor > saldo) {
            System.out.println("ERRO: Saldo insuficiente para transferência.");
            return false;
        }

        if (!destino.podeReceberTransferencia()) {
            System.out.println("ERRO: O destino não pode receber transferências.");
            return false;
        }

        // Realizar a transferência
        this.saldo -= valor;
        destino.receberTransferencia(valor);

        System.out.println("Transferência de " + String.format("%.2f", valor) +
                          " euros de " + this.getIdentificador() +
                          " para " + destino.getIdentificador() + " concluída.");
        return true;
    }

    /**
     * Recebe uma transferência de outro objeto.
     *
     * @param valor Valor recebido.
     */
    @Override
    public void receberTransferencia(double valor) {
        this.saldo += valor;
    }

    /**
     * Retorna o identificador da conta (IBAN).
     *
     * @return O IBAN da conta.
     */
    @Override
    public String getIdentificador() {
        return iban;
    }

    // ========== MÉTODOS ESPECÍFICOS ==========

    /**
     * Deposita dinheiro na conta.
     *
     * @param valor Valor a depositar.
     */
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de " + String.format("%.2f", valor) +
                              " euros realizado. Novo saldo: " + String.format("%.2f", saldo) + " euros");
        }
    }

    // ========== GETTERS ==========
    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // ========== TOSTRING ==========
    @Override
    public String toString() {
        return getTipoPagamento() + ": " + iban +
               " - Titular: " + titular +
               " - Saldo: " + String.format("%.2f", saldo) + " euros";
    }
}
