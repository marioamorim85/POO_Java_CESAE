import java.util.Scanner;

/**
 * Classe CesaeDigital - Sistema Central de Gestão Académica
 * 
 * Esta é a classe principal que vai gerir todo o sistema.
 * 
 * TODO: Adicionar as listas de cursos, turmas, UCs, professores e alunos
 * TODO: Implementar os submenus de cada opção
 * 
 * @author [O TEU NOME]
 * @version 1.0
 */
public class CesaeDigital {
    // ========== ATRIBUTOS ==========
    // TODO: Criar ArrayList para cada entidade:
    // private ArrayList<Curso> cursos;
    // private ArrayList<Turma> turmas;
    // private ArrayList<UnidadeCurricular> ucs;
    // private ArrayList<Professor> professores;
    // private ArrayList<Aluno> alunos;

    private Scanner scanner;

    // ========== CONSTRUTOR ==========
    public CesaeDigital() {
        scanner = new Scanner(System.in);
        // TODO: Inicializar as ArrayLists
        // TODO: Chamar método para carregar dados iniciais
    }

    // ========== MENU PRINCIPAL ==========
    public void menuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║     CESAE DIGITAL - GESTÃO ACADÉMICA         ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Gestão de Cursos                         ║");
            System.out.println("║  2. Gestão de Turmas                         ║");
            System.out.println("║  3. Gestão de Unidades Curriculares          ║");
            System.out.println("║  4. Gestão de Professores                    ║");
            System.out.println("║  5. Gestão de Alunos                         ║");
            System.out.println("║  6. Estatísticas e Relatórios                ║");
            System.out.println("║  0. Sair                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    System.out.println("\n[TODO] Implementar gestão de cursos");
                    break;
                case 2:
                    System.out.println("\n[TODO] Implementar gestão de turmas");
                    break;
                case 3:
                    System.out.println("\n[TODO] Implementar gestão de UCs");
                    break;
                case 4:
                    System.out.println("\n[TODO] Implementar gestão de professores");
                    break;
                case 5:
                    System.out.println("\n[TODO] Implementar gestão de alunos");
                    break;
                case 6:
                    System.out.println("\n[TODO] Implementar estatísticas");
                    break;
                case 0:
                    System.out.println("\nObrigado por utilizar o sistema!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // ========== MÉTODO AUXILIAR ==========
    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Introduza um número: ");
            }
        }
    }
}
