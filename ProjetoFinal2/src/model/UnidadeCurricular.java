package model;

/**
 * Classe UnidadeCurricular
 * 
 * Representa uma unidade curricular (disciplina) do plano de estudos.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class UnidadeCurricular {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Nome da unidade curricular */
    private String nome;
    /** Código identificador (ex: POO-101) */
    private String codigo;
    /** Carga horária total em horas */
    private int cargaHoraria;
    /** Professor responsável pela UC */
    private Professor professor;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo da UnidadeCurricular.
     * 
     * @param nome         Nome da UC.
     * @param codigo       Código identificador.
     * @param cargaHoraria Número total de horas.
     * @param professor    Professor responsável (pode ser null).
     */
    public UnidadeCurricular(String nome, String codigo, int cargaHoraria, Professor professor) {
        this.nome = nome;
        this.codigo = codigo;
        if (cargaHoraria > 0) {
            this.cargaHoraria = cargaHoraria;
        } else {
            System.out.println("Erro: Carga horária deve ser maior que 0.");
            this.cargaHoraria = 1;
        }
        this.professor = professor;
    }

    /**
     * Construtor sem professor (sobrecarga).
     * 
     * @param nome         Nome da UC.
     * @param codigo       Código identificador.
     * @param cargaHoraria Número total de horas.
     */
    public UnidadeCurricular(String nome, String codigo, int cargaHoraria) {
        this(nome, codigo, cargaHoraria, null);
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o nome da UC.
     * 
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o código da UC.
     * 
     * @return O código.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtém a carga horária.
     * 
     * @return O número de horas.
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * Obtém o professor responsável.
     * 
     * @return O professor ou null.
     */
    public Professor getProfessor() {
        return professor;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o nome da UC.
     * 
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o código da UC.
     * 
     * @param codigo Novo código.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Define a carga horária.
     * Valida se é maior que 0.
     * 
     * @param cargaHoraria Nova carga horária.
     */
    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria > 0) {
            this.cargaHoraria = cargaHoraria;
        } else {
            System.out.println("Erro: Carga horária deve ser maior que 0.");
            this.cargaHoraria = 1;
        }
    }

    /**
     * Define o professor responsável.
     * Também adiciona esta UC ao professor (se não atingiu o limite).
     * 
     * @param professor Novo professor.
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual da UC.
     * 
     * @return String formatada.
     */
    @Override
    public String toString() {
        String profNome = (professor != null) ? professor.getNome() : "Sem professor";
        return codigo + " - " + nome + " | " + cargaHoraria + "h | Prof: " + profNome;
    }
}
