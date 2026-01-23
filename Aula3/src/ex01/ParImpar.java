package ex01;

//Importar a classe Scanner
import java.util.Scanner;

public class ParImpar {
    public static void main(String[] args) {

        //Inicializar o Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um número inteiro: ");

        //Váriavel para armazenar o número digitado
        int numero = scanner.nextInt();

        // Verificar se o resto da divisão por 2 é igual a 0(indica que é par)
        if (numero %2 ==0) {
            System.out.println("o número " + numero + " é par.");
        } else {
            System.out.println("o número " + numero + " é ímpar.");
        }
        scanner.close();
    }
}
