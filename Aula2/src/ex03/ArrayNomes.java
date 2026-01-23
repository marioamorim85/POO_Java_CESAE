package ex03;

public class ArrayNomes {
    public static void main(String[] args) {
        String[] nomes = {"Mário", "Ana", "Tomás"};

        System.out.println("Pimeiro nome: " + nomes[0]);
        System.out.println("Último nome: " + nomes[nomes.length - 1]);

        // Tmanho da string nome 2
        System.out.println("Tamanho da string segundo nome: " + nomes[1].length());

        System.out.println("Todos os nomes:");
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }
}
