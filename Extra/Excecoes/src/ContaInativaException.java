/**
 * Exceção personalizada para conta inativa.
 *
 * Esta exceção é lançada quando se tenta realizar uma operação
 * numa conta bancária que foi desativada.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class ContaInativaException extends Exception {

    // Atributo para guardar o número da conta
    private String numeroConta;

    /**
     * Construtor com mensagem simples.
     *
     * @param mensagem Mensagem de erro.
     */
    public ContaInativaException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor com número da conta.
     *
     * @param numeroConta O número da conta inativa.
     * @param operacao A operação que se tentou realizar.
     */
    public ContaInativaException(String numeroConta, String operacao) {
        super("Não é possível realizar " + operacao + ". A conta " + numeroConta + " está inativa.");
        this.numeroConta = numeroConta;
    }

    // ========== GETTERS ==========
    /**
     * Obtém o número da conta que causou a exceção.
     * @return O número da conta inativa.
     */
    public String getNumeroConta() {
        return numeroConta;
    }
}
