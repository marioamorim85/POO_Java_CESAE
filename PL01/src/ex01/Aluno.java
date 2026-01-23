package ex01;

public class Aluno {
    // Atributos
    private String nome;
    private int numero;
    private double media;

    /**
     * Construtor da classe Aluno.
     * @param nome   Nome do aluno.
     * @param numero Número de identificação do aluno.
     * @param media  Média do aluno.
     */

    public Aluno(String nome, int numero, double media) {
        this.nome = nome;
        this.numero = numero;
        this.media = media;
    }

    // Métodos Getters (para aceder aos dados de fora da classe)
    public String getNome() {
        return nome;
    }
    public int getNumero() {
        return numero;
    }
    public double getMedia() {
        return media;
    }
}
