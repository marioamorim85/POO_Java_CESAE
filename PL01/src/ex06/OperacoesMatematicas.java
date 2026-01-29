package ex06;

/**
 * A classe OperacoesMatematicas fornece métodos para calculos comuns
 * Esta classe demonstra o uso de métodos que NÃO usam atributos 
 * e que retornam valores (using return)
 */

public class OperacoesMatematicas {

    /**
     * Construtor da classe
     * Mesmo que classe não tenha atributos, preciamos de um construtor
     * para criar o objecto
     * Sem este construtor, não podemos criar objectos desta classe
     */
    public OperacoesMatematicas() {
        // Vazio, pois não há atributos para inicializar
    }

    /**
     * Calcula a optencia de um numero
     * 
     * @param base a base da potencia
     * @param expoente o expoente da potencia
     * @return o resultado da potencia
     */
    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    /**
     * Calcula a raiz quadrada de um número
     * 
     * @param num o número para o qual queremos a raiz
     * @return o resultado da raiz quadrada
     */
    public double raizQuadrada (double num) {
        return Math.sqrt(num);
    }

    /**
     * Calcula a média aritmética de 3 números
     * 
     * @param n1 o primeiro número
     * @param n2 o segundo número
     * @param n3 o terceiro némero
     * @return a média aritmética dos 3 números
     */

    public double media(double n1, double n2, double n3) {
        return (n1+n2+n3) / 3;
    }
}
