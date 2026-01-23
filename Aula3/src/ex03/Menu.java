package ex03;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        //Iniciar o scaneer
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            // Imprint as opções do menu
            System.out.println("==== Menu ====");
            System.out.println("1. Opção 1");
            System.out.println("2. Opção 2");
            System.out.println("3. Opção 3");
            System.out.println("0. Sair");
    
            // Ler a escolha do usuário
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1 -> System.out.println("Escolheu a Opção 1");
                case 2 -> System.out.println("Escolheu a Opção 2");
                case 3 -> System.out.println("Escolheu a Opção 3");
                case 0 -> System.out.println("A sair...");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
