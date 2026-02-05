/**
 * Interface Recarregavel - Define o contrato para métodos recarregáveis.
 *
 * Classes que implementam esta interface podem ser recarregadas
 * com dinheiro até um determinado limite.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public interface Recarregavel {

    // ========== CONSTANTE ==========
    /**
     * Taxa de recarga padrão (em percentagem).
     * Interfaces podem ter constantes (implicitamente public static final).
     */
    double TAXA_RECARGA_PADRAO = 0.01;  // 1%

    /**
     * Recarrega o método de pagamento com um valor.
     *
     * @param valor Valor a recarregar.
     * @return true se a recarga foi bem-sucedida, false caso contrário.
     */
    boolean recarregar(double valor);

    /**
     * Obtém o limite máximo de recarga permitido.
     *
     * @return O limite máximo em euros.
     */
    double getLimiteRecarga();

    /**
     * Obtém o saldo atual do método recarregável.
     *
     * @return O saldo atual em euros.
     */
    double getSaldoAtual();

    // ========== MÉTODOS DEFAULT ==========
    /**
     * Calcula quanto ainda pode ser recarregado.
     *
     * @return O valor disponível para recarga.
     */
    default double espacoParaRecarga() {
        return getLimiteRecarga() - getSaldoAtual();
    }

    /**
     * Verifica se está no limite máximo.
     *
     * @return true se não é possível recarregar mais.
     */
    default boolean estaNoLimite() {
        return getSaldoAtual() >= getLimiteRecarga();
    }

    /**
     * Calcula a taxa de recarga para um determinado valor.
     *
     * @param valor Valor da recarga.
     * @return O valor da taxa a pagar.
     */
    default double calcularTaxaRecarga(double valor) {
        return valor * TAXA_RECARGA_PADRAO;
    }
}
