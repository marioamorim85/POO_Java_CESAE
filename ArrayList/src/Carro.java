/**
 * Classe Carro - Gestão de Garagem de Carros
 * 
 * Representa um carro com informações sobre marca, modelo, velocidades máxima e
 * atual.
 * Permite acelerar e travar o carro.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Carro {
    // Atributos privados
    private String marca;
    private String modelo;
    private int velocidadeMaxima;
    private int velocidadeAtual;

    /**
     * Construtor da classe Carro.
     * Inicializa o carro com marca, modelo e velocidade máxima.
     * A velocidade atual é inicializada a 0.
     * 
     * @param marca            Marca do carro.
     * @param modelo           Modelo do carro.
     * @param velocidadeMaxima Velocidade máxima do carro em km/h.
     */
    public Carro(String marca, String modelo, int velocidadeMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadeMaxima = velocidadeMaxima;
        this.velocidadeAtual = 0;
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém a marca do carro.
     * 
     * @return A marca do carro.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Obtém o modelo do carro.
     * 
     * @return O modelo do carro.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Obtém a velocidade máxima do carro.
     * 
     * @return A velocidade máxima em km/h.
     */
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    /**
     * Obtém a velocidade atual do carro.
     * 
     * @return A velocidade atual em km/h.
     */
    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define a marca do carro.
     * 
     * @param marca Nova marca do carro.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Define o modelo do carro.
     * 
     * @param modelo Novo modelo do carro.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Define a velocidade máxima do carro.
     * 
     * @param velocidadeMaxima Nova velocidade máxima em km/h.
     */
    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    /**
     * Define a velocidade atual do carro.
     * 
     * @param velocidadeAtual Nova velocidade atual em km/h.
     */
    public void setVelocidadeAtual(int velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Acelera o carro aumentando a velocidade atual.
     * A velocidade atual não pode ultrapassar a velocidade máxima.
     * 
     * @param incremento Quantidade a aumentar a velocidade (em km/h).
     */
    public void acelerar(int incremento) {
        // Verifica se o incremento é positivo
        if (incremento <= 0) {
            System.out.println("O incremento deve ser positivo!");
            return;
        }

        // Calcula a nova velocidade
        int novaVelocidade = this.velocidadeAtual + incremento;

        // Se ultrapassar a velocidade máxima, usa a velocidade máxima
        if (novaVelocidade > this.velocidadeMaxima) {
            this.velocidadeAtual = this.velocidadeMaxima;
            System.out.println("Velocidade máxima atingida: " + this.velocidadeMaxima + " km/h");
        } else {
            this.velocidadeAtual = novaVelocidade;
            System.out.println("Carro acelerou para: " + this.velocidadeAtual + " km/h");
        }
    }

    /**
     * Trava o carro diminuindo a velocidade atual.
     * A velocidade nunca pode ser negativa.
     * 
     * @param decremento Quantidade a diminuir a velocidade (em km/h).
     */
    public void travar(int decremento) {
        // Verifica se o decremento é positivo
        if (decremento <= 0) {
            System.out.println("O decremento deve ser positivo!");
            return;
        }

        // Calcula a nova velocidade
        int novaVelocidade = this.velocidadeAtual - decremento;

        // A velocidade não pode ser negativa
        if (novaVelocidade < 0) {
            this.velocidadeAtual = 0;
            System.out.println("Carro parado completamente: 0 km/h");
        } else {
            this.velocidadeAtual = novaVelocidade;
            System.out.println("Carro travou para: " + this.velocidadeAtual + " km/h");
        }
    }

    /**
     * Retorna uma representação textual do objeto Carro.
     * 
     * [POO - EXPLICAÇÃO DO @Override]:
     * A anotação @Override indica que este método está a sobrescrever (substituir)
     * a implementação original do método toString() que existe na classe 'Object'
     * (a classe "mãe" de todas as classes em Java).
     * 
     * [PORQUÊ FAZER ISTO?]:
     * Por padrão, o Java imprime apenas o endereço de memória do objeto. Ao
     * sobrescrever o toString(), garantimos que, quando usarmos
     * System.out.println(carro),
     * será exibida esta String formatada com os dados reais do carro, facilitando
     * a leitura e o debug.
     * 
     * @return String com as informações do carro no formato amigável.
     */
    @Override
    public String toString() {
        return marca + " " + modelo + " - Vel.Max: " + velocidadeMaxima + "km/h - Vel.Atual: " + velocidadeAtual
                + "km/h";
    }
}

// 1. Se não fizesse @Override, o que apareceria?
// Se não criasse o seu próprio método toString(), o Java usaria o método padrão
// da classe Object.
// O resultado ao imprimir um carro seria algo como: Carro@15db9742
// Carro: O nome da classe.
// @: Um separador.
// 15db9742: O "hashcode" do objeto em hexadecimal (basicamente um identificador
// interno baseado no endereço de memória).

// Para um utilizador ou para fazer debug, essa informação é quase inútil, pois
// não diz se é um Toyota ou um BMW.

// 2. É necessário ter o toString?
// Tecnicamente, não é obrigatório. O programa vai compilar e correr
// perfeitamente sem ele.

// No entanto, é uma boa prática quase obrigatória em POO (Programação Orientada
// a Objetos) por estas razões:

// Facilidade de Impressão:
// Sem o toString, para imprimir os dados de um carro, teria de fazer sempre:
// System.out.println(c.getMarca() + " " + c.getModelo() ...);
// Com o toString, basta fazer System.out.println(c);

// Debug: Quando está a testar o código e quer ver o que está dentro de uma
// ArrayList, o Java chama automaticamente o
// toString() de cada elemento. Sem ele, veria apenas uma lista de códigos
// estranhos.

// Clareza: Ajuda outros programadores a perceberem rapidamente qual é a
// informação mais importante desse objeto.
// Em resumo: Não é necessário para o Java, mas é fundamental para o
// Programador!