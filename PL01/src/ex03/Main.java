package ex03;

public class Main {
    public static void main(String[] args) {
        //1. Instanciar dois objectos da classe Triangulo
        Triangulo triangulo1 = new Triangulo(7.0, 4.0);
        Triangulo triangulo2 = new Triangulo(5.0, 3.0);

        //2. Calcular e mostrar a área de cada triângulo
        double area1 = triangulo1.calcularArea();
        double area2 = triangulo2.calcularArea();

        //3. Mostrar os resultados
        System.out.println("Área do Triângulo 1: " + area1);
        System.out.println("Área do Triângulo 2: " + area2);

        //3.1 Mostrar os resultados com valores da base e altura
        System.out.println("Triângulo 1 -> Base:" + triangulo1.getBase() + ", Altura:" + triangulo1.getAltura() + ", Área:" + area1);
        System.out.println("Triângulo 2 -> Base:" + triangulo2.getBase() + ", Altura:" + triangulo2.getAltura() + ", Área:" + area2);

        //4. Comparar as áreas e indicar a maior
        if (area1 > area2) {
            System.out.println("O triângulo 1 tem a maior área.");
        } else if (area2 > area1) {
            System.out.println("O triângulo 2 tem a maior área.");
        } else {
            System.out.println("Os dois triângulos têm áreas iguais.");
        }
    }
}
