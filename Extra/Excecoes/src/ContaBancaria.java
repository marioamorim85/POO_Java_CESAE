/**
 * Classe ContaBancaria - Sistema Bancário com Exceções
 *
 * Representa uma conta bancária com operações que lançam exceções
 * personalizadas para tratar situações de erro de forma elegante.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class ContaBancaria {

    // ========== ATRIBUTOS PRIVADOS ==========
    private String titular;
    private double saldo;
    private String numeroConta;
    private boolean ativa;
    private int totalOperacoes;

    /**
     * Construtor da classe ContaBancaria.
     *
     * @param titular      Nome do titular da conta.
     * @param saldoInicial Saldo inicial da conta (deve ser >= 0).
     * @param numeroConta  Número identificador da conta.
     * @throws ValorInvalidoException Se o saldo inicial for negativo.
     */
    public ContaBancaria(String titular, double saldoInicial, String numeroConta)
            throws ValorInvalidoException {

        // Validar que o saldo inicial não é negativo
        if (saldoInicial < 0) {
            throw new ValorInvalidoException(saldoInicial, "saldo inicial");
        }

        this.titular = titular;
        this.saldo = saldoInicial;
        this.numeroConta = numeroConta;
        this.ativa = true;  // Conta começa ativa por defeito
        this.totalOperacoes = 0;
    }

    // ========== MÉTODOS PRINCIPAIS ==========

    /**
     * Deposita dinheiro na conta.
     *
     * @param valor Valor a depositar (deve ser positivo).
     * @throws ValorInvalidoException Se o valor for <= 0.
     * @throws ContaInativaException Se a conta estiver inativa.
     */
    public void depositar(double valor) throws ValorInvalidoException, ContaInativaException {
        // Verificar se a conta está ativa
        if (!ativa) {
            throw new ContaInativaException(numeroConta, "depósito");
        }

        // Verificar se o valor é válido
        if (valor <= 0) {
            throw new ValorInvalidoException(valor, "depósito");
        }

        // Realizar o depósito
        this.saldo += valor;
        this.totalOperacoes++;
        System.out.println("Depósito de " + String.format("%.2f", valor) +
                          " euros realizado com sucesso.");
    }

    /**
     * Levanta dinheiro da conta.
     *
     * @param valor Valor a levantar (deve ser positivo e <= saldo).
     * @throws ValorInvalidoException Se o valor for <= 0.
     * @throws SaldoInsuficienteException Se o saldo for insuficiente.
     * @throws ContaInativaException Se a conta estiver inativa.
     */
    public void levantar(double valor)
            throws ValorInvalidoException, SaldoInsuficienteException, ContaInativaException {

        // Verificar se a conta está ativa
        if (!ativa) {
            throw new ContaInativaException(numeroConta, "levantamento");
        }

        // Verificar se o valor é válido
        if (valor <= 0) {
            throw new ValorInvalidoException(valor, "levantamento");
        }

        // Verificar se há saldo suficiente
        if (valor > saldo) {
            throw new SaldoInsuficienteException(saldo, valor);
        }

        // Realizar o levantamento
        this.saldo -= valor;
        this.totalOperacoes++;
        System.out.println("Levantamento de " + String.format("%.2f", valor) +
                          " euros realizado com sucesso.");
    }

    /**
     * Transfere dinheiro para outra conta.
     *
     * @param valor   Valor a transferir.
     * @param destino Conta de destino.
     * @throws ValorInvalidoException Se o valor for <= 0.
     * @throws SaldoInsuficienteException Se o saldo for insuficiente.
     * @throws ContaInativaException Se alguma das contas estiver inativa.
     */
    public void transferir(double valor, ContaBancaria destino)
            throws ValorInvalidoException, SaldoInsuficienteException, ContaInativaException {

        // Verificar se a conta de origem está ativa
        if (!this.ativa) {
            throw new ContaInativaException(this.numeroConta, "transferência (origem)");
        }

        // Verificar se a conta de destino está ativa
        if (!destino.ativa) {
            throw new ContaInativaException(destino.numeroConta, "transferência (destino)");
        }

        // Verificar se o valor é válido
        if (valor <= 0) {
            throw new ValorInvalidoException(valor, "transferência");
        }

        // Verificar se há saldo suficiente
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException(this.saldo, valor);
        }

        // Realizar a transferência
        this.saldo -= valor;
        destino.saldo += valor;
        this.totalOperacoes++;
        destino.totalOperacoes++;

        System.out.println("Transferência de " + String.format("%.2f", valor) +
                          " euros de " + this.titular + " para " + destino.titular +
                          " realizada com sucesso.");
    }

    /**
     * Consulta e exibe o saldo atual da conta.
     */
    public void consultarSaldo() {
        String estado = ativa ? "Ativa" : "Inativa";
        System.out.println("Conta: " + numeroConta + " - Titular: " + titular);
        System.out.println("Saldo: " + String.format("%.2f", saldo) + " euros - Estado: " + estado);
    }

    /**
     * Desativa a conta, impedindo operações futuras.
     */
    public void desativarConta() {
        if (ativa) {
            this.ativa = false;
            System.out.println("Conta " + numeroConta + " desativada com sucesso.");
        } else {
            System.out.println("A conta já se encontra inativa.");
        }
    }

    /**
     * Ativa a conta, permitindo operações.
     */
    public void ativarConta() {
        if (!ativa) {
            this.ativa = true;
            System.out.println("Conta " + numeroConta + " ativada com sucesso.");
        } else {
            System.out.println("A conta já se encontra ativa.");
        }
    }

    // ========== GETTERS ==========
    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public int getTotalOperacoes() {
        return totalOperacoes;
    }

    // ========== SETTERS ==========
    public void setTitular(String titular) {
        this.titular = titular;
    }

    // ========== TOSTRING ==========
    @Override
    public String toString() {
        String estado = ativa ? "Ativa" : "Inativa";
        return numeroConta + " - " + titular + " - Saldo: " +
               String.format("%.2f", saldo) + " euros - " + estado;
    }
}
