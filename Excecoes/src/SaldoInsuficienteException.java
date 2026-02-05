/**
 * Exceção personalizada para saldo insuficiente.
 *
 * Esta exceção é lançada quando se tenta realizar uma operação
 * (levantamento ou transferência) com um valor superior ao saldo disponível.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class SaldoInsuficienteException extends Exception {

    // Atributos para informação adicional sobre o erro
    private double saldoDisponivel;
    private double valorSolicitado;

    /**
     * Construtor com mensagem simples.
     *
     * @param mensagem Mensagem de erro.
     */
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor com informações detalhadas.
     *
     * @param saldoDisponivel O saldo atual da conta.
     * @param valorSolicitado O valor que se tentou levantar/transferir.
     */
    public SaldoInsuficienteException(double saldoDisponivel, double valorSolicitado) {
        super("Não é possível levantar " + String.format("%.2f", valorSolicitado) +
              " euros. Saldo disponível: " + String.format("%.2f", saldoDisponivel) + " euros");
        this.saldoDisponivel = saldoDisponivel;
        this.valorSolicitado = valorSolicitado;
    }

    // ========== GETTERS ==========
    /**
     * Obtém o saldo que estava disponível no momento do erro.
     * @return O saldo disponível em euros.
     */
    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    /**
     * Obtém o valor que foi solicitado.
     * @return O valor solicitado em euros.
     */
    public double getValorSolicitado() {
        return valorSolicitado;
    }

    /**
     * Calcula o valor em falta para completar a operação.
     * @return A diferença entre o valor solicitado e o saldo disponível.
     */
    public double getValorEmFalta() {
        return valorSolicitado - saldoDisponivel;
    }
}
