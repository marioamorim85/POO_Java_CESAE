package ex01;

public class Array {
    public static void main(String[] args) {
        // Array de 5 nemros
        int[] numeros = {2, 4, 6, 8, 10};

        int soma = 0;

        for (int i = 0; i < numeros.length; i++) {
            soma = soma + numeros[i];
        }

        System.out.println("A soma total do array é: " + soma);
    }
}
