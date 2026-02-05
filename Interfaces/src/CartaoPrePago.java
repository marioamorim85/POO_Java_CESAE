/**
 * Classe CartaoPrePago - Implementa Pagavel, Transferivel e Recarregavel.
 *
 * Representa um cartão pré-pago que permite fazer pagamentos,
 * receber transferências e ser recarregado até um limite.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class CartaoPrePago implements Pagavel, Transferivel, Recarregavel {

    // ========== ATRIBUTOS PRIVADOS ==========
    private String numero;
    private String titular;
    private double saldo;
    private double limiteRecarga;

    /**
     * Construtor da classe CartaoPrePago.
     *
     * @param numero        Número do cartão.
     * @param titular       Nome do titular.
     * @param saldoInicial  Saldo inicial do cartão.
     * @param limiteRecarga Limite máximo de saldo permitido.
     */
    public CartaoPrePago(String numero, String titular, double saldoInicial, double limiteRecarga) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.limiteRecarga = limiteRecarga;
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE PAGAVEL ==========

    @Override
    public boolean processarPagamento(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: Valor de pagamento inválido.");
            return false;
        }

        if (valor > saldo) {
            System.out.println("ERRO: Saldo insuficiente no cartão pré-pago. Disponível: " +
                              String.format("%.2f", saldo) + " euros");
            return false;
        }

        this.saldo -= valor;
        System.out.println("Pagamento de " + String.format("%.2f", valor) +
                          " euros processado com " + getTipoPagamento() + ".");
        return true;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public String getTipoPagamento() {
        return "Cartão Pré-Pago";
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE TRANSFERIVEL ==========

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

        this.saldo -= valor;
        destino.receberTransferencia(valor);

        System.out.println("Transferência de " + String.format("%.2f", valor) +
                          " euros de " + this.getIdentificador() +
                          " para " + destino.getIdentificador() + " concluída.");
        return true;
    }

    @Override
    public void receberTransferencia(double valor) {
        // Verificar se não excede o limite
        if (saldo + valor > limiteRecarga) {
            double valorAceite = limiteRecarga - saldo;
            this.saldo = limiteRecarga;
            System.out.println("AVISO: Transferência parcialmente aceite. " +
                              "Recebido: " + String.format("%.2f", valorAceite) +
                              " euros (limite atingido).");
        } else {
            this.saldo += valor;
        }
    }

    @Override
    public String getIdentificador() {
        return "**** " + numero.substring(Math.max(0, numero.length() - 4));
    }

    // ========== IMPLEMENTAÇÃO DA INTERFACE RECARREGAVEL ==========

    @Override
    public boolean recarregar(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: Valor de recarga inválido.");
            return false;
        }

        if (estaNoLimite()) {
            System.out.println("ERRO: Cartão já está no limite máximo.");
            return false;
        }

        // Calcular taxa de recarga
        double taxa = calcularTaxaRecarga(valor);
        double valorLiquido = valor - taxa;

        // Verificar se excede o limite
        if (saldo + valorLiquido > limiteRecarga) {
            valorLiquido = limiteRecarga - saldo;
            System.out.println("AVISO: Recarga ajustada para não exceder o limite.");
        }

        this.saldo += valorLiquido;
        System.out.println("Recarga de " + String.format("%.2f", valor) +
                          " euros realizada (taxa: " + String.format("%.2f", taxa) +
                          " euros). Saldo atual: " + String.format("%.2f", saldo) + " euros");
        return true;
    }

    @Override
    public double getLimiteRecarga() {
        return limiteRecarga;
    }

    @Override
    public double getSaldoAtual() {
        return saldo;
    }

    // ========== MÉTODOS ESPECÍFICOS ==========

    /**
     * Obtém o número do cartão mascarado.
     *
     * @return Número no formato **** XXXX.
     */
    public String getNumeroMascarado() {
        if (numero.length() >= 4) {
            return "**** " + numero.substring(numero.length() - 4);
        }
        return "**** " + numero;
    }

    // ========== GETTERS ==========
    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    // ========== TOSTRING ==========
    @Override
    public String toString() {
        return getTipoPagamento() + ": " + getNumeroMascarado() +
               " - Titular: " + titular +
               " - Saldo: " + String.format("%.2f", saldo) + " euros" +
               " - Limite: " + String.format("%.2f", limiteRecarga) + " euros";
    }
}
