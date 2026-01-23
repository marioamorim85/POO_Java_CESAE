package ex04;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar um cilindro
        Cilindro cilindro1 = new Cilindro(3.0, 5.0);

        // 2. Calcular volume e área lateral
        double volume = cilindro1.calcularVolume();
        double areaLateral = cilindro1.calcularAreaSuperficie();

        // 3. Exibir os resultados
        System.out.println("Cilindro 1 -> Raio: " + cilindro1.getRaio() + ", Altura: " + cilindro1.getAltura());
        System.out.printf("Volume: %.2f\n", volume);
        System.out.printf("Área Lateral: %.2f\n", areaLateral);
    }
}
