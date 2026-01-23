package ex02;

public class Gato {
    // Atributos privados
    private String nome;
    private String corPelo;
    private String som;

    /** 
     * Construtor da classe Gato.
     * @param nome    Nome do gato.
     * @param corPelo Cor do pelo do gato.
     * @param som     Som que o gato faz.
    */
   public Gato (String nome, String corPelo) {
        this.nome = nome;
        this.corPelo = corPelo;
        this.som = "Miau";
   }

   /**
    * Método miar: imprime na consola o nome do gato e o seu som actual 
    */
   public void miar() {
    System.out.println(nome + " diz " + som);
   }

   // Método brincar: imprime uma mensagem a indicar que o gato está a brincar
   public void brincar() {
    System.out.println(nome + " está a brincar!");
   }

   /**
    * Método Getter: permite ler os contéudos dos atributos privados
    * @return nome do gato
    */
    public String getNome() {
        return nome;
    }

    public String getCorPelo() {
        return corPelo;
    }

    public String getSom() {
        return som;
    }

    /**
     * Método Setter: permite alterar os contéudos dos atributos privados
     * @param novoSom o novo som do gato
     */
    public void setSom (String novoSom) {
        this.som = novoSom;
    }
}
