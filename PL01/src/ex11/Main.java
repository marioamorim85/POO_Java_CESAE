 package ex11;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== GESTÃO DE CARTEIRAS DIGITAIS ===\n");

        // 1. Criar um objeto da classe CarteiraDigital
        CarteiraDigital carteira1 = new CarteiraDigital("João Silva", 0, "EUR", true);

        // 2. Adicionar 500€ e consultar o saldo
        System.out.println("--- Teste 1: Adicionar 500€ ---");
        carteira1.adicionarSaldo(500);
        carteira1.consultarSaldo();

        // 3. Gastar 75€ e exibir o saldo
        System.out.println("\n--- Teste 2: Gastar 75€ ---");
        carteira1.gastar(75);
        carteira1.consultarSaldo();

        // 4. Criar mais dois objetos da classe CarteiraDigital
        System.out.println("\n--- Criação de mais duas carteiras ---");
        CarteiraDigital carteira2 = new CarteiraDigital("Maria Santos", 300, "EUR", true);
        CarteiraDigital carteira3 = new CarteiraDigital("Pedro Costa", 150, "EUR", true);
        
        System.out.println("Carteiras criadas:");
        carteira2.consultarSaldo();
        carteira3.consultarSaldo();

        // 5. Enviar 100€ de uma carteira para a outra
        System.out.println("\n--- Teste 3: Enviar 100€ de Maria para Pedro ---");
        carteira2.enviarDinheiro(100, carteira3);

        // 6. Exibir o saldo atual das três carteiras
        System.out.println("\n--- Saldo final de todas as carteiras ---");
        carteira1.consultarSaldo();
        carteira2.consultarSaldo();
        carteira3.consultarSaldo();

        // Testes adicionais para validar as regras de negócio
        System.out.println("\n=== TESTES ADICIONAIS ===\n");

        // Teste: Tentar enviar mais dinheiro do que tem disponível
        System.out.println("--- Teste 4: Tentar enviar 500€ (saldo insuficiente) ---");
        carteira2.enviarDinheiro(500, carteira3);

        // Teste: Criar carteira com moeda diferente
        System.out.println("\n--- Teste 5: Enviar dinheiro entre moedas diferentes ---");
        CarteiraDigital carteiraUSD = new CarteiraDigital("Ana Silva", 100, "USD", true);
        carteira1.enviarDinheiro(50, carteiraUSD);

        // Teste: Carteira inativa
        System.out.println("\n--- Teste 6: Enviar dinheiro de/para carteira inativa ---");
        CarteiraDigital carteiraInativa = new CarteiraDigital("Carlos Santos", 200, "EUR", false);
        carteiraInativa.enviarDinheiro(50, carteira1);
        carteira1.enviarDinheiro(50, carteiraInativa);

        // Teste: Tentar gastar mais do que tem
        System.out.println("\n--- Teste 7: Tentar gastar mais do que o saldo disponível ---");
        carteira3.gastar(500);
        carteira3.consultarSaldo();
    }
}
