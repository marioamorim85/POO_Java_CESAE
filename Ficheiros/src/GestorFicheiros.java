import java.io.*;
import java.util.ArrayList;

/**
 * Classe GestorFicheiros - Utilitário para operações de I/O.
 *
 * Esta classe contém métodos estáticos para guardar e carregar
 * listas de alunos em ficheiros CSV e gerar relatórios formatados.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class GestorFicheiros {

    // ========== CONSTANTES ==========
    /** Codificação de caracteres usada nos ficheiros */
    private static final String CHARSET = "UTF-8";

    // ========== MÉTODOS DE ESCRITA ==========

    /**
     * Guarda uma lista de alunos num ficheiro CSV.
     * Cria o ficheiro se não existir, ou substitui se existir.
     *
     * @param alunos   Lista de alunos a guardar.
     * @param ficheiro Caminho do ficheiro de destino.
     * @return true se guardou com sucesso, false caso contrário.
     */
    public static boolean guardarAlunos(ArrayList<Aluno> alunos, String ficheiro) {
        // Criar diretoria se não existir
        criarDiretoria(ficheiro);

        // Usar try-with-resources para garantir que o ficheiro é fechado
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ficheiro), CHARSET)))) {

            // Escrever cabeçalho
            writer.println(Aluno.getCabecalhoCSV());

            // Escrever cada aluno
            for (Aluno aluno : alunos) {
                writer.println(aluno.toCSV());
            }

            System.out.println("Ficheiro guardado com sucesso: " + ficheiro);
            System.out.println("Total de registos: " + alunos.size());
            return true;

        } catch (IOException e) {
            System.out.println("ERRO ao guardar ficheiro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega uma lista de alunos de um ficheiro CSV.
     *
     * @param ficheiro Caminho do ficheiro a carregar.
     * @return ArrayList com os alunos carregados (vazia se erro).
     */
    public static ArrayList<Aluno> carregarAlunos(String ficheiro) {
        ArrayList<Aluno> alunos = new ArrayList<>();

        // Verificar se o ficheiro existe
        File file = new File(ficheiro);
        if (!file.exists()) {
            System.out.println("AVISO: Ficheiro não encontrado: " + ficheiro);
            return alunos;
        }

        // Usar try-with-resources para garantir que o ficheiro é fechado
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(ficheiro), CHARSET))) {

            String linha;
            int linhasLidas = 0;
            int alunosCarregados = 0;

            // Ler linha a linha
            while ((linha = reader.readLine()) != null) {
                linhasLidas++;

                // Converter linha para Aluno
                Aluno aluno = Aluno.fromCSV(linha);

                // Adicionar se válido
                if (aluno != null) {
                    alunos.add(aluno);
                    alunosCarregados++;
                }
            }

            System.out.println("Ficheiro carregado: " + ficheiro);
            System.out.println("Linhas lidas: " + linhasLidas + " | Alunos carregados: " + alunosCarregados);

        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Ficheiro não encontrado: " + ficheiro);
        } catch (IOException e) {
            System.out.println("ERRO ao ler ficheiro: " + e.getMessage());
        }

        return alunos;
    }

    /**
     * Adiciona um aluno ao final de um ficheiro existente.
     * Se o ficheiro não existir, cria um novo com cabeçalho.
     *
     * @param aluno    Aluno a adicionar.
     * @param ficheiro Caminho do ficheiro.
     * @return true se adicionou com sucesso, false caso contrário.
     */
    public static boolean adicionarAluno(Aluno aluno, String ficheiro) {
        // Criar diretoria se não existir
        criarDiretoria(ficheiro);

        // Verificar se o ficheiro existe
        File file = new File(ficheiro);
        boolean ficheiroExiste = file.exists();

        // Usar FileWriter com append=true para adicionar ao final
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ficheiro, true), CHARSET)))) {

            // Se o ficheiro não existia, escrever cabeçalho primeiro
            if (!ficheiroExiste) {
                writer.println(Aluno.getCabecalhoCSV());
            }

            // Adicionar o aluno
            writer.println(aluno.toCSV());

            System.out.println("Aluno adicionado ao ficheiro: " + aluno.getNome());
            return true;

        } catch (IOException e) {
            System.out.println("ERRO ao adicionar aluno: " + e.getMessage());
            return false;
        }
    }

    // ========== MÉTODOS DE RELATÓRIO ==========

    /**
     * Exporta um relatório formatado com os dados dos alunos.
     *
     * @param alunos   Lista de alunos.
     * @param ficheiro Caminho do ficheiro de relatório.
     * @return true se exportou com sucesso, false caso contrário.
     */
    public static boolean exportarRelatorio(ArrayList<Aluno> alunos, String ficheiro) {
        // Criar diretoria se não existir
        criarDiretoria(ficheiro);

        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ficheiro), CHARSET)))) {

            // Calcular estatísticas
            double mediaGeral = calcularMediaGeral(alunos);
            int aprovados = contarAprovados(alunos);

            // Escrever cabeçalho do relatório
            writer.println("+================================================================+");
            writer.println("|           RELATORIO DE ALUNOS - CESAE DIGITAL                 |");
            writer.println("+================================================================+");
            writer.println("| Numero | Nome                 | Curso          | Media        |");
            writer.println("+--------+----------------------+----------------+--------------+");

            // Escrever dados dos alunos
            for (Aluno aluno : alunos) {
                writer.printf("| %-6d | %-20s | %-14s | %5.2f %-6s |%n",
                    aluno.getNumero(),
                    truncarTexto(aluno.getNome(), 20),
                    truncarTexto(aluno.getCurso(), 14),
                    aluno.getMedia(),
                    aluno.estaAprovado() ? "(Aprov)" : "(Repr)");
            }

            // Escrever rodapé com estatísticas
            writer.println("+================================================================+");
            writer.printf("| Total de alunos: %-3d                                          |%n", alunos.size());
            writer.printf("| Aprovados: %-3d | Reprovados: %-3d                              |%n",
                aprovados, alunos.size() - aprovados);
            writer.printf("| Media geral: %.2f                                              |%n", mediaGeral);
            writer.println("+================================================================+");
            writer.println("| Relatorio gerado automaticamente pelo Sistema de Gestao       |");
            writer.println("+================================================================+");

            System.out.println("Relatório exportado com sucesso: " + ficheiro);
            return true;

        } catch (IOException e) {
            System.out.println("ERRO ao exportar relatório: " + e.getMessage());
            return false;
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    /**
     * Cria a diretoria do ficheiro se não existir.
     *
     * @param ficheiro Caminho completo do ficheiro.
     */
    private static void criarDiretoria(String ficheiro) {
        File file = new File(ficheiro);
        File diretoria = file.getParentFile();
        if (diretoria != null && !diretoria.exists()) {
            if (diretoria.mkdirs()) {
                System.out.println("Diretoria criada: " + diretoria.getPath());
            }
        }
    }

    /**
     * Calcula a média geral de todos os alunos.
     *
     * @param alunos Lista de alunos.
     * @return A média geral.
     */
    private static double calcularMediaGeral(ArrayList<Aluno> alunos) {
        if (alunos.isEmpty()) return 0;

        double soma = 0;
        for (Aluno aluno : alunos) {
            soma += aluno.getMedia();
        }
        return soma / alunos.size();
    }

    /**
     * Conta o número de alunos aprovados.
     *
     * @param alunos Lista de alunos.
     * @return Número de alunos com média >= 10.
     */
    private static int contarAprovados(ArrayList<Aluno> alunos) {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (aluno.estaAprovado()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Trunca um texto para um tamanho máximo.
     *
     * @param texto   Texto a truncar.
     * @param tamanho Tamanho máximo.
     * @return Texto truncado ou original se menor que o tamanho.
     */
    private static String truncarTexto(String texto, int tamanho) {
        if (texto.length() <= tamanho) {
            return texto;
        }
        return texto.substring(0, tamanho - 3) + "...";
    }

    /**
     * Verifica se um ficheiro existe.
     *
     * @param ficheiro Caminho do ficheiro.
     * @return true se existe, false caso contrário.
     */
    public static boolean ficheiroExiste(String ficheiro) {
        return new File(ficheiro).exists();
    }

    /**
     * Elimina um ficheiro.
     *
     * @param ficheiro Caminho do ficheiro.
     * @return true se eliminou, false caso contrário.
     */
    public static boolean eliminarFicheiro(String ficheiro) {
        File file = new File(ficheiro);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
