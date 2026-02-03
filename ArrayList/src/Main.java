import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe Principal - Gestão de Garagem de Carros
 * 
 * Demonstra o funcionamento da classe Carro usando uma ArrayList.
 * Inclui operações como adicionar, remover, consultar e ordenar carros.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // ========== 1. CRIAR A GARAGEM (ArrayList de Carros) ==========
        ArrayList<Carro> garagem = new ArrayList<>();

        // OPÇÃO ALTERNATIVA 1: Criar a lista já com carros (Java 9+)
        // ArrayList<Carro> garagem = new ArrayList<>(List.of(
        // new Carro("Toyota", "Corolla", 180),
        // new Carro("BMW", "320i", 200)
        // ));

        // OPÇÃO ALTERNATIVA 2: Adicionar vários de uma só vez mais tarde
        // garagem.addAll(List.of(
        // new Carro("Audi", "A4", 220),
        // new Carro("Mercedes", "C-Class", 240)
        // ));

        System.out.println("========== GESTÃO DE GARAGEM DE CARROS ==========\n");

        // ========== 2. ADICIONAR CARROS À GARAGEM ==========
        System.out.println("1. A ADICIONAR 5 CARROS À GARAGEM...\n");
        garagem.add(new Carro("Toyota", "Corolla", 180));
        System.out.println("Carro adicionado: " + garagem.get(0));
        imprimirGaragem(garagem);
        System.out.println();

        garagem.add(new Carro("BMW", "320i", 200));
        System.out.println("Carro adicionado: " + garagem.get(1));
        imprimirGaragem(garagem);
        System.out.println();

        garagem.add(new Carro("Audi", "A4", 220));
        System.out.println("Carro adicionado: " + garagem.get(2));
        imprimirGaragem(garagem);
        System.out.println();

        garagem.add(new Carro("Mercedes", "C-Class", 240));
        System.out.println("Carro adicionado: " + garagem.get(3));
        imprimirGaragem(garagem);
        System.out.println();

        garagem.add(new Carro("Volkswagen", "Golf", 190));
        System.out.println("Carro adicionado: " + garagem.get(4));
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 3. CONSULTAR INFORMAÇÕES ==========
        System.out.println("2. A CONSULTAR INFORMAÇÕES DA GARAGEM...\n");
        System.out.println("Número total de carros na garagem: " + garagem.size());
        System.out.println("Primeiro carro: " + garagem.get(0));
        System.out.println("Último carro: " + garagem.get(garagem.size() - 1));
        System.out.println("\nTodos os carros:");
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 4. ACEDER A UM CARRO ESPECÍFICO ==========
        System.out.println("3. A ACEDER A UM CARRO ESPECÍFICO...\n");
        System.out.println("Carro no índice 2: " + garagem.get(2));
        garagem.get(2).acelerar(50);
        System.out.println("Após acelerar 50 km/h: " + garagem.get(2));
        System.out.println();

        // ========== 5. MODIFICAR UM CARRO ==========
        System.out.println("4. A MODIFICAR UM CARRO...\n");
        System.out.println("Carro antigo no índice 0: " + garagem.get(0));
        garagem.set(0, new Carro("Ferrari", "F8", 340));
        System.out.println("Carro novo no índice 0: " + garagem.get(0));
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 6. REMOVER CARROS ==========
        System.out.println("5. A REMOVER CARROS...\n");
        System.out.println("Carro removido: " + garagem.get(0));
        garagem.remove(0);
        System.out.println("Garagem após remoção do primeiro carro:");
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 7. VERIFICAR EXISTÊNCIA ==========
        System.out.println("6. A VERIFICAR EXISTÊNCIA DE CARROS...\n");
        boolean existe = existeCarro(garagem, "BMW", "320i");
        System.out.println("Existe um BMW 320i na garagem? " + (existe ? "Sim" : "Não"));

        boolean naoExiste = existeCarro(garagem, "Porsche", "911");
        System.out.println("Existe um Porsche 911 na garagem? " + (naoExiste ? "Sim" : "Não"));
        System.out.println();

        // ========== 8. OPERAÇÕES ÚTEIS ==========
        System.out.println("7. OPERAÇÕES ÚTEIS...\n");
        System.out.println("Total de carros que restam: " + garagem.size());
        System.out.println("Garagem está vazia? " + (garagem.isEmpty() ? "Sim" : "Não"));
        System.out.println("Garagem atual:");
        imprimirGaragem(garagem);

        // Acelerar todos os carros em 10 km/h
        System.out.println("\nA acelerar todos os carros em 10 km/h...");
        for (Carro carro : garagem) {
            carro.acelerar(10);
        }
        System.out.println("Garagem após acelerar todos:");
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 9. ORDENAR CARROS (AVANÇADO) ==========
        System.out.println("8. A ORDENAR CARROS POR MARCA...\n");
        System.out.println("Garagem antes da ordenação:");
        imprimirGaragem(garagem);
        
        // Ordenar carros por marca (alfabeticamente)
        Collections.sort(garagem, (carro1, carro2) -> carro1.getMarca().compareTo(carro2.getMarca()));
        
        System.out.println("\nGaragem após ordenação por marca:");
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 10. ORDENAR CARROS POR VELOCIDADE MÁXIMA ==========
        System.out.println("9. A ORDENAR CARROS POR VELOCIDADE MÁXIMA...\n");
        System.out.println("Garagem antes da ordenação:");
        imprimirGaragem(garagem);

        // Ordenar carros por velocidade máxima
        // Collections.sort ordena a lista de acordo com a comparação definida no segundo argumento
        // A lambda expression define a comparação entre dois carros com base na velocidade máxima
        Collections.sort(garagem, (Carro1, Carro2) -> Integer.compare(Carro1.getVelocidadeMaxima(), Carro2.getVelocidadeMaxima()));

        System.out.println("\nGaragem após a ordenação por velocidade máxima:");
        imprimirGaragem(garagem);
        System.out.println();

        // ========== 11. LIMPAR GARAGEM ==========
        System.out.println("10. A LIMPAR A GARAGEM...\n");
        System.out.println("Total de carros antes da limpeza: " + garagem.size());
        garagem.clear();
        System.out.println("Total de carros depois da limpeza: " + garagem.size());
        System.out.println("Garagem está vazia? " + (garagem.isEmpty() ? "Sim" : "Não"));
    }

    /**
     * Método auxiliar que imprime todos os carros da garagem.
     * 
     * @param garagem ArrayList contendo os carros.
     */
    private static void imprimirGaragem(ArrayList<Carro> garagem) {
        if (garagem.isEmpty()) {
            System.out.println("   [Garagem vazia]");
            return;
        }
        /*
         * // Ciclo for tradicional com índice (o que fazia aparecer o [0], [1], etc)
         * for (int i = 0; i < garagem.size(); i++) {
         * System.out.println("   [" + i + "] " + garagem.get(i));
         * }
         */

        // Ciclo for-each (mais simples, não usa índice)
        for (Carro carro : garagem) {
            System.out.println("   " + carro);
        }
    }

    /**
     * Método que verifica se existe um carro específico na garagem.
     * Procura por marca e modelo.
     * 
     * @param garagem ArrayList contendo os carros.
     * @param marca   Marca do carro a procurar.
     * @param modelo  Modelo do carro a procurar.
     * @return true se o carro existe, false caso contrário.
     */
    private static boolean existeCarro(ArrayList<Carro> garagem, String marca, String modelo) {
        // Percorre todos os carros da garagem
        for (Carro carro : garagem) {
            // Se encontra um carro com a mesma marca e modelo, retorna true
            if (carro.getMarca().equals(marca) && carro.getModelo().equals(modelo)) {
                return true;
            }
        }
        // Se não encontrar, retorna false
        return false;
    }
}
