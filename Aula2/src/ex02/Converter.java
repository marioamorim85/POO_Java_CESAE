package ex02;

public class Converter {

    public static void main(String[] args) {

        // Declarar a variavel preço
        double preco = 19.99;

        // Declarar a váriavel int precoInteiro e converter o valor de preco para int
        int precoInteiro = (int) preco;

        System.out.println("Preço original: " + preco);
        System.out.println("Preço inteiro: " + precoInteiro);

        int precoInteiro2 = (int) Math.round(preco);

        System.out.println("Preço arrendodado: " + precoInteiro2);

    }
}
