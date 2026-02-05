/**
 * Classe Carro - Subclasse de Veiculo
 * 
 * Implementa o método andar().
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Carro extends Veiculo {
    /**
     * Construtor da classe Carro.
     */
    public Carro() {
    }

    /**
     * Implementação do método andar para Carro.
     */
    @Override
    public void andar() {
        System.out.println("Carro a andar...");
    }
}
