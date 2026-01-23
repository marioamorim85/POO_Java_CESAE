package ex02;

public class Tabuada {
    public static void main(String[] args) {
        int numero = 5; // Númeropara o qual queremos a tabuada

        System.out.println("Tabuada do " + numero + ":");

        // Loop for para iterar  de 1 a 10 e imprimir a tabuada
        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.println(numero + " x " + i + " = " + resultado);
        }
    }
}
