/**
 * Classe Bicicleta - Subclasse de Veiculo
 * 
 * Implementa o método andar().
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Bicicleta extends Veiculo {
    /**
     * Construtor da classe Bicicleta.
     */
    public Bicicleta() {
    }

    /**
     * Implementação do método andar para Bicicleta.
     */
    @Override
    public void andar() {
        System.out.println("Bicicleta a andar...");
    }
}
