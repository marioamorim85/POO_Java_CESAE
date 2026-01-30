package ex08;

public class ContaBancaria {
    //Atributos privados
    private String titular;
    private String numeroConta;
    private double saldo;
    private boolean ativa;

    //Construtor
    public ContaBancaria(String titular, String numeroConta) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
        this.ativa = true;
    }

    // Método que retorna o estado da conta com base no saldo
    public String estadoConta() {
        if (saldo > 0) {
            return "Conta Positiva";
        } else if (saldo == 0) {
            return "Conta a Zero";
        } else {
            return "Conta Negativa";
        }
    }

    // Método depositar na conta se ativa
    public void depositar(double valor) {
        if (ativa && valor > 0) {
            saldo += valor;
            System.out.println("Depósito de " + valor + "euros realizado com sucesso");
        } else {
            System.out.println("Acção impossível: A conta do titular " + titular + " não está ativa ou o valor é inválido.");
        }
    }

    // Getters
    public String getTitular() {
        return titular;
    }
    
    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

}
