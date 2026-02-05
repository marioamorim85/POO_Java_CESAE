/**
 * Classe Abstrata Funcionario
 * 
 * Representa um funcionário genérico da empresa.
 * É abstrata porque não faz sentido criar um "funcionário genérico" -
 * todos os funcionários devem ser de um tipo específico.
 * 
 * Define a estrutura comum a todos os tipos de funcionários
 * e obriga as subclasses a implementar o cálculo do salário.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public abstract class Funcionario {
    // ========== CONSTANTE STATIC (CONTADOR) ==========
    /** Contador estático para geração automática do número de funcionário */
    private static int contadorFuncionarios = 1000;

    // ========== ATRIBUTOS PRIVADOS ==========
    /** Número de identificação único do funcionário */
    private final int numeroFuncionario;
    /** Nome completo do funcionário */
    private String nome;
    /** Endereço de email */
    private String email;
    /** Data de admissão no formato dd/mm/aaaa */
    private String dataAdmissao;

    // ========== CONSTRUTOR ==========
    /**
     * Construtor da classe Funcionario.
     * O número de funcionário é gerado automaticamente.
     * 
     * @param nome         Nome completo do funcionário.
     * @param email        Endereço de email.
     * @param dataAdmissao Data de admissão (dd/mm/aaaa).
     */
    public Funcionario(String nome, String email, String dataAdmissao) {
        this.numeroFuncionario = ++contadorFuncionarios;
        this.nome = nome;
        this.email = email;
        this.dataAdmissao = dataAdmissao;
    }

    // ========== MÉTODOS ABSTRATOS ==========
    /**
     * Calcula o salário do funcionário.
     * Cada tipo de funcionário implementa este método de forma diferente.
     * 
     * @return O valor do salário calculado.
     */
    public abstract double calcularSalario();

    /**
     * Mostra os detalhes completos do funcionário.
     * Cada tipo de funcionário mostra informação específica.
     */
    public abstract void mostrarDetalhes();

    /**
     * Retorna o tipo de funcionário.
     * 
     * @return String com o tipo (ex: "Horista", "Mensalista").
     */
    public abstract String getTipo();

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o número de funcionário.
     * 
     * @return O número de identificação.
     */
    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    /**
     * Obtém o nome do funcionário.
     * 
     * @return O nome completo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o email do funcionário.
     * 
     * @return O endereço de email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém a data de admissão.
     * 
     * @return A data no formato dd/mm/aaaa.
     */
    public String getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * Obtém o valor atual do contador (static).
     * 
     * @return O último número atribuído.
     */
    public static int getContadorFuncionarios() {
        return contadorFuncionarios;
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define o nome do funcionário.
     * 
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o email do funcionário.
     * 
     * @param email Novo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Define a data de admissão.
     * 
     * @param dataAdmissao Nova data (dd/mm/aaaa).
     */
    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    // ========== MÉTODOS CONCRETOS ==========
    /**
     * Representação textual básica do funcionário.
     * Este método é concreto (tem implementação) e pode ser
     * usado por todas as subclasses.
     * 
     * @return String com informação básica.
     */
    @Override
    public String toString() {
        return "Funcionário #" + numeroFuncionario + " - " + nome + " (" + getTipo() + ")";
    }

    /**
     * Mostra a informação base comum a todos os funcionários.
     * As subclasses podem chamar este método antes de mostrar
     * a sua informação específica.
     */
    protected void mostrarInfoBase() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║           DADOS DO FUNCIONÁRIO               ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  Número: " + numeroFuncionario);
        System.out.println("  Nome: " + nome);
        System.out.println("  Email: " + email);
        System.out.println("  Data Admissão: " + dataAdmissao);
        System.out.println("  Tipo: " + getTipo());
    }
}
