package poo_ex1;

/*
* Classe que representa uma Pessoa
* Este é um exemplo simples de classe em Java

* Dica: 
* Começamos por criar esta classe (Pessoa) antes da classe Main
*Faz mais sentido definir "o que é uma Pessoa" (o molde) antes de 
* criar objetos dessa classe na Main.
* É a analogia da palna da casa vs. a construção da casa.
*/ 

public class Pessoa {
    // Atributos da classe Pessoa
    private String nome; //private: só pode ser acessado dentro da classe
    private int idade;
    private double altura;

    /**
     * Construtor da classe Pessoa
     * o construtor é um método especial usado para incialixar objetos
     * É chamdado quando criamos um novo objeto da classe com o operador "new"
     * 
     * @param nome  Nome da pessoa
     * @param idade Idade da pessoa
     * @param altura Altura da pessoa
     */
    public Pessoa(String nome, int idade, double altura) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
    }

    // Métodos Getters para obter os valore, que permitem ler os valores dos atributos privados
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public double getAltura() {
        return altura;
    }
}
