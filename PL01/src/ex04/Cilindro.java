package ex04;

public class Cilindro {
    //Atributos privados
    private double raio;
    private double altura;

    //Construtor
    public Cilindro(double raio, double altura) {
        this.raio = raio;
        this.altura = altura;
    }

    // Método para calcular o volume do cilindro
    public double calcularVolume() {
        return Math.PI * Math.pow(raio, 2) * altura;
    }

    // Método para calcular a área da superfície do cilindro
    public double calcularAreaSuperficie() {
        return 2 * Math.PI * raio * altura;
    }

    //Getters
    public double getAltura() {
        return altura;
    }

    public double getRaio() {
        return raio;
    }

}
