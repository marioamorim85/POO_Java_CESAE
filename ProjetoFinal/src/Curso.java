import java.util.ArrayList;

/**
 * Classe Curso
 * 
 * Representa um curso do CESAE Digital.
 * Agrega turmas e unidades curriculares.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Curso {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Nome do curso */
    private String nome;
    /** Tipo de curso (enum) */
    private TipoCurso tipo;
    /** Duração total em meses */
    private int duracaoMeses;
    /** Lista de turmas do curso */
    private ArrayList<Turma> turmas;
    /** Lista de UCs do plano curricular */
    private ArrayList<UnidadeCurricular> unidadesCurriculares;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do Curso.
     * 
     * @param nome         Nome do curso.
     * @param tipo         Tipo de curso.
     * @param duracaoMeses Duração em meses.
     */
    public Curso(String nome, TipoCurso tipo, int duracaoMeses) {
        this.nome = nome;
        this.tipo = tipo;
        this.duracaoMeses = duracaoMeses > 0 ? duracaoMeses : 1;
        this.turmas = new ArrayList<>();
        this.unidadesCurriculares = new ArrayList<>();
    }

    /**
     * Construtor simplificado (tipo FORMACAO por defeito).
     * 
     * @param nome         Nome do curso.
     * @param duracaoMeses Duração em meses.
     */
    public Curso(String nome, int duracaoMeses) {
        this(nome, TipoCurso.FORMACAO, duracaoMeses);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o nome do curso.
     * 
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o tipo de curso.
     * 
     * @return O tipo (enum).
     */
    public TipoCurso getTipo() {
        return tipo;
    }

    /**
     * Obtém a duração em meses.
     * 
     * @return A duração.
     */
    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    /**
     * Obtém a lista de turmas.
     * 
     * @return ArrayList de turmas.
     */
    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    /**
     * Obtém a lista de UCs.
     * 
     * @return ArrayList de UCs.
     */
    public ArrayList<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o nome do curso.
     * 
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o tipo de curso.
     * 
     * @param tipo Novo tipo.
     */
    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }

    /**
     * Define a duração em meses.
     * Valida se é maior que 0.
     * 
     * @param duracaoMeses Nova duração.
     */
    public void setDuracaoMeses(int duracaoMeses) {
        if (duracaoMeses > 0) {
            this.duracaoMeses = duracaoMeses;
        } else {
            System.out.println("Erro: Duração deve ser maior que 0.");
            this.duracaoMeses = 1;
        }
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Adiciona uma turma ao curso.
     * 
     * @param turma Turma a adicionar.
     * @return true se adicionou com sucesso.
     */
    public boolean adicionarTurma(Turma turma) {
        if (turma == null) {
            System.out.println("Erro: Turma inválida.");
            return false;
        }
        if (turmas.contains(turma)) {
            System.out.println("Erro: Turma já existe neste curso.");
            return false;
        }
        turmas.add(turma);
        turma.setCurso(this);
        System.out.println("Turma '" + turma.getNome() + "' adicionada ao curso " + nome);
        return true;
    }

    /**
     * Remove uma turma do curso.
     * 
     * @param turma Turma a remover.
     * @return true se removeu com sucesso.
     */
    public boolean removerTurma(Turma turma) {
        if (turmas.remove(turma)) {
            turma.setCurso(null);
            System.out.println("Turma '" + turma.getNome() + "' removida do curso " + nome);
            return true;
        }
        System.out.println("Erro: Turma não encontrada.");
        return false;
    }

    /**
     * Adiciona uma UC ao plano curricular.
     * 
     * @param uc Unidade Curricular a adicionar.
     * @return true se adicionou com sucesso.
     */
    public boolean adicionarUC(UnidadeCurricular uc) {
        if (uc == null) {
            System.out.println("Erro: UC inválida.");
            return false;
        }
        if (unidadesCurriculares.contains(uc)) {
            System.out.println("Erro: UC já existe neste curso.");
            return false;
        }
        unidadesCurriculares.add(uc);
        System.out.println("UC '" + uc.getNome() + "' adicionada ao curso " + nome);
        return true;
    }

    /**
     * Lista todas as turmas do curso.
     */
    public void listarTurmas() {
        System.out.println("=== Turmas do Curso " + nome + " ===");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma registada.");
        } else {
            for (int i = 0; i < turmas.size(); i++) {
                System.out.println((i + 1) + ". " + turmas.get(i));
            }
        }
    }

    /**
     * Mostra os detalhes completos do curso.
     */
    public void mostrarDetalhes() {
        System.out.println("============ DETALHES DO CURSO ============");
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Duração: " + duracaoMeses + " meses");
        System.out.println("Número de Turmas: " + turmas.size());
        System.out.println("Número de UCs: " + unidadesCurriculares.size());
        System.out.println("============================================");
    }

    /**
     * Obtém o número total de alunos em todas as turmas.
     * 
     * @return Total de alunos.
     */
    public int getTotalAlunos() {
        int total = 0;
        for (Turma turma : turmas) {
            total += turma.getNumeroAlunos();
        }
        return total;
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual do curso.
     * 
     * @return String formatada.
     */
    @Override
    public String toString() {
        return nome + " | Tipo: " + tipo + " | " + duracaoMeses + " meses | Turmas: " + turmas.size();
    }
}
