package poo_ex1;

// Classe princiapl para executar o exerício
public class Main {
    public static void main(String[] args) {
        
        // CRIAR OS OBJECTOS
        Pessoa pessoa1 = new Pessoa("Maria Silva", 28,1.65);
        Pessoa pessoa2 = new Pessoa("João Santos", 40, 1.80);
        Pessoa pessoa3 = new Pessoa("Mário Amorim", 40, 1.85);


        // Imprimir os dados da primeira pessoa
        System.out.println("==== Pessoa 1 ====");
        System.out.println("Nome: " + pessoa1.getNome());
        System.out.println("Idade: " + pessoa1.getIdade());
        System.out.println("Altura: " + pessoa1.getAltura());

        System.out.println();

        // Imprimir os dados da primeira pessoa
        System.out.println("==== Pessoa 2 ====");
        System.out.println("Nome: " + pessoa2.getNome());
        System.out.println("Idade: " + pessoa2.getIdade());
        System.out.println("Altura: " + pessoa2.getAltura());
    }
}
