/**
 * Classe Main - Demonstração do Sistema de Gestão de Funcionários
 * 
 * Esta classe demonstra o uso de:
 * - Classes abstratas e métodos abstratos
 * - Herança (extends)
 * - Polimorfismo
 * - Encapsulamento
 * - ArrayList com polimorfismo
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTÃO DE FUNCIONÁRIOS - CLASSES ABSTRATAS ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");

        // ========== 1. CRIAR O GESTOR ==========
        System.out.println("\n========== 1. CRIAR GESTOR DE FUNCIONÁRIOS ==========");
        GestorFuncionarios gestor = new GestorFuncionarios("TechSoft, Lda.");
        System.out.println("Gestor criado para a empresa: " + gestor.getNomeEmpresa());

        // ========== 2. CRIAR FUNCIONÁRIOS DE DIFERENTES TIPOS ==========
        System.out.println("\n========== 2. CRIAR FUNCIONÁRIOS ==========");
        
        // Funcionários Horistas
        FuncionarioHorista horista1 = new FuncionarioHorista(
            "João Silva", "joao.silva@techsoft.pt", "15/01/2023",
            180, 8.50  // 180 horas, 8.50€/hora
        );
        
        FuncionarioHorista horista2 = new FuncionarioHorista(
            "Ana Costa", "ana.costa@techsoft.pt", "01/03/2024",
            160, 9.00  // 160 horas, 9.00€/hora
        );
        
        // Funcionários Mensalistas
        FuncionarioMensalista mensalista1 = new FuncionarioMensalista(
            "Maria Santos", "maria.santos@techsoft.pt", "10/06/2020",
            1500.00, 150.00  // 1500€ base + 150€ subsídio
        );
        
        FuncionarioMensalista mensalista2 = new FuncionarioMensalista(
            "Pedro Ferreira", "pedro.ferreira@techsoft.pt", "01/09/2022",
            1800.00, 175.00  // 1800€ base + 175€ subsídio
        );
        
        // Funcionários Comissionados
        FuncionarioComissionado comissionado1 = new FuncionarioComissionado(
            "Carlos Oliveira", "carlos.oliveira@techsoft.pt", "20/02/2021",
            900.00, 25000.00, 4.0  // 900€ base, 25000€ vendas, 4% comissão
        );
        
        FuncionarioComissionado comissionado2 = new FuncionarioComissionado(
            "Sofia Rodrigues", "sofia.rodrigues@techsoft.pt", "15/11/2023",
            850.00, 18000.00, 5.5  // 850€ base, 18000€ vendas, 5.5% comissão
        );

        // ========== 3. ADICIONAR AO GESTOR (POLIMORFISMO) ==========
        System.out.println("\n========== 3. ADICIONAR FUNCIONÁRIOS AO GESTOR ==========");
        // Note: todos são adicionados como "Funcionario" (tipo da superclasse)
        gestor.adicionarFuncionario(horista1);
        gestor.adicionarFuncionario(horista2);
        gestor.adicionarFuncionario(mensalista1);
        gestor.adicionarFuncionario(mensalista2);
        gestor.adicionarFuncionario(comissionado1);
        gestor.adicionarFuncionario(comissionado2);

        // ========== 4. LISTAR TODOS OS FUNCIONÁRIOS ==========
        System.out.println("\n========== 4. LISTAR FUNCIONÁRIOS ==========");
        gestor.listarFuncionarios();

        // ========== 5. MOSTRAR DETALHES INDIVIDUAIS ==========
        System.out.println("\n========== 5. DETALHES DE CADA TIPO ==========");
        
        System.out.println("\n--- EXEMPLO DE FUNCIONÁRIO HORISTA ---");
        horista1.mostrarDetalhes();
        
        System.out.println("\n--- EXEMPLO DE FUNCIONÁRIO MENSALISTA ---");
        mensalista1.mostrarDetalhes();
        
        System.out.println("\n--- EXEMPLO DE FUNCIONÁRIO COMISSIONADO ---");
        comissionado1.mostrarDetalhes();

        // ========== 6. RELATÓRIO DE FOLHA SALARIAL ==========
        System.out.println("\n========== 6. FOLHA SALARIAL ==========");
        gestor.mostrarRelatorioFolhaSalarial();

        // ========== 7. ESTATÍSTICAS ==========
        System.out.println("\n========== 7. ESTATÍSTICAS ==========");
        gestor.mostrarEstatisticas();

        // ========== 8. LISTAR POR TIPO ==========
        System.out.println("\n========== 8. LISTAR POR TIPO ==========");
        gestor.listarPorTipo("Horista");
        gestor.listarPorTipo("Comissionado");

        // ========== 9. DEMONSTRAR POLIMORFISMO ==========
        System.out.println("\n========== 9. DEMONSTRAÇÃO DE POLIMORFISMO ==========");
        System.out.println("Percorrer ArrayList<Funcionario> e chamar calcularSalario():");
        System.out.println("(Cada subclasse executa o seu próprio método)\n");
        
        for (Funcionario f : gestor.getFuncionarios()) {
            // O método correto é chamado automaticamente!
            System.out.printf("  %s (%s): %.2f EUR\n", 
                f.getNome(), f.getTipo(), f.calcularSalario());
        }

        // ========== 10. ATUALIZAR DADOS (DEMONSTRAÇÃO) ==========
        System.out.println("\n========== 10. ATUALIZAR DADOS ==========");
        System.out.println("Atualizar vendas do comissionado Carlos Oliveira...");
        System.out.println("  Vendas anteriores: " + String.format("%.2f EUR", comissionado1.getVendasMes()));
        System.out.println("  Salario anterior: " + String.format("%.2f EUR", comissionado1.calcularSalario()));
        
        comissionado1.setVendasMes(35000.00); // Aumentar vendas
        
        System.out.println("  Vendas novas: " + String.format("%.2f EUR", comissionado1.getVendasMes()));
        System.out.println("  Salario novo: " + String.format("%.2f EUR", comissionado1.calcularSalario()));

        // ========== 11. FOLHA SALARIAL ATUALIZADA ==========
        System.out.println("\n========== 11. NOVA FOLHA SALARIAL ==========");
        gestor.mostrarRelatorioFolhaSalarial();

        // ========== FIM ==========
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMONSTRACAO CONCLUIDA!                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("\nConceitos demonstrados:");
        System.out.println("  [x] Classe abstrata (Funcionario)");
        System.out.println("  [x] Metodos abstratos (calcularSalario, mostrarDetalhes)");
        System.out.println("  [x] Heranca (extends Funcionario)");
        System.out.println("  [x] Polimorfismo (ArrayList<Funcionario> com subclasses)");
        System.out.println("  [x] Override de metodos");
        System.out.println("  [x] Encapsulamento (private + getters/setters)");
        System.out.println("  [x] Constantes e variaveis static");
    }
}
