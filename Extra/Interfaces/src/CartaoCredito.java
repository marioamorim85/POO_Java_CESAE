/**
 * Classe CartaoCredito - Implementa apenas a interface Pagavel.
 *
 * Representa um cartão de crédito que permite fazer pagamentos
 * até ao limite de crédito disponível.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class CartaoCredito implements Pagavel {

    // ========== ATRIBUTOS PRIVADOS ==========
    private String numero;
    private String titular;
    private double limiteCredito;
    private double saldoUtilizado;  // Quanto já foi gasto

    /**
     * Construtor da classe CartaoCredito.
     *
     * @param numero        Número do cartão (últimos 4 dígitos visíveis).
     * @param titular       Nome do titular.
     * @param limiteCredito Limite de crédito em euros.
     */
    public CartaoCredito(String numero, String titular, double limiteCredito) {
        this.numero = numero;
        this.titular = titular;
        this.limiteCredito = limiteCredito;
        this.saldoUtilizado = 0;
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE PAGAVEL ==========

    /**
     * Processa um pagamento usando o cartão de crédito.
     * O valor é descontado do limite disponível.
     *
     * @param valor Valor do pagamento.
     * @return true se o pagamento foi processado, false caso contrário.
     */
    @Override
    public boolean processarPagamento(double valor) {
        // Verificar se o valor é válido
        if (valor <= 0) {
            System.out.println("ERRO: Valor de pagamento inválido.");
            return false;
        }

        // Verificar se há limite disponível
        double limiteDisponivel = limiteCredito - saldoUtilizado;
        if (valor > limiteDisponivel) {
            System.out.println("ERRO: Limite de crédito insuficiente. Disponível: " +
                              String.format("%.2f", limiteDisponivel) + " euros");
            return false;
        }

        // Processar o pagamento
        this.saldoUtilizado += valor;
        System.out.println("Pagamento de " + String.format("%.2f", valor) +
                          " euros processado com " + getTipoPagamento() + ".");
        return true;
    }

    /**
     * Consulta o limite de crédito disponível.
     *
     * @return O limite disponível (limite total - saldo utilizado).
     */
    @Override
    public double consultarSaldo() {
        return limiteCredito - saldoUtilizado;
    }

    /**
     * Retorna o tipo de pagamento.
     *
     * @return "Cartão de Crédito".
     */
    @Override
    public String getTipoPagamento() {
        return "Cartão de Crédito";
    }

    // ========== MÉTODOS ESPECÍFICOS ==========

    /**
     * Paga a fatura do cartão (total ou parcial).
     *
     * @param valor Valor a pagar da fatura.
     */
    public void pagarFatura(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: Valor inválido.");
            return;
        }

        if (valor > saldoUtilizado) {
            valor = saldoUtilizado;  // Não pagar mais do que o devido
        }

        this.saldoUtilizado -= valor;
        System.out.println("Pagamento de fatura: " + String.format("%.2f", valor) +
                          " euros. Saldo em dívida: " + String.format("%.2f", saldoUtilizado) + " euros");
    }

    /**
     * Obtém o número do cartão mascarado.
     *
     * @return Número no formato **** **** **** XXXX.
     */
    public String getNumeroMascarado() {
        // Mostrar apenas os últimos 4 dígitos
        if (numero.length() >= 4) {
            return "**** **** **** " + numero.substring(numero.length() - 4);
        }
        return "**** **** **** " + numero;
    }

    // ========== GETTERS ==========
    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    // ========== TOSTRING ==========
    @Override
    public String toString() {
        return getTipoPagamento() + ": " + getNumeroMascarado() +
               " - Titular: " + titular +
               " - Limite: " + String.format("%.2f", limiteCredito) + " euros" +
               " - Disponível: " + String.format("%.2f", consultarSaldo()) + " euros";
    }
}
