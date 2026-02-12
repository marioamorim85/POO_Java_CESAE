package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de servico que gere toda a logica de negocio
 * do Sistema CESAE Digital.
 * 
 * Padrao Singleton para garantir uma unica instancia.
 * 
 * @author Mario Amorim
 * @version 2.0 - JavaFX
 */
public class CesaeDigital {

    // Instancia unica (Singleton)
    private static CesaeDigital instance;
    
    // Listas de dados
    private final List<Aluno> alunos;
    private final List<Professor> professores;
    private final List<Curso> cursos;
    private final List<Turma> turmas;
    private final List<UnidadeCurricular> unidadesCurriculares;
    
    /**
     * Construtor privado (Singleton)
     */
    private CesaeDigital() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.unidadesCurriculares = new ArrayList<>();
        
        // Carregar dados de exemplo
        carregarDadosExemplo();
    }
    
    /**
     * Obter a instancia unica do CesaeDigital
     * 
     * @return Instancia do CesaeDigital
     */
    public static CesaeDigital getInstance() {
        if (instance == null) {
            instance = new CesaeDigital();
        }
        return instance;
    }
    
    // ============== METODOS PARA ALUNOS ==============
    
    public void adicionarAluno(Aluno aluno) {
        if (aluno != null && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }
    
    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }
    
    public Aluno buscarAlunoPorNome(String nome) {
        return alunos.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
    
    public List<Aluno> listarAlunosPorEstado(EstadoAluno estado) {
        return alunos.stream()
                .filter(a -> a.getEstado() == estado)
                .collect(Collectors.toList());
    }
    
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }
    
    public int getTotalAlunos() {
        return alunos.size();
    }
    
    public int getAlunosAtivos() {
        return (int) alunos.stream()
                .filter(a -> a.getEstado() == EstadoAluno.ATIVO)
                .count();
    }
    
    // ============== METODOS PARA PROFESSORES ==============
    
    public void adicionarProfessor(Professor professor) {
        if (professor != null && !professores.contains(professor)) {
            professores.add(professor);
        }
    }
    
    public void removerProfessor(Professor professor) {
        professores.remove(professor);
    }
    
    public Professor buscarProfessorPorNome(String nome) {
        return professores.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
    
    public List<Professor> getProfessores() {
        return new ArrayList<>(professores);
    }
    
    public int getTotalProfessores() {
        return professores.size();
    }
    
    // ============== METODOS PARA CURSOS ==============
    
    public void adicionarCurso(Curso curso) {
        if (curso != null && !cursos.contains(curso)) {
            cursos.add(curso);
        }
    }
    
    public void removerCurso(Curso curso) {
        cursos.remove(curso);
    }
    
    public Curso buscarCursoPorNome(String nome) {
        return cursos.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
    
    public List<Curso> listarCursosPorTipo(TipoCurso tipo) {
        return cursos.stream()
                .filter(c -> c.getTipo() == tipo)
                .collect(Collectors.toList());
    }
    
    public List<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }
    
    public int getTotalCursos() {
        return cursos.size();
    }
    
    // ============== METODOS PARA TURMAS ==============
    
    public void adicionarTurma(Turma turma) {
        if (turma != null && !turmas.contains(turma)) {
            turmas.add(turma);
        }
    }
    
    public void removerTurma(Turma turma) {
        turmas.remove(turma);
    }
    
    public Turma buscarTurmaPorNome(String nome) {
        return turmas.stream()
                .filter(t -> t.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
    
    public List<Turma> getTurmas() {
        return new ArrayList<>(turmas);
    }
    
    public int getTotalTurmas() {
        return turmas.size();
    }
    
    // ============== METODOS PARA UNIDADES CURRICULARES ==============
    
    public void adicionarUnidadeCurricular(UnidadeCurricular uc) {
        if (uc != null && !unidadesCurriculares.contains(uc)) {
            unidadesCurriculares.add(uc);
        }
    }
    
    public void removerUnidadeCurricular(UnidadeCurricular uc) {
        unidadesCurriculares.remove(uc);
    }
    
    public List<UnidadeCurricular> getUnidadesCurriculares() {
        return new ArrayList<>(unidadesCurriculares);
    }
    
    public int getTotalUnidadesCurriculares() {
        return unidadesCurriculares.size();
    }
    
    // ============== DADOS DE EXEMPLO ==============
    
    /**
     * Carrega dados de exemplo para demonstracao
     */
    private void carregarDadosExemplo() {
        // Criar Professores
        Professor prof1 = new Professor("Mario Amorim", "mario@cesae.pt", "912345678", 40, "Desenvolvimento Web", 2500.0);
        Professor prof2 = new Professor("Ana Silva", "ana@cesae.pt", "923456789", 35, "Bases de Dados", 2400.0);
        Professor prof3 = new Professor("Carlos Santos", "carlos@cesae.pt", "934567890", 45, "Programacao", 2300.0);
        
        adicionarProfessor(prof1);
        adicionarProfessor(prof2);
        adicionarProfessor(prof3);
        
        // Criar Unidades Curriculares
        UnidadeCurricular uc1 = new UnidadeCurricular("Programacao Orientada a Objetos", "POO-101", 50, prof3);
        UnidadeCurricular uc2 = new UnidadeCurricular("Desenvolvimento Web", "DW-102", 75, prof1);
        UnidadeCurricular uc3 = new UnidadeCurricular("Bases de Dados", "BD-103", 50, prof2);
        UnidadeCurricular uc4 = new UnidadeCurricular("JavaScript", "JS-104", 40, prof1);
        
        adicionarUnidadeCurricular(uc1);
        adicionarUnidadeCurricular(uc2);
        adicionarUnidadeCurricular(uc3);
        adicionarUnidadeCurricular(uc4);
        
        // Associar UCs aos professores
        prof1.adicionarUC(uc2);
        prof1.adicionarUC(uc4);
        prof2.adicionarUC(uc3);
        prof3.adicionarUC(uc1);
        
        // Criar Cursos
        Curso curso1 = new Curso("Full Stack Developer", TipoCurso.FORMACAO, 12);
        curso1.adicionarUC(uc1);
        curso1.adicionarUC(uc2);
        curso1.adicionarUC(uc3);
        curso1.adicionarUC(uc4);
        
        Curso curso2 = new Curso("Web Development Essentials", TipoCurso.WORKSHOP, 6);
        curso2.adicionarUC(uc2);
        curso2.adicionarUC(uc4);
        
        adicionarCurso(curso1);
        adicionarCurso(curso2);
        
        // Criar Turmas
        Turma turma1 = new Turma("FSD-2024-A", 20, curso1);
        Turma turma2 = new Turma("FSD-2024-B", 20, curso1);
        Turma turma3 = new Turma("WEB-2024", 15, curso2);
        
        adicionarTurma(turma1);
        adicionarTurma(turma2);
        adicionarTurma(turma3);
        
        // Adicionar turmas aos cursos
        curso1.adicionarTurma(turma1);
        curso1.adicionarTurma(turma2);
        curso2.adicionarTurma(turma3);
        
        // Criar Alunos
        Aluno aluno1 = new Aluno("Joao Silva", "joao@email.com", "911111111", 22, turma1);
        aluno1.setEstado(EstadoAluno.ATIVO);
        
        Aluno aluno2 = new Aluno("Maria Santos", "maria@email.com", "922222222", 24, turma1);
        aluno2.setEstado(EstadoAluno.ATIVO);
        
        Aluno aluno3 = new Aluno("Pedro Costa", "pedro@email.com", "933333333", 21, turma1);
        aluno3.setEstado(EstadoAluno.ATIVO);
        
        Aluno aluno4 = new Aluno("Ana Oliveira", "ana@email.com", "944444444", 23, turma2);
        aluno4.setEstado(EstadoAluno.ATIVO);
        
        Aluno aluno5 = new Aluno("Rui Ferreira", "rui@email.com", "955555555", 25, turma2);
        aluno5.setEstado(EstadoAluno.DESISTENTE);
        
        Aluno aluno6 = new Aluno("Sofia Martins", "sofia@email.com", "966666666", 20, turma3);
        aluno6.setEstado(EstadoAluno.ATIVO);
        
        Aluno aluno7 = new Aluno("Tiago Rocha", "tiago@email.com", "977777777", 26, turma3);
        aluno7.setEstado(EstadoAluno.CONCLUIDO);
        
        adicionarAluno(aluno1);
        adicionarAluno(aluno2);
        adicionarAluno(aluno3);
        adicionarAluno(aluno4);
        adicionarAluno(aluno5);
        adicionarAluno(aluno6);
        adicionarAluno(aluno7);
        
        // Inscrever alunos nas turmas
        turma1.inscreverAluno(aluno1);
        turma1.inscreverAluno(aluno2);
        turma1.inscreverAluno(aluno3);
        turma2.inscreverAluno(aluno4);
        turma2.inscreverAluno(aluno5);
        turma3.inscreverAluno(aluno6);
        turma3.inscreverAluno(aluno7);
        
        // Adicionar notas aos alunos
        aluno1.adicionarNota(15.5);
        aluno1.adicionarNota(17.0);
        aluno2.adicionarNota(18.0);
        aluno2.adicionarNota(16.5);
        aluno3.adicionarNota(12.0);
        aluno3.adicionarNota(14.5);
        aluno4.adicionarNota(19.0);
        aluno5.adicionarNota(10.0);
        aluno6.adicionarNota(16.0);
        aluno7.adicionarNota(17.5);
    }
    
    /**
     * Apresenta um resumo do sistema
     * 
     * @return String com resumo estatistico
     */
    public String getResumo() {
        return String.format(
            """
            === CESAE Digital - Resumo ===
            Total de Alunos: %d (Ativos: %d)
            Total de Professores: %d
            Total de Cursos: %d
            Total de Turmas: %d
            Total de UCs: %d""",
            getTotalAlunos(), getAlunosAtivos(),
            getTotalProfessores(),
            getTotalCursos(),
            getTotalTurmas(),
            getTotalUnidadesCurriculares()
        );
    }
}
