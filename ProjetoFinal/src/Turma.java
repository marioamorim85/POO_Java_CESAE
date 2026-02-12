import java.util.ArrayList;

/**
 * Classe Turma
 * 
 * Representa uma turma de alunos associada a um curso.
 * Gere a lista de alunos inscritos e as UCs da turma.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class Turma {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Nome da turma (ex: SD-2025-A) */
    private String nome;
    /** Capacidade máxima de alunos */
    private int capacidadeMaxima;
    /** Lista de alunos inscritos */
    private ArrayList<Aluno> alunos;
    /** Lista de UCs da turma */
    private ArrayList<UnidadeCurricular> unidadesCurriculares;
    /** Curso ao qual a turma pertence */
    private Curso curso;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo da Turma.
     * 
     * @param nome             Nome da turma.
     * @param capacidadeMaxima Número máximo de alunos.
     * @param curso            Curso associado.
     */
    public Turma(String nome, int capacidadeMaxima, Curso curso) {
        this.nome = nome;
        if (capacidadeMaxima > 0) {
            this.capacidadeMaxima = capacidadeMaxima;
        } else {
            System.out.println("Erro: Capacidade deve ser maior que 0.");
            this.capacidadeMaxima = 1;
        }
        this.alunos = new ArrayList<>();
        this.unidadesCurriculares = new ArrayList<>();
        this.curso = curso;
    }

    /**
     * Construtor sem curso (sobrecarga).
     * 
     * @param nome             Nome da turma.
     * @param capacidadeMaxima Número máximo de alunos.
     */
    public Turma(String nome, int capacidadeMaxima) {
        this(nome, capacidadeMaxima, null);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o nome da turma.
     * 
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a capacidade máxima.
     * 
     * @return O número máximo de alunos.
     */
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    /**
     * Obtém a lista de alunos.
     * 
     * @return ArrayList de alunos.
     */
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * Obtém a lista de UCs.
     * 
     * @return ArrayList de UCs.
     */
    public ArrayList<UnidadeCurricular> getUnidadesCurriculares() {
        return unidadesCurriculares;
    }

    /**
     * Obtém o curso associado.
     * 
     * @return O curso.
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Obtém o número atual de alunos inscritos.
     * 
     * @return O número de alunos.
     */
    public int getNumeroAlunos() {
        return alunos.size();
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o nome da turma.
     * 
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a capacidade máxima.
     * Valida se é maior que 0.
     * 
     * @param capacidadeMaxima Nova capacidade.
     */
    public void setCapacidadeMaxima(int capacidadeMaxima) {
        if (capacidadeMaxima > 0) {
            this.capacidadeMaxima = capacidadeMaxima;
        } else {
            System.out.println("Erro: Capacidade deve ser maior que 0.");
            this.capacidadeMaxima = 1;
        }
    }

    /**
     * Define o curso associado.
     * 
     * @param curso Novo curso.
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // ========== MÉTODOS DE FUNCIONAMENTO ==========
    /**
     * Inscreve um aluno na turma.
     * Valida capacidade máxima e duplicados.
     * 
     * @param aluno Aluno a inscrever.
     * @return true se inscreveu com sucesso, false caso contrário.
     */
    public boolean inscreverAluno(Aluno aluno) {
        if (aluno == null) {
            System.out.println("Erro: Aluno inválido.");
            return false;
        }
        if (alunos.size() >= capacidadeMaxima) {
            System.out.println("Erro: Turma " + nome + " já atingiu a capacidade máxima (" + capacidadeMaxima + ").");
            return false;
        }
        if (alunos.contains(aluno)) {
            System.out.println("Erro: Aluno já está inscrito nesta turma.");
            return false;
        }
        alunos.add(aluno);
        aluno.setTurma(this);
        System.out.println("Aluno " + aluno.getNome() + " inscrito na turma " + nome);
        return true;
    }

    /**
     * Remove um aluno da turma.
     * 
     * @param aluno Aluno a remover.
     * @return true se removeu com sucesso, false caso contrário.
     */
    public boolean removerAluno(Aluno aluno) {
        if (alunos.remove(aluno)) {
            aluno.setTurma(null);
            System.out.println("Aluno " + aluno.getNome() + " removido da turma " + nome);
            return true;
        } else {
            System.out.println("Erro: Aluno não encontrado na turma.");
            return false;
        }
    }

    /**
     * Lista todos os alunos da turma.
     */
    public void listarAlunos() {
        System.out.println("=== Alunos da Turma " + nome + " (" + alunos.size() + "/" + capacidadeMaxima + ") ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno inscrito.");
        } else {
            for (int i = 0; i < alunos.size(); i++) {
                System.out.println((i + 1) + ". " + alunos.get(i));
            }
        }
    }

    /**
     * Adiciona uma UC à turma.
     * 
     * @param uc Unidade Curricular a adicionar.
     * @return true se adicionou com sucesso, false caso contrário.
     */
    public boolean adicionarUC(UnidadeCurricular uc) {
        if (uc == null) {
            System.out.println("Erro: UC inválida.");
            return false;
        }
        if (unidadesCurriculares.contains(uc)) {
            System.out.println("Erro: UC já existe nesta turma.");
            return false;
        }
        unidadesCurriculares.add(uc);
        System.out.println("UC '" + uc.getNome() + "' adicionada à turma " + nome);
        return true;
    }

    /**
     * Remove uma UC da turma.
     * 
     * @param uc Unidade Curricular a remover.
     * @return true se removeu com sucesso.
     */
    public boolean removerUC(UnidadeCurricular uc) {
        if (unidadesCurriculares.remove(uc)) {
            System.out.println("UC '" + uc.getNome() + "' removida da turma " + nome);
            return true;
        }
        System.out.println("Erro: UC não encontrada.");
        return false;
    }

    /**
     * Calcula a média das notas de todos os alunos da turma.
     * 
     * @return A média geral da turma.
     */
    public double calcularMediaTurma() {
        if (alunos.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        int count = 0;
        for (Aluno aluno : alunos) {
            double media = aluno.calcularMedia();
            if (media > 0) {
                soma += media;
                count++;
            }
        }
        return (count > 0) ? soma / count : 0.0;
    }

    /**
     * Calcula a taxa de ocupação da turma.
     * 
     * @return Percentagem de ocupação (0-100).
     */
    public double getTaxaOcupacao() {
        return (double) alunos.size() / capacidadeMaxima * 100;
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual da turma.
     * 
     * @return String formatada.
     */
    @Override
    public String toString() {
        String cursoNome = (curso != null) ? curso.getNome() : "Sem curso";
        return nome + " | Alunos: " + alunos.size() + "/" + capacidadeMaxima +
                " | Curso: " + cursoNome;
    }
}
