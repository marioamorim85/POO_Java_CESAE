package ex07;

public class Main {
    public static void main(String[] args) {
        //1. Criar dois objectos da classe Filme
        Filme filme1 = new Filme("Inception", "Christopher Nolan", "Sci-Fi", 148, 16);
        Filme filme2 = new Filme("Rei Leão", "Roger Allers", "Animação", 88, 6);


        //2. Mostrar a informação dos filmes
        System.out.println("--- Informação dos Filmes ---");
        filme1.MostrarInformacao();
        System.out.println();
        filme2.MostrarInformacao();
        System.out.println();

        //3. Verificar se um espectador de 10 anos pode ver cada filme
        System.out.println("--- Teste de Acesso (Idade 10) ---");
        int idadeEspectador = 10;
        System.out.println("Idade do Espectador: " + idadeEspectador + " anos");

        // System.out.println("Pode ver o filme " + filme1.getTitulo() + "? " + (filme1.podeVerFilme(idadeEspectador) ? "Sim" : "Não"));
        // System.out.println("Pode ver o filme " + filme2.getTitulo() + "? " + (filme2.podeVerFilme(idadeEspectador) ? "Sim" : "Não"));

        //4. Testar acesso usando o método auxiliar
        testarAcesso(filme1, idadeEspectador);
        testarAcesso(filme2, idadeEspectador);
    }

    /**
     * Método auxiliar para verificar e imprimir se um espectador pode ou não ver o filme
     */
    public static void testarAcesso(Filme f, int idade) {
        if (f.podeVerFilme(idade)) {
            System.out.println("Pode ver o filme: " + f.getTitulo());
        } else {
            System.out.println("Não pode ver o filme: " + f.getTitulo() + " Classificação demasiado alta.");
        }
    }
}
