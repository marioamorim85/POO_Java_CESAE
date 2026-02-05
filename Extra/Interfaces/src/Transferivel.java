/**
 * Interface Transferivel - Define o contrato para transferências.
 *
 * Classes que implementam esta interface podem transferir
 * dinheiro para outros objetos Transferivel.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public interface Transferivel {

    /**
     * Transfere dinheiro para outro destino transferível.
     *
     * @param valor   Valor a transferir.
     * @param destino Objeto destino que também implementa Transferivel.
     * @return true se a transferência foi bem-sucedida, false caso contrário.
     */
    boolean transferir(double valor, Transferivel destino);

    /**
     * Recebe uma transferência de outro objeto.
     * Este método é chamado pelo objeto que está a enviar.
     *
     * @param valor Valor a receber.
     */
    void receberTransferencia(double valor);

    /**
     * Obtém o identificador único do objeto transferível.
     *
     * @return String com o identificador (ex: IBAN, número do cartão).
     */
    String getIdentificador();

    // ========== MÉTODO DEFAULT ==========
    /**
     * Verifica se pode receber transferências.
     * Por defeito, retorna true.
     *
     * @return true se pode receber, false caso contrário.
     */
    default boolean podeReceberTransferencia() {
        return true;
    }
}
