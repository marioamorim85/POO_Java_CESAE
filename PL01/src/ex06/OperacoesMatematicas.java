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

}
