import java.util.ArrayList;

/**
 * Classe Principal - Sistema de Gestão de Funcionários
 * 
 * Demonstra o funcionamento de Herança, Polimorfismo e ArrayList.
 * Cria e manipula diferentes tipos de funcionários (Funcionario, Vendedor, Gerente).
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE FUNCIONÁRIOS ===\n");

        // ========== 1. CRIAR FUNCIONÁRIOS ==========
        System.out.println("--- Funcionários Contratados ---");
        
        // Criar um Vendedor
        Vendedor joao = new Vendedor("João Silva", 1500, "Vendas");
        
        // Criar um Gerente
        Gerente maria = new Gerente("Maria Santos", 2500, "Gestão", 5);
        
        // Criar um Funcionário genérico
        Funcionario carlos = new Funcionario("Carlos Costa", 1200, "Armazém");
        
        System.out.println("Nome: " + joao.getNome() + " - Departamento: " + joao.getDepartamento() + " - Salário: " + joao.getSalario() + " euros - Horas: 0");
        System.out.println("Nome: " + maria.getNome() + " - Departamento: " + maria.getDepartamento() + " - Salário: " + maria.getSalario() + " euros - Horas: 0");
        System.out.println("Nome: " + carlos.getNome() + " - Departamento: " + carlos.getDepartamento() + " - Salário: " + carlos.getSalario() + " euros - Horas: 0");

        System.out.println("\n--- Ações com Vendedor ---");
        // ========== 2. AÇÕES COM VENDEDOR ==========
        joao.registarVenda(2000);
        joao.registarVenda(1500);
        joao.trabalhar(8);
        joao.relatorioVendas();
        System.out.println("Bónus do João: [" + joao.bonus() + " euros]");
        System.out.println(joao);

        System.out.println("\n--- Ações com Gerente ---");
        // ========== 3. AÇÕES COM GERENTE ==========
        maria.trabalhar(10);
        maria.adicionarAEquipa();
        maria.relatorioEquipa();
        System.out.println("Bónus da Maria: [" + maria.bonus() + " euros]");
        System.out.println(maria);

        System.out.println("\n--- Todas as Pessoas Trabalharam Mais 5 Horas ---");
        // ========== 4. AÇÕES COMUNS (ArrayList) ==========
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(joao);
        funcionarios.add(maria);
        funcionarios.add(carlos);
        
        // Usar for-each para fazer todos trabalharem mais 5 horas
        for (Funcionario func : funcionarios) {
            func.trabalhar(5);
        }
        
        // Imprimir cada funcionário
        for (Funcionario func : funcionarios) {
            System.out.println(func);
        }

        System.out.println("\n--- Relatórios por Tipo ---");
        // ========== 5. DOWNCAST (instanceof) ==========
        for (Funcionario func : funcionarios) {
            // Verificar se é Vendedor
            if (func instanceof Vendedor) {
                Vendedor v = (Vendedor) func;
                v.relatorioVendas();
            }
            // Verificar se é Gerente
            else if (func instanceof Gerente) {
                Gerente g = (Gerente) func;
                g.relatorioEquipa();
            }
            // Caso contrário, é um Funcionário regular
            else {
                System.out.println("Funcionário regular");
            }
        }

        System.out.println("\n--- Folha de Pagamento ---");
        // ========== 6. CALCULAR FOLHA DE PAGAMENTO ==========
        System.out.println("Salário Base Total: " + (1500 + 2500 + 1200) + " euros");
        System.out.println("Bónus do João: " + joao.bonus() + " euros");
        System.out.println("Bónus da Maria: " + maria.bonus() + " euros");
        System.out.println("Bónus do Carlos: " + carlos.bonus() + " euros");
        
        double totalBonus = joao.bonus() + maria.bonus() + carlos.bonus() - (joao.getSalario() + maria.getSalario() + carlos.getSalario());
        System.out.println("Total de Bónus este Mês: " + totalBonus + " euros");
        
        double totalAPagar = (joao.getSalario() + maria.getSalario() + carlos.getSalario()) + totalBonus;
        System.out.println("Total a Pagar: " + totalAPagar + " euros");

        System.out.println("\n--- Resumo Final ---");
        // ========== 7. RESUMO FINAL ==========
        System.out.println("Nome: " + joao.getNome() + " - Departamento: " + joao.getDepartamento() + " - Salário: " + joao.getSalario() + " euros - Horas: " + joao.getHoras_trabalho());
        System.out.println("Nome: " + maria.getNome() + " - Departamento: " + maria.getDepartamento() + " - Salário: " + maria.getSalario() + " euros - Horas: " + maria.getHoras_trabalho());
        System.out.println("Nome: " + carlos.getNome() + " - Departamento: " + carlos.getDepartamento() + " - Salário: " + carlos.getSalario() + " euros - Horas: " + carlos.getHoras_trabalho());
        
        // Calcular horas totais
        int horasTotais = joao.getHoras_trabalho() + maria.getHoras_trabalho() + carlos.getHoras_trabalho();
        System.out.println("\nTotal de Horas Trabalhadas: " + horasTotais + "h");
        System.out.println("Total a Pagar (Salário + Bónus): " + totalAPagar + " euros");
    }
}
