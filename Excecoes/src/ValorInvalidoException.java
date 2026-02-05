/**
 * Exceção personalizada para valores inválidos.
 *
 * Esta exceção é lançada quando se introduz um valor negativo ou zero
 * numa operação que requer valores positivos (depósito, levantamento, etc.).
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class ValorInvalidoException extends Exception {

    // Atributo para guardar o valor inválido
    private double valorInvalido;

    /**
     * Construtor com mensagem simples.
     *
     * @param mensagem Mensagem de erro.
     */
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor com o valor inválido.
     *
     * @param valorInvalido O valor que causou o erro.
     */
    public ValorInvalidoException(double valorInvalido) {
        super("O valor deve ser positivo. Valor introduzido: " +
              String.format("%.2f", valorInvalido) + " euros");
        this.valorInvalido = valorInvalido;
    }

    /**
     * Construtor com valor e operação.
     *
     * @param valorInvalido O valor que causou o erro.
     * @param operacao O nome da operação que falhou.
     */
    public ValorInvalidoException(double valorInvalido, String operacao) {
        super("Valor inválido para " + operacao + ": " +
              String.format("%.2f", valorInvalido) + " euros. O valor deve ser positivo.");
        this.valorInvalido = valorInvalido;
    }

    // ========== GETTERS ==========
    /**
     * Obtém o valor que causou a exceção.
     * @return O valor inválido.
     */
    public double getValorInvalido() {
        return valorInvalido;
    }
}
