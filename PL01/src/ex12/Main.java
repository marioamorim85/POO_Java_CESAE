package ex12;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA DE JOGOS ===\n");

        // 1. Criar três objetos da classe Jogo com jogos diferentes
        System.out.println("--- Criação dos jogos ---");
        Jogo jogo1 = new Jogo("The Witcher 3", "PC", 29.99);
        Jogo jogo2 = new Jogo("God of War", "PlayStation", 49.99);
        Jogo jogo3 = new Jogo("Halo Infinite", "Xbox", 59.99);
        System.out.println("Jogos criados: " + jogo1.getTitulo() + ", " + jogo2.getTitulo() + ", " + jogo3.getTitulo());

        // 2. Simular sessões de jogo (invocar o método jogar várias vezes)
        System.out.println("\n=== SESSÕES DE JOGO ===\n");

        // Sessões do jogo 1
        System.out.println("--- " + jogo1.getTitulo() + " ---");
        jogo1.jogar(5.5);
        jogo1.jogar(10.0);
        jogo1.jogar(8.5);
        jogo1.jogar(15.0);

        // Sessões do jogo 2
        System.out.println("\n--- " + jogo2.getTitulo() + " ---");
        jogo2.jogar(3.0);
        jogo2.jogar(7.5);
        jogo2.jogar(4.5);

        // Sessões do jogo 3
        System.out.println("\n--- " + jogo3.getTitulo() + " ---");
        jogo3.jogar(2.0);
        jogo3.jogar(6.0);
        jogo3.jogar(3.5);
        jogo3.jogar(8.5);
        jogo3.jogar(5.0);

        // 3. Avaliar cada jogo com classificações diferentes
        System.out.println("\n=== AVALIAÇÕES ===\n");
        jogo1.avaliar(9);
        jogo2.avaliar(10);
        jogo3.avaliar(7);

        // 4. Mostrar os detalhes de todos os jogos
        System.out.println("\n=== DETALHES DE TODOS OS JOGOS ===\n");
        jogo1.mostrarDetalhes();
        System.out.println();
        jogo2.mostrarDetalhes();
        System.out.println();
        jogo3.mostrarDetalhes();

        // 5. Identificar qual teve o melhor custo-benefício (menor valor por hora)
        System.out.println("\n=== ANÁLISE DE CUSTO-BENEFÍCIO ===\n");

        // Obter valores por hora de cada jogo
        double valorPorHora1 = jogo1.obterValorPorHoraNumerico();
        double valorPorHora2 = jogo2.obterValorPorHoraNumerico();
        double valorPorHora3 = jogo3.obterValorPorHoraNumerico();

        // Determinar qual é o melhor custo-benefício
        Jogo melhorCustoBeneficio = null;
        double menorValorPorHora = Double.MAX_VALUE;

        if (valorPorHora1 > 0 && valorPorHora1 < menorValorPorHora) {
            menorValorPorHora = valorPorHora1;
            melhorCustoBeneficio = jogo1;
        }
        if (valorPorHora2 > 0 && valorPorHora2 < menorValorPorHora) {
            menorValorPorHora = valorPorHora2;
            melhorCustoBeneficio = jogo2;
        }
        if (valorPorHora3 > 0 && valorPorHora3 < menorValorPorHora) {
            menorValorPorHora = valorPorHora3;
            melhorCustoBeneficio = jogo3;
        }

        // Exibir resultado
        if (melhorCustoBeneficio != null) {
            System.out.println("🏆 MELHOR CUSTO-BENEFÍCIO:");
            System.out.println("   Jogo: " + melhorCustoBeneficio.getTitulo());
            System.out.println("   Plataforma: " + melhorCustoBeneficio.getPlataforma());
            System.out.println("   Valor por hora: " + String.format("%.2f", menorValorPorHora) + "€/hora");
            System.out.println("   (Menor custo por hora de jogo!)");
        } else {
            System.out.println("Nenhum jogo foi jogado ainda.");
        }

        // Resumo comparativo
        System.out.println("\n=== RESUMO COMPARATIVO ===");
        System.out.println("1. " + jogo1.getTitulo() + " (" + jogo1.getPlataforma() + "):");
        System.out.println("   → " + String.format("%.1f", jogo1.getHorasJogadas()) + "h jogadas | " + jogo1.calcularValorPorHora() + " | Nota: " + jogo1.getClassificacao() + "/10");
        
        System.out.println("\n2. " + jogo2.getTitulo() + " (" + jogo2.getPlataforma() + "):");
        System.out.println("   → " + String.format("%.1f", jogo2.getHorasJogadas()) + "h jogadas | " + jogo2.calcularValorPorHora() + " | Nota: " + jogo2.getClassificacao() + "/10");
        
        System.out.println("\n3. " + jogo3.getTitulo() + " (" + jogo3.getPlataforma() + "):");
        System.out.println("   → " + String.format("%.1f", jogo3.getHorasJogadas()) + "h jogadas | " + jogo3.calcularValorPorHora() + " | Nota: " + jogo3.getClassificacao() + "/10");
    }
}
