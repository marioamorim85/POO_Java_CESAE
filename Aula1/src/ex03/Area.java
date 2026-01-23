package ex03;

public class Area {
    public static void main(String[] args) {
        double altura = 5.0;
        double largura = 10.0;
        double area = altura * largura;
        System.out.println("A área do retângulo é: " + area);

        double base = 5.0;
        double alturaTriangulo = 10.0;
        double areaTriangulo = (base * alturaTriangulo) / 2;
        System.out.println("A área do triângulo é: " + areaTriangulo);

        // Area do circulo
        double raio = 7.0;
        double areaCirculo = Math.PI * Math.pow(raio, 2);
        System.out.println("A área do círculo é: " + areaCirculo);
    }
}
