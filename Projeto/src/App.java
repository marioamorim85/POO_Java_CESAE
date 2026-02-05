/**
 * Classe Principal - Ponto de Entrada da Aplicação
 * 
 * Sistema de Gestão Académica do CESAE Digital.
 * Projeto Final de Programação Orientada a Objetos.
 * 
 * @author [O TEU NOME]
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     CESAE DIGITAL - GESTÃO ACADÉMICA         ║");
        System.out.println("║       Projeto Final - POO (Java)             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        // Criar e iniciar o sistema
        CesaeDigital sistema = new CesaeDigital();
        sistema.menuPrincipal();
    }
}
