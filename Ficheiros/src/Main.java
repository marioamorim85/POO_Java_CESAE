import java.util.ArrayList;
import java.io.File;

/**
 * Classe Principal - Demonstração de Leitura e Escrita de Ficheiros
 *
 * Esta classe demonstra operações de I/O em Java, incluindo
 * escrita e leitura de ficheiros CSV e geração de relatórios.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("+================================================================+");
        System.out.println("|         GESTAO DE ALUNOS COM FICHEIROS                        |");
        System.out.println("|     Demonstracao de I/O em Java                               |");
        System.out.println("+================================================================+\n");

        // Definir caminhos dos ficheiros
        // Usar File.separator para compatibilidade entre Windows e Linux
        String pastaActual = System.getProperty("user.dir");
        String pastaDados = pastaActual + File.separator + "dados";
        String ficheiroCSV = pastaDados + File.separator + "alunos.csv";
        String ficheiroRelatorio = pastaDados + File.separator + "relatorio.txt";

        System.out.println("Pasta de dados: " + pastaDados);
        System.out.println("Ficheiro CSV: " + ficheiroCSV);
        System.out.println("Ficheiro Relatorio: " + ficheiroRelatorio);
        System.out.println();

        // ========== 1. CRIAR LISTA DE ALUNOS ==========
        System.out.println("1. CRIAR LISTA DE ALUNOS");
        System.out.println("-".repeat(50));

        ArrayList<Aluno> alunos = new ArrayList<>();

        // Adicionar alunos à lista
        alunos.add(new Aluno(1001, "Joao Silva", "Informatica", 15.5));
        alunos.add(new Aluno(1002, "Maria Santos", "Design", 17.0));
        alunos.add(new Aluno(1003, "Pedro Costa", "Marketing", 14.0));
        alunos.add(new Aluno(1004, "Ana Ferreira", "Informatica", 18.5));
        alunos.add(new Aluno(1005, "Rui Oliveira", "Design", 9.5));

        // Mostrar alunos criados
        System.out.println("Alunos criados:");
        for (Aluno aluno : alunos) {
            System.out.println("  " + aluno);
        }
        System.out.println();

        // ========== 2. GUARDAR ALUNOS NO FICHEIRO CSV ==========
        System.out.println("2. GUARDAR ALUNOS NO FICHEIRO CSV");
        System.out.println("-".repeat(50));

        boolean guardou = GestorFicheiros.guardarAlunos(alunos, ficheiroCSV);
        if (guardou) {
            System.out.println("Operacao concluida com sucesso!");
        }
        System.out.println();

        // ========== 3. CARREGAR ALUNOS DO FICHEIRO ==========
        System.out.println("3. CARREGAR ALUNOS DO FICHEIRO");
        System.out.println("-".repeat(50));

        // Limpar a lista para demonstrar o carregamento
        System.out.println("A limpar lista em memoria...");
        alunos.clear();
        System.out.println("Lista em memoria: " + alunos.size() + " alunos");

        // Carregar do ficheiro
        System.out.println("\nA carregar do ficheiro...");
        alunos = GestorFicheiros.carregarAlunos(ficheiroCSV);

        System.out.println("\nAlunos carregados:");
        for (Aluno aluno : alunos) {
            System.out.println("  " + aluno);
        }
        System.out.println();

        // ========== 4. ADICIONAR NOVO ALUNO AO FICHEIRO ==========
        System.out.println("4. ADICIONAR NOVO ALUNO AO FICHEIRO");
        System.out.println("-".repeat(50));

        Aluno novoAluno = new Aluno(1006, "Sofia Rodrigues", "Marketing", 16.0);
        System.out.println("Novo aluno: " + novoAluno);

        boolean adicionou = GestorFicheiros.adicionarAluno(novoAluno, ficheiroCSV);
        if (adicionou) {
            alunos.add(novoAluno);  // Adicionar também à lista em memória
            System.out.println("Aluno adicionado com sucesso!");
        }
        System.out.println();

        // ========== 5. GERAR RELATORIO FORMATADO ==========
        System.out.println("5. GERAR RELATORIO FORMATADO");
        System.out.println("-".repeat(50));

        boolean exportou = GestorFicheiros.exportarRelatorio(alunos, ficheiroRelatorio);
        if (exportou) {
            System.out.println("Relatorio gerado com sucesso!");
        }
        System.out.println();

        // ========== 6. DEMONSTRAR TRATAMENTO DE ERROS ==========
        System.out.println("6. DEMONSTRAR TRATAMENTO DE ERROS");
        System.out.println("-".repeat(50));

        // Tentar carregar um ficheiro que não existe
        System.out.println("A tentar carregar ficheiro inexistente...");
        ArrayList<Aluno> alunosInexistentes = GestorFicheiros.carregarAlunos("ficheiro_inexistente.csv");
        System.out.println("Resultado: " + alunosInexistentes.size() + " alunos carregados");
        System.out.println();

        // ========== 7. VERIFICAR FICHEIROS CRIADOS ==========
        System.out.println("7. VERIFICAR FICHEIROS CRIADOS");
        System.out.println("-".repeat(50));

        System.out.println("Ficheiro CSV existe? " +
            (GestorFicheiros.ficheiroExiste(ficheiroCSV) ? "Sim" : "Nao"));
        System.out.println("Ficheiro Relatorio existe? " +
            (GestorFicheiros.ficheiroExiste(ficheiroRelatorio) ? "Sim" : "Nao"));
        System.out.println();

        // ========== 8. LER E MOSTRAR CONTEUDO DOS FICHEIROS ==========
        System.out.println("8. CONTEUDO DO FICHEIRO CSV");
        System.out.println("-".repeat(50));

        mostrarConteudoFicheiro(ficheiroCSV);
        System.out.println();

        System.out.println("9. CONTEUDO DO RELATORIO");
        System.out.println("-".repeat(50));

        mostrarConteudoFicheiro(ficheiroRelatorio);
        System.out.println();

        // ========== RESUMO FINAL ==========
        System.out.println("+================================================================+");
        System.out.println("|  CONCLUSAO: O Java oferece varias classes para I/O:           |");
        System.out.println("|  - FileWriter/FileReader: escrita/leitura basica              |");
        System.out.println("|  - BufferedWriter/BufferedReader: I/O com buffer (eficiente)  |");
        System.out.println("|  - PrintWriter: escrita formatada                             |");
        System.out.println("|  - Try-with-resources: gestao automatica de recursos          |");
        System.out.println("+================================================================+");
    }

    /**
     * Método auxiliar para mostrar o conteúdo de um ficheiro.
     *
     * @param ficheiro Caminho do ficheiro a mostrar.
     */
    private static void mostrarConteudoFicheiro(String ficheiro) {
        java.io.File file = new java.io.File(ficheiro);
        if (!file.exists()) {
            System.out.println("Ficheiro nao encontrado: " + ficheiro);
            return;
        }

        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(
                    new java.io.FileInputStream(ficheiro), "UTF-8"))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

        } catch (java.io.IOException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }
}
