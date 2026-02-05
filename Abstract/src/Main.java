import java.util.ArrayList;

/**
 * Classe Principal - Exercício de Classes Abstractas
 * 
 * Demonstra polimorfismo com uma ArrayList de Veiculo.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Criar ArrayList de Veiculo
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        // Adicionar 1 Carro e 1 Bicicleta
        veiculos.add(new Carro());
        veiculos.add(new Bicicleta());

        // Percorrer com for-each e chamar andar()
        for (Veiculo v : veiculos) {
            v.andar();
        }
    }
}
