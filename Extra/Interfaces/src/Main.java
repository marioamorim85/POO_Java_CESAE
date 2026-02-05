import java.util.ArrayList;

/**
 * Classe Principal - Demonstração de Interfaces em Java
 *
 * Esta classe demonstra o uso de interfaces, implementação múltipla
 * e polimorfismo através de interfaces.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              SISTEMA DE PAGAMENTOS                           ║");
        System.out.println("║         Demonstração de Interfaces em Java                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ========== 1. CRIAR MÉTODOS DE PAGAMENTO ==========
        System.out.println("1. CRIAR MÉTODOS DE PAGAMENTO");
        System.out.println("─".repeat(50));

        // Criar diferentes tipos de métodos de pagamento
        CartaoCredito cartaoCredito = new CartaoCredito("1234567890121234", "João Silva", 2000.00);
        ContaBancaria contaBancaria = new ContaBancaria("PT50 0001 0001 1234 5678 9012 3", "Maria Santos", 5000.00);
        CartaoPrePago cartaoPrePago = new CartaoPrePago("9876543210985678", "Pedro Costa", 100.00, 500.00);

        System.out.println(cartaoCredito);
        System.out.println(contaBancaria);
        System.out.println(cartaoPrePago);
        System.out.println();

        // ========== 2. POLIMORFISMO COM INTERFACE PAGAVEL ==========
        System.out.println("2. POLIMORFISMO COM INTERFACE PAGAVEL");
        System.out.println("─".repeat(50));

        // ArrayList que aceita qualquer objeto Pagavel
        ArrayList<Pagavel> metodosPagamento = new ArrayList<>();
        metodosPagamento.add(cartaoCredito);
        metodosPagamento.add(contaBancaria);
        metodosPagamento.add(cartaoPrePago);

        // Processar pagamentos usando polimorfismo
        double[] valores = {150.00, 500.00, 50.00};
        for (int i = 0; i < metodosPagamento.size(); i++) {
            Pagavel metodo = metodosPagamento.get(i);
            System.out.print("A processar pagamento de " + String.format("%.2f", valores[i]) +
                           " euros com " + metodo.getTipoPagamento() + "... ");
            if (metodo.processarPagamento(valores[i])) {
                System.out.println("   Saldo restante: " + String.format("%.2f", metodo.consultarSaldo()) + " euros");
            }
        }
        System.out.println();

        // ========== 3. USAR MÉTODO DEFAULT DA INTERFACE ==========
        System.out.println("3. USAR MÉTODO DEFAULT DA INTERFACE");
        System.out.println("─".repeat(50));

        System.out.println("Verificar se pode pagar 1000 euros:");
        for (Pagavel metodo : metodosPagamento) {
            boolean pode = metodo.podePagar(1000.00);
            System.out.println("  " + metodo.getTipoPagamento() + ": " + (pode ? "Sim" : "Não"));
        }

        System.out.println("\nResumo dos métodos (método default):");
        for (Pagavel metodo : metodosPagamento) {
            System.out.print("  ");
            metodo.mostrarResumo();
        }
        System.out.println();

        // ========== 4. TRANSFERÊNCIAS (INTERFACE TRANSFERIVEL) ==========
        System.out.println("4. TRANSFERÊNCIAS (INTERFACE TRANSFERIVEL)");
        System.out.println("─".repeat(50));

        // ArrayList que aceita apenas objetos Transferivel
        ArrayList<Transferivel> contasTransferiveis = new ArrayList<>();
        contasTransferiveis.add(contaBancaria);
        contasTransferiveis.add(cartaoPrePago);

        System.out.println("Saldos antes da transferência:");
        System.out.println("  Conta Bancária: " + String.format("%.2f", contaBancaria.consultarSaldo()) + " euros");
        System.out.println("  Cartão Pré-Pago: " + String.format("%.2f", cartaoPrePago.consultarSaldo()) + " euros");

        // Transferir da conta para o cartão pré-pago
        System.out.println("\nA transferir 200 euros da Conta para o Cartão Pré-Pago...");
        contaBancaria.transferir(200.00, cartaoPrePago);

        System.out.println("\nSaldos após a transferência:");
        System.out.println("  Conta Bancária: " + String.format("%.2f", contaBancaria.consultarSaldo()) + " euros");
        System.out.println("  Cartão Pré-Pago: " + String.format("%.2f", cartaoPrePago.consultarSaldo()) + " euros");
        System.out.println();

        // ========== 5. RECARGA (INTERFACE RECARREGAVEL) ==========
        System.out.println("5. RECARGA (INTERFACE RECARREGAVEL)");
        System.out.println("─".repeat(50));

        System.out.println("Estado do Cartão Pré-Pago:");
        System.out.println("  Saldo atual: " + String.format("%.2f", cartaoPrePago.getSaldoAtual()) + " euros");
        System.out.println("  Limite: " + String.format("%.2f", cartaoPrePago.getLimiteRecarga()) + " euros");
        System.out.println("  Espaço para recarga: " + String.format("%.2f", cartaoPrePago.espacoParaRecarga()) + " euros");
        System.out.println("  Está no limite? " + (cartaoPrePago.estaNoLimite() ? "Sim" : "Não"));

        System.out.println("\nA recarregar 100 euros...");
        cartaoPrePago.recarregar(100.00);

        System.out.println("\nTaxa de recarga para 50 euros: " +
                          String.format("%.2f", cartaoPrePago.calcularTaxaRecarga(50.00)) + " euros");
        System.out.println("(Constante TAXA_RECARGA_PADRAO = " + Recarregavel.TAXA_RECARGA_PADRAO * 100 + "%)");
        System.out.println();

        // ========== 6. VERIFICAR TIPOS COM INSTANCEOF ==========
        System.out.println("6. VERIFICAR TIPOS COM INSTANCEOF");
        System.out.println("─".repeat(50));

        Object[] objetos = {cartaoCredito, contaBancaria, cartaoPrePago};

        for (Object obj : objetos) {
            System.out.println("Objeto: " + obj.getClass().getSimpleName());
            System.out.println("  É Pagavel? " + (obj instanceof Pagavel));
            System.out.println("  É Transferivel? " + (obj instanceof Transferivel));
            System.out.println("  É Recarregavel? " + (obj instanceof Recarregavel));
        }
        System.out.println();

        // ========== 7. DIFERENÇA: INTERFACE VS CLASSE ABSTRATA ==========
        System.out.println("7. RESUMO: INTERFACE VS CLASSE ABSTRATA");
        System.out.println("─".repeat(50));
        System.out.println("┌────────────────────┬─────────────────┬─────────────────┐");
        System.out.println("│ Característica     │ Interface       │ Classe Abstrata │");
        System.out.println("├────────────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ Herança múltipla   │ Sim (implements)│ Não (extends)   │");
        System.out.println("│ Métodos            │ Abstratos+default│ Abstratos+concr.│");
        System.out.println("│ Atributos          │ Só constantes   │ Qualquer tipo   │");
        System.out.println("│ Construtores       │ Não             │ Sim             │");
        System.out.println("└────────────────────┴─────────────────┴─────────────────┘");
        System.out.println();

        // ========== 8. RESUMO FINAL ==========
        System.out.println("8. ESTADO FINAL DOS MÉTODOS DE PAGAMENTO");
        System.out.println("─".repeat(50));
        System.out.println(cartaoCredito);
        System.out.println(contaBancaria);
        System.out.println(cartaoPrePago);

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  CONCLUSÃO: Interfaces definem contratos que as classes      ║");
        System.out.println("║  devem cumprir, permitindo polimorfismo e flexibilidade.     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
