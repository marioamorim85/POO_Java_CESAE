package ex02;

// Definição da classe pública Pessoa
public class Pessoa {
    //Método main: ponto de entrada para a execução do programa em Java
    public static void main(String[] args) {

        //Declaração e inicilialização de variáveis
        String nome = "Mário Amorim"; // Variável 'nome' do tipo String
        int idade = 40;               // Variável 'idade' do tipo int
        double altura = 1.85;        // Variável 'altura' do tipo double

        // Imprime uma mensagem na consola concatenando o texto com os valores das variáveis
        System.out.println(
            "Olá, o meu nome é " + nome + ", tenho " 
                + idade + " anos e minha altura é " 
                    + altura + " metros."
        );
    }
}
