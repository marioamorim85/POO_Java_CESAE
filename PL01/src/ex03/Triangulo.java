package ex03;

public class Triangulo {
    //Atributos privados
    private double base;
    private double altura;

    /**
     * Construtor da classe Triangulo
     * @param base
     * @param altura
     */
    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    //Método para calcular a área do triângulo
    public double calcularArea() {
        return (base * altura) / 2;
    }

    //Getters para base e altura
    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

}
