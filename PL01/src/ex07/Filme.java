package ex07;

public class Filme {
    // Atributos
    private String titulo;
    private String realizador;
    private String genero;
    private int duracao; // em minutos
    private int classificacaoEtaria;

    /**
     * Construtor da classe Filme
     * 
     * @param titulo              Titulo do filme
     * @param realizador          Realizador do filme
     * @param genero              Genero do filme
     * @param duracao             Duração do filme em minutos
     * @param classificacaoEtaria Classificação etária do filme
     */
    public Filme(String titulo, String realizador, String genero, int duracao, int classificacaoEtaria) {
        this.titulo = titulo;
        this.realizador = realizador;
        this.genero = genero;
        this.duracao = duracao;
        this.classificacaoEtaria = classificacaoEtaria;
    }

    // Imprime os detalhes do filme
    public void MostrarInformacao() {
        System.out.println("Filme: " + titulo);
        System.out.println("  > Realizador: " + realizador);
        System.out.println("  > Género: " + genero);
        System.out.println("  > Duração: " + duracao + " minutos");
        System.out.println("  > Classificação Etária: " + classificacaoEtaria + " anos");
    }

    // Verifica se um espectador pode ver o filme com base na sua idade
    public boolean podeVerFilme(int idade) {
        return idade >= classificacaoEtaria;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

}
