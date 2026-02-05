/**
 * Classe Aluno - Representa um aluno com persistência em ficheiro.
 *
 * Esta classe inclui métodos para converter o aluno para formato CSV
 * e para criar um aluno a partir de uma linha CSV.
 *
 * @author Mário Amorim
 * @version 1.0
 */
public class Aluno {

    // ========== ATRIBUTOS PRIVADOS ==========
    private int numero;
    private String nome;
    private String curso;
    private double media;

    // ========== CONSTANTE ==========
    /** Separador usado no ficheiro CSV */
    public static final String SEPARADOR_CSV = ";";

    /**
     * Construtor da classe Aluno.
     *
     * @param numero Número do aluno.
     * @param nome   Nome do aluno.
     * @param curso  Curso do aluno.
     * @param media  Média do aluno (0-20).
     */
    public Aluno(int numero, String nome, String curso, double media) {
        this.numero = numero;
        this.nome = nome;
        this.curso = curso;
        this.media = media;
    }

    // ========== MÉTODOS DE SERIALIZAÇÃO ==========

    /**
     * Converte o aluno para uma linha no formato CSV.
     * Formato: numero;nome;curso;media
     *
     * @return String com os dados do aluno separados por ponto e vírgula.
     */
    public String toCSV() {
        return numero + SEPARADOR_CSV +
               nome + SEPARADOR_CSV +
               curso + SEPARADOR_CSV +
               String.format("%.1f", media);
    }

    /**
     * Cria um objeto Aluno a partir de uma linha CSV.
     * Método estático de fábrica (factory method).
     *
     * @param linhaCSV Linha no formato: numero;nome;curso;media
     * @return Novo objeto Aluno, ou null se a linha for inválida.
     */
    public static Aluno fromCSV(String linhaCSV) {
        // Ignorar linhas vazias ou cabeçalho
        if (linhaCSV == null || linhaCSV.trim().isEmpty()) {
            return null;
        }

        // Ignorar linha de cabeçalho
        if (linhaCSV.toLowerCase().startsWith("numero")) {
            return null;
        }

        try {
            // Separar a linha pelos separadores
            String[] campos = linhaCSV.split(SEPARADOR_CSV);

            // Verificar se tem todos os campos
            if (campos.length < 4) {
                System.out.println("AVISO: Linha inválida (campos insuficientes): " + linhaCSV);
                return null;
            }

            // Extrair os valores
            int numero = Integer.parseInt(campos[0].trim());
            String nome = campos[1].trim();
            String curso = campos[2].trim();
            double media = Double.parseDouble(campos[3].trim().replace(",", "."));

            // Criar e retornar o aluno
            return new Aluno(numero, nome, curso, media);

        } catch (NumberFormatException e) {
            System.out.println("AVISO: Erro ao converter valores numéricos: " + linhaCSV);
            return null;
        }
    }

    /**
     * Retorna o cabeçalho do ficheiro CSV.
     * Método estático utilitário.
     *
     * @return String com os nomes das colunas.
     */
    public static String getCabecalhoCSV() {
        return "numero" + SEPARADOR_CSV +
               "nome" + SEPARADOR_CSV +
               "curso" + SEPARADOR_CSV +
               "media";
    }

    // ========== MÉTODOS DE VALIDAÇÃO ==========

    /**
     * Verifica se o aluno está aprovado (média >= 10).
     *
     * @return true se aprovado, false se reprovado.
     */
    public boolean estaAprovado() {
        return media >= 10.0;
    }

    /**
     * Retorna a classificação qualitativa do aluno.
     *
     * @return String com a classificação (Insuficiente, Suficiente, Bom, Muito Bom, Excelente).
     */
    public String getClassificacao() {
        if (media < 10) return "Insuficiente";
        if (media < 14) return "Suficiente";
        if (media < 16) return "Bom";
        if (media < 18) return "Muito Bom";
        return "Excelente";
    }

    // ========== GETTERS ==========
    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public double getMedia() {
        return media;
    }

    // ========== SETTERS ==========
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setMedia(double media) {
        if (media >= 0 && media <= 20) {
            this.media = media;
        }
    }

    // ========== TOSTRING ==========
    @Override
    public String toString() {
        return "Aluno " + numero + ": " + nome +
               " - Curso: " + curso +
               " - Média: " + String.format("%.1f", media) +
               " (" + getClassificacao() + ")";
    }
}
