/**
 * Classe Principal - Demonstração de Exceções em Java
 *
 * Esta classe demonstra o uso de exceções personalizadas e blocos try-catch
 * num sistema bancário simulado.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         SISTEMA BANCÁRIO COM EXCEÇÕES                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ========== 1. CRIAR CONTAS BANCÁRIAS ==========
        System.out.println("1. CRIAR CONTAS BANCÁRIAS");
        System.out.println("─".repeat(50));

        ContaBancaria contaJoao = null;
        ContaBancaria contaMaria = null;

        try {
            contaJoao = new ContaBancaria("João Silva", 1000.00, "PT50-0001");
            System.out.println("Conta criada: " + contaJoao);

            contaMaria = new ContaBancaria("Maria Santos", 500.00, "PT50-0002");
            System.out.println("Conta criada: " + contaMaria);

        } catch (ValorInvalidoException e) {
            System.out.println("ERRO ao criar conta: " + e.getMessage());
        }
        System.out.println();

        // ========== 2. OPERAÇÕES BEM-SUCEDIDAS ==========
        System.out.println("2. OPERAÇÕES BEM-SUCEDIDAS");
        System.out.println("─".repeat(50));

        try {
            // Depósito
            contaJoao.depositar(500.00);
            System.out.println("Saldo atual: " + String.format("%.2f", contaJoao.getSaldo()) + " euros");

            // Levantamento
            contaJoao.levantar(200.00);
            System.out.println("Saldo atual: " + String.format("%.2f", contaJoao.getSaldo()) + " euros");

            // Transferência
            contaJoao.transferir(300.00, contaMaria);
            System.out.println("Saldo João: " + String.format("%.2f", contaJoao.getSaldo()) + " euros");
            System.out.println("Saldo Maria: " + String.format("%.2f", contaMaria.getSaldo()) + " euros");

        } catch (ValorInvalidoException | SaldoInsuficienteException | ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println();

        // ========== 3. TRATAMENTO DE SALDO INSUFICIENTE ==========
        System.out.println("3. TRATAMENTO DE SALDO INSUFICIENTE");
        System.out.println("─".repeat(50));

        try {
            System.out.println("A tentar levantar 5000.00 euros da conta do João...");
            contaJoao.levantar(5000.00);

        } catch (SaldoInsuficienteException e) {
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("  → Saldo disponível: " + String.format("%.2f", e.getSaldoDisponivel()) + " euros");
            System.out.println("  → Valor solicitado: " + String.format("%.2f", e.getValorSolicitado()) + " euros");
            System.out.println("  → Valor em falta: " + String.format("%.2f", e.getValorEmFalta()) + " euros");

        } catch (ValorInvalidoException | ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println();

        // ========== 4. TRATAMENTO DE VALOR INVÁLIDO ==========
        System.out.println("4. TRATAMENTO DE VALOR INVÁLIDO");
        System.out.println("─".repeat(50));

        try {
            System.out.println("A tentar depositar -100.00 euros...");
            contaJoao.depositar(-100.00);

        } catch (ValorInvalidoException e) {
            System.out.println("ERRO: " + e.getMessage());

        } catch (ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        try {
            System.out.println("A tentar levantar 0.00 euros...");
            contaJoao.levantar(0);

        } catch (ValorInvalidoException e) {
            System.out.println("ERRO: " + e.getMessage());

        } catch (SaldoInsuficienteException | ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println();

        // ========== 5. TRATAMENTO DE CONTA INATIVA ==========
        System.out.println("5. TRATAMENTO DE CONTA INATIVA");
        System.out.println("─".repeat(50));

        // Desativar a conta da Maria
        contaMaria.desativarConta();

        try {
            System.out.println("A tentar depositar na conta inativa da Maria...");
            contaMaria.depositar(100.00);

        } catch (ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());
            System.out.println("  → Conta afetada: " + e.getNumeroConta());

        } catch (ValorInvalidoException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        try {
            System.out.println("A tentar transferir para a conta inativa da Maria...");
            contaJoao.transferir(50.00, contaMaria);

        } catch (ContaInativaException e) {
            System.out.println("ERRO: " + e.getMessage());

        } catch (ValorInvalidoException | SaldoInsuficienteException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.println();

        // ========== 6. USO DO BLOCO FINALLY ==========
        System.out.println("6. USO DO BLOCO FINALLY");
        System.out.println("─".repeat(50));

        try {
            System.out.println("A iniciar operação bancária...");
            contaJoao.levantar(100.00);
            System.out.println("Operação concluída com sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO durante a operação: " + e.getMessage());

        } finally {
            // O bloco finally executa sempre, independentemente de exceções
            System.out.println("── BLOCO FINALLY ──");
            System.out.println("  Este bloco executa SEMPRE, mesmo que ocorra uma exceção.");
            System.out.println("  Útil para: fechar ficheiros, libertar recursos, etc.");
        }
        System.out.println();

        // ========== 7. MÚLTIPLOS CATCH NUM ÚNICO BLOCO ==========
        System.out.println("7. MÚLTIPLOS CATCH NUM ÚNICO BLOCO (Java 7+)");
        System.out.println("─".repeat(50));

        // Reativar a conta da Maria para demonstrar
        contaMaria.ativarConta();

        try {
            // Esta operação pode lançar várias exceções
            System.out.println("A tentar transferir 10000.00 euros...");
            contaJoao.transferir(10000.00, contaMaria);

        } catch (ValorInvalidoException | SaldoInsuficienteException | ContaInativaException e) {
            // Capturar múltiplas exceções num único catch
            System.out.println("ERRO capturado (multi-catch): " + e.getMessage());
            System.out.println("  → Tipo de exceção: " + e.getClass().getSimpleName());
        }
        System.out.println();

        // ========== 8. RESUMO FINAL ==========
        System.out.println("8. RESUMO FINAL");
        System.out.println("─".repeat(50));
        System.out.println("\nEstado das contas:");
        contaJoao.consultarSaldo();
        System.out.println("  Total de operações: " + contaJoao.getTotalOperacoes());
        System.out.println();
        contaMaria.consultarSaldo();
        System.out.println("  Total de operações: " + contaMaria.getTotalOperacoes());

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONCLUSÃO: As exceções permitem tratar erros de forma       ║");
        System.out.println("║  elegante, fornecendo informação útil ao utilizador.         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
