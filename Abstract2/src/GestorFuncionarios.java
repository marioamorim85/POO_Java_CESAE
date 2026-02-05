import java.util.ArrayList;

/**
 * Classe GestorFuncionarios
 * 
 * Gere uma coleção de funcionários e fornece operações
 * como listagem, cálculo de folha salarial e estatísticas.
 * 
 * Demonstra o uso de polimorfismo - um ArrayList de Funcionario
 * pode conter objetos de qualquer subclasse.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class GestorFuncionarios {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Lista de todos os funcionários */
    private final ArrayList<Funcionario> funcionarios;
    /** Nome da empresa */
    private final String nomeEmpresa;

    // ========== CONSTRUTOR ==========
    /**
     * Construtor do GestorFuncionarios.
     * 
     * @param nomeEmpresa Nome da empresa.
     */
    public GestorFuncionarios(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.funcionarios = new ArrayList<>();
    }

    // ========== MÉTODOS DE GESTÃO ==========
    /**
     * Adiciona um funcionário à lista.
     * Aceita qualquer subclasse de Funcionario (polimorfismo).
     * 
     * @param funcionario Funcionário a adicionar.
     * @return true se adicionou com sucesso.
     */
    public boolean adicionarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            System.out.println("Erro: Funcionário inválido.");
            return false;
        }
        funcionarios.add(funcionario);
        System.out.println("Funcionário " + funcionario.getNome() + " adicionado com sucesso!");
        return true;
    }

    /**
     * Remove um funcionário da lista pelo número.
     * 
     * @param numeroFuncionario Número do funcionário.
     * @return true se removeu, false se não encontrou.
     */
    public boolean removerFuncionario(int numeroFuncionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNumeroFuncionario() == numeroFuncionario) {
                Funcionario removido = funcionarios.remove(i);
                System.out.println("Funcionário " + removido.getNome() + " removido.");
                return true;
            }
        }
        System.out.println("Funcionário não encontrado.");
        return false;
    }

    /**
     * Encontra um funcionário pelo número.
     * 
     * @param numeroFuncionario Número do funcionário.
     * @return O funcionário ou null.
     */
    public Funcionario encontrarFuncionario(int numeroFuncionario) {
        for (Funcionario f : funcionarios) {
            if (f.getNumeroFuncionario() == numeroFuncionario) {
                return f;
            }
        }
        return null;
    }

    // ========== MÉTODOS DE LISTAGEM ==========
    /**
     * Lista todos os funcionários.
     * Demonstra polimorfismo - o toString() correto é chamado.
     */
    public void listarFuncionarios() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║        LISTA DE FUNCIONÁRIOS - " + nomeEmpresa);
        System.out.println("╠══════════════════════════════════════════════╣");
        
        if (funcionarios.isEmpty()) {
            System.out.println("  (Nenhum funcionário registado)");
        } else {
            for (Funcionario f : funcionarios) {
                // Polimorfismo: chama o toString() de cada subclasse
                System.out.println("  " + f);
            }
        }
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("  Total: " + funcionarios.size() + " funcionário(s)");
    }

    /**
     * Lista funcionários de um tipo específico.
     * 
     * @param tipo Tipo a filtrar ("Horista", "Mensalista", "Comissionado").
     */
    public void listarPorTipo(String tipo) {
        System.out.println("\n=== FUNCIONÁRIOS DO TIPO: " + tipo.toUpperCase() + " ===");
        int count = 0;
        for (Funcionario f : funcionarios) {
            if (f.getTipo().equalsIgnoreCase(tipo)) {
                System.out.println("  " + f);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("  (Nenhum funcionário deste tipo)");
        }
        System.out.println("  Total: " + count);
    }

    /**
     * Mostra detalhes de todos os funcionários.
     * Demonstra polimorfismo - o mostrarDetalhes() correto é chamado.
     */
    public void mostrarTodosDetalhes() {
        System.out.println("\n========== DETALHES DE TODOS OS FUNCIONÁRIOS ==========");
        for (Funcionario f : funcionarios) {
            System.out.println();
            // Polimorfismo: chama o mostrarDetalhes() de cada subclasse
            f.mostrarDetalhes();
        }
    }

    // ========== MÉTODOS DE CÁLCULO ==========
    /**
     * Calcula o total da folha salarial.
     * Soma os salários de todos os funcionários.
     * 
     * @return Valor total a pagar.
     */
    public double calcularFolhaSalarial() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            // Polimorfismo: chama o calcularSalario() de cada subclasse
            total += f.calcularSalario();
        }
        return total;
    }

    /**
     * Encontra o funcionário com maior salário.
     * 
     * @return O funcionário com maior salário ou null.
     */
    public Funcionario encontrarMaiorSalario() {
        if (funcionarios.isEmpty()) {
            return null;
        }
        
        Funcionario maior = funcionarios.get(0);
        for (Funcionario f : funcionarios) {
            if (f.calcularSalario() > maior.calcularSalario()) {
                maior = f;
            }
        }
        return maior;
    }

    /**
     * Encontra o funcionário com menor salário.
     * 
     * @return O funcionário com menor salário ou null.
     */
    public Funcionario encontrarMenorSalario() {
        if (funcionarios.isEmpty()) {
            return null;
        }
        
        Funcionario menor = funcionarios.get(0);
        for (Funcionario f : funcionarios) {
            if (f.calcularSalario() < menor.calcularSalario()) {
                menor = f;
            }
        }
        return menor;
    }

    /**
     * Calcula a média salarial.
     * 
     * @return Média dos salários ou 0 se não houver funcionários.
     */
    public double calcularMediaSalarial() {
        if (funcionarios.isEmpty()) {
            return 0;
        }
        return calcularFolhaSalarial() / funcionarios.size();
    }

    // ========== MÉTODOS DE RELATÓRIO ==========
    /**
     * Mostra o relatório da folha salarial.
     */
    public void mostrarRelatorioFolhaSalarial() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║           RELATÓRIO DE FOLHA SALARIAL                ║");
        System.out.println("║           " + nomeEmpresa);
        System.out.println("╠══════════════════════════════════════════════════════╣");
        
        if (funcionarios.isEmpty()) {
            System.out.println("  (Nenhum funcionário registado)");
        } else {
            System.out.println("  Funcionário                          Tipo           Salário");
            System.out.println("  ─────────────────────────────────────────────────────────────");
            
            for (Funcionario f : funcionarios) {
                System.out.printf("  %-35s %-12s %10.2f EUR\n", 
                    f.getNome(), f.getTipo(), f.calcularSalario());
            }
            
            System.out.println("  ═════════════════════════════════════════════════════════════");
            System.out.printf("  TOTAL A PAGAR:                                    %10.2f EUR\n", 
                calcularFolhaSalarial());
            System.out.printf("  MEDIA SALARIAL:                                   %10.2f EUR\n", 
                calcularMediaSalarial());
        }
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    /**
     * Mostra estatísticas gerais.
     */
    public void mostrarEstatisticas() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║              ESTATÍSTICAS                    ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        
        // Contar por tipo
        int horistas = 0, mensalistas = 0, comissionados = 0;
        for (Funcionario f : funcionarios) {
            switch (f.getTipo()) {
                case "Horista" -> horistas++;
                case "Mensalista" -> mensalistas++;
                case "Comissionado" -> comissionados++;
            }
        }
        
        System.out.println("  Total de Funcionários: " + funcionarios.size());
        System.out.println("    - Horistas: " + horistas);
        System.out.println("    - Mensalistas: " + mensalistas);
        System.out.println("    - Comissionados: " + comissionados);
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("  Folha Salarial Total: %.2f EUR\n", calcularFolhaSalarial());
        System.out.printf("  Media Salarial: %.2f EUR\n", calcularMediaSalarial());
        
        Funcionario maior = encontrarMaiorSalario();
        Funcionario menor = encontrarMenorSalario();
        
        if (maior != null) {
            System.out.printf("  Maior Salario: %s - %.2f EUR\n", 
                maior.getNome(), maior.calcularSalario());
        }
        if (menor != null) {
            System.out.printf("  Menor Salario: %s - %.2f EUR\n", 
                menor.getNome(), menor.calcularSalario());
        }
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // ========== GETTERS ==========
    /**
     * Obtém a lista de funcionários.
     * 
     * @return ArrayList de Funcionario.
     */
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Obtém o número de funcionários.
     * 
     * @return Quantidade de funcionários.
     */
    public int getNumeroFuncionarios() {
        return funcionarios.size();
    }

    /**
     * Obtém o nome da empresa.
     * 
     * @return Nome da empresa.
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
}
