package ex06;

public class Main {
    public static void main(String[] args) {
        // 1. Criar um objecto da classe OperacoesMatematicas
        // Como o objecto não tem atributos, ele serve apenas como um "prestador de serviços"
        OperacoesMatematicas op = new OperacoesMatematicas();

        // 2. Realizar as operações e guardar os resultados
        double resultPot = op.potencia(2, 3); // 2^3 = 8
        double resultRaiz = op.raizQuadrada(16); // raiz de 16 = 4
        double resultMedia = op.media(10, 15, 20); // (10+15+20)/3 = 15

        // 3. Imprimir os resultados na consola
        System.out.println("Resultados das Operações Matemáticas:");
        System.out.println("Potência (2^3): " + resultPot);
        System.out.println("Raiz Quadrada de 16: " + resultRaiz);
        System.out.println("Média (10, 15, 20): " + resultMedia);
    }
}
