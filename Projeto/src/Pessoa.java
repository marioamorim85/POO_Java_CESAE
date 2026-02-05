/**
 * Classe Abstracta Pessoa
 * 
 * Representa uma pessoa genérica no sistema académico.
 * Serve como base para as classes Aluno e Professor.
 * 
 * @author [O TEU NOME]
 * @version 1.0
 */
public abstract class Pessoa {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Nome completo da pessoa */
    private String nome;
    /** Endereço de email */
    private String email;
    /** Número de telefone */
    private String telefone;
    /** Idade da pessoa */
    private int idade;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo da classe Pessoa.
     * 
     * @param nome     Nome completo da pessoa.
     * @param email    Endereço de email (deve conter '@').
     * @param telefone Número de telefone.
     * @param idade    Idade da pessoa (deve ser >= 0).
     */
    public Pessoa(String nome, String email, String telefone, int idade) {
        this.nome = nome;
        setEmail(email); // Usa setter para validação
        this.telefone = telefone;
        setIdade(idade); // Usa setter para validação
    }

    /**
     * Construtor simplificado da classe Pessoa (sobrecarga).
     * Útil quando o telefone é opcional/desconhecido.
     * 
     * @param nome  Nome completo da pessoa.
     * @param email Endereço de email.
     * @param idade Idade da pessoa.
     */
    public Pessoa(String nome, String email, int idade) {
        // Chama o construtor completo usando this()
        // O "" é uma string vazia - significa que o telefone fica vazio
        // Isto evita ter de passar null ou um valor placeholder
        this(nome, email, "", idade);
    }

    // ========== MÉTODO ABSTRACTO ==========
    /**
     * Método abstracto que deve ser implementado nas subclasses.
     * Apresenta os detalhes específicos de cada tipo de pessoa.
     */
    public abstract void mostrarDetalhes();

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém o nome da pessoa.
     * 
     * @return O nome completo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o email da pessoa.
     * 
     * @return O endereço de email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém o telefone da pessoa.
     * 
     * @return O número de telefone.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Obtém a idade da pessoa.
     * 
     * @return A idade.
     */
    public int getIdade() {
        return idade;
    }

    // ========== MÉTODOS SETTERS (COM VALIDAÇÃO) ==========
    /**
     * Define o nome da pessoa.
     * 
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Erro: Nome não pode ser vazio.");
        }
    }

    /**
     * Define o email da pessoa.
     * Valida se o email contém '@'.
     * 
     * @param email Novo email.
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Erro: Email inválido (deve conter '@').");
            this.email = "invalido@email.com";
        }
    }

    /**
     * Define o telefone da pessoa.
     * 
     * @param telefone Novo telefone.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Define a idade da pessoa.
     * Valida se a idade é >= 0.
     * 
     * @param idade Nova idade.
     */
    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        } else {
            System.out.println("Erro: Idade não pode ser negativa.");
            this.idade = 0;
        }
    }

    // ========== MÉTODO TOSTRING ==========
    /**
     * Retorna uma representação textual da pessoa.
     * 
     * @return String formatada com os dados da pessoa.
     */
    @Override
    public String toString() {
        return "Nome: " + nome + " | Email: " + email + " | Telefone: " + telefone + " | Idade: " + idade;
    }
}
