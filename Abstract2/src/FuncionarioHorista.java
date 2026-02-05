/**
 * Classe FuncionarioHorista - Subclasse de Funcionario
 * 
 * Representa um funcionário que recebe por hora trabalhada.
 * O salário é calculado multiplicando as horas pelo valor/hora.
 * Horas acima de 160 são consideradas extras e pagam 1.5× mais.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class FuncionarioHorista extends Funcionario {
    // ========== CONSTANTES ==========
    /** Número de horas normais mensais (40h/semana × 4 semanas) */
    private static final int HORAS_NORMAIS = 160;
    /** Multiplicador para horas extra */
    private static final double MULTIPLICADOR_EXTRA = 1.5;

    // ========== ATRIBUTOS PRIVADOS ==========
    /** Número de horas trabalhadas no mês */
    private int horasTrabalhadas;
    /** Valor pago por cada hora normal */
    private double valorHora;

    // ========== CONSTRUTORES ==========
    /**
     * Construtor completo do FuncionarioHorista.
     * 
     * @param nome            Nome completo.
     * @param email           Endereço de email.
     * @param dataAdmissao    Data de admissão.
     * @param horasTrabalhadas Horas trabalhadas no mês.
     * @param valorHora       Valor pago por hora.
     */
    public FuncionarioHorista(String nome, String email, String dataAdmissao,
                              int horasTrabalhadas, double valorHora) {
        super(nome, email, dataAdmissao); // Chama construtor da superclasse
        this.horasTrabalhadas = horasTrabalhadas >= 0 ? horasTrabalhadas : 0;
        this.valorHora = valorHora >= 0 ? valorHora : 0;
    }

    // ========== MÉTODOS GETTERS ==========
    /**
     * Obtém as horas trabalhadas.
     * 
     * @return Número de horas.
     */
    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    /**
     * Obtém o valor por hora.
     * 
     * @return Valor em euros.
     */
    public double getValorHora() {
        return valorHora;
    }

    /**
     * Calcula as horas normais trabalhadas.
     * 
     * @return Horas normais (máximo 160).
     */
    public int getHorasNormais() {
        return Math.min(horasTrabalhadas, HORAS_NORMAIS);
    }

    /**
     * Calcula as horas extra trabalhadas.
     * 
     * @return Horas acima de 160 (ou 0).
     */
    public int getHorasExtra() {
        return Math.max(0, horasTrabalhadas - HORAS_NORMAIS);
    }

    // ========== MÉTODOS SETTERS ==========
    /**
     * Define as horas trabalhadas.
     * Valida se o valor não é negativo.
     * 
     * @param horasTrabalhadas Número de horas.
     */
    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas >= 0) {
            this.horasTrabalhadas = horasTrabalhadas;
        } else {
            System.out.println("Erro: Horas não podem ser negativas.");
            this.horasTrabalhadas = 0;
        }
    }

    /**
     * Define o valor por hora.
     * Valida se o valor não é negativo.
     * 
     * @param valorHora Valor em euros.
     */
    public void setValorHora(double valorHora) {
        if (valorHora >= 0) {
            this.valorHora = valorHora;
        } else {
            System.out.println("Erro: Valor/hora não pode ser negativo.");
            this.valorHora = 0;
        }
    }

    // ========== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS ==========
    /**
     * Calcula o salário do funcionário horista.
     * 
     * Fórmula:
     * - Até 160 horas: horas × valorHora
     * - Acima de 160: horas extra × valorHora × 1.5
     * 
     * @return O salário total calculado.
     */
    @Override
    public double calcularSalario() {
        double salarioNormal = getHorasNormais() * valorHora;
        double salarioExtra = getHorasExtra() * valorHora * MULTIPLICADOR_EXTRA;
        return salarioNormal + salarioExtra;
    }

    /**
     * Retorna o tipo de funcionário.
     * 
     * @return "Horista".
     */
    @Override
    public String getTipo() {
        return "Horista";
    }

    /**
     * Mostra os detalhes completos do funcionário horista.
     */
    @Override
    public void mostrarDetalhes() {
        mostrarInfoBase(); // Chama método da superclasse
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  DETALHES HORISTA:");
        System.out.println("  Horas Trabalhadas: " + horasTrabalhadas);
        System.out.println("    - Horas Normais: " + getHorasNormais());
        System.out.println("    - Horas Extra: " + getHorasExtra());
        System.out.println("  Valor/Hora: " + String.format("%.2f EUR", valorHora));
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("  CÁLCULO DO SALÁRIO:");
        double salarioNormal = getHorasNormais() * valorHora;
        double salarioExtra = getHorasExtra() * valorHora * MULTIPLICADOR_EXTRA;
        System.out.println("    Normal: " + getHorasNormais() + "h x " + 
                          String.format("%.2f EUR", valorHora) + " = " + 
                          String.format("%.2f EUR", salarioNormal));
        if (getHorasExtra() > 0) {
            System.out.println("    Extra:  " + getHorasExtra() + "h x " + 
                              String.format("%.2f EUR", valorHora) + " x 1.5 = " + 
                              String.format("%.2f EUR", salarioExtra));
        }
        System.out.println("  SALARIO TOTAL: " + String.format("%.2f EUR", calcularSalario()));
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
