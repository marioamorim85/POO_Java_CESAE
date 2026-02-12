import javax.swing.*;

/**
 * Classe principal da aplicacao Swing
 * Sistema de Gestao Academica CESAE Digital
 * 
 * @author Mario Amorim
 * @version 2.0 - Swing GUI
 */
public class App {

    public static void main(String[] args) {
        // Executar na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Usar look and feel do sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                System.err.println("Failed to set system look and feel: " + e.getMessage());
            }
            
            // Criar e mostrar a janela principal
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
