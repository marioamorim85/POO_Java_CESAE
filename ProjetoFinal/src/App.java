/**
 * Classe Principal - Ponto de Entrada da Aplicação
 * 
 * Sistema de Gestão Académica do CESAE Digital.
 * Projeto Final de Programação Orientada a Objetos.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class App {
    /**
     * Método main - Ponto de entrada do programa.
     * Cria uma instância do sistema e inicia o menu principal.
     * 
     * @param args Argumentos da linha de comandos (não utilizados).
     */
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                                              ║");
        System.out.println("║     CESAE DIGITAL - GESTÃO ACADÉMICA         ║");
        System.out.println("║       Projeto Final - POO (Java)             ║");
        System.out.println("║                                              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        // Criar instância do sistema
        CesaeDigital sistema = new CesaeDigital();

        // Iniciar o menu principal
        sistema.menuPrincipal();
    }
}
