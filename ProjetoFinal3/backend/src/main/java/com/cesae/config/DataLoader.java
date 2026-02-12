package com.cesae.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cesae.entity.Aluno;
import com.cesae.entity.Curso;
import com.cesae.entity.EstadoAluno;
import com.cesae.entity.Professor;
import com.cesae.entity.TipoCurso;
import com.cesae.entity.Turma;
import com.cesae.entity.UnidadeCurricular;
import com.cesae.repository.AlunoRepository;
import com.cesae.repository.CursoRepository;
import com.cesae.repository.ProfessorRepository;
import com.cesae.repository.TurmaRepository;
import com.cesae.repository.UnidadeCurricularRepository;

/**
 * Carrega dados de teste iniciais na base de dados.
 * Executado automaticamente ao iniciar a aplicação.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UnidadeCurricularRepository ucRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void run(String... args) {
        // Só carrega dados se a base estiver vazia
        if (professorRepository.count() > 0) {
            System.out.println("Dados já existem na base de dados.");
            return;
        }

        System.out.println("Carregando dados de teste...");

        // ========== PROFESSORES ==========
        Professor prof1 = new Professor("Ana Silva", "ana.silva@cesae.pt", "912345678", 35, "Java/POO", 2500);
        Professor prof2 = new Professor("Carlos Santos", "carlos.santos@cesae.pt", "923456789", 42, "Bases de Dados",
                2800);
        Professor prof3 = new Professor("Maria Oliveira", "maria.oliveira@cesae.pt", "934567890", 38, "Frontend", 2600);
        Professor prof4 = new Professor("João Pereira", "joao.pereira@cesae.pt", "945678901", 45, "DevOps", 3000);

        professorRepository.save(prof1);
        professorRepository.save(prof2);
        professorRepository.save(prof3);
        professorRepository.save(prof4);

        // ========== UNIDADES CURRICULARES ==========
        UnidadeCurricular uc1 = new UnidadeCurricular("Programação Orientada a Objetos", "POO-101", 60, prof1);
        UnidadeCurricular uc2 = new UnidadeCurricular("Bases de Dados", "BD-102", 50, prof2);
        UnidadeCurricular uc3 = new UnidadeCurricular("HTML/CSS/JavaScript", "FE-103", 80, prof3);
        UnidadeCurricular uc4 = new UnidadeCurricular("React Framework", "FE-104", 60, prof3);
        UnidadeCurricular uc5 = new UnidadeCurricular("Docker & Kubernetes", "DO-105", 40, prof4);

        ucRepository.save(uc1);
        ucRepository.save(uc2);
        ucRepository.save(uc3);
        ucRepository.save(uc4);
        ucRepository.save(uc5);

        // ========== CURSOS ==========
        Curso curso1 = new Curso("Técnico de Programação", TipoCurso.FORMACAO, 12);
        Curso curso2 = new Curso("Desenvolvimento Web Full-Stack", TipoCurso.FORMACAO, 9);
        Curso curso3 = new Curso("DevOps Avançado", TipoCurso.POS_GRADUACAO, 6);

        curso1.getUnidadesCurriculares().add(uc1);
        curso1.getUnidadesCurriculares().add(uc2);
        curso2.getUnidadesCurriculares().add(uc3);
        curso2.getUnidadesCurriculares().add(uc4);
        curso2.getUnidadesCurriculares().add(uc2);
        curso3.getUnidadesCurriculares().add(uc5);

        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);

        // ========== TURMAS ==========
        Turma turma1 = new Turma("TP-2025-A", 20, curso1);
        Turma turma2 = new Turma("TP-2025-B", 20, curso1);
        Turma turma3 = new Turma("WEB-2025-A", 25, curso2);
        Turma turma4 = new Turma("WEB-2025-B", 25, curso2);
        Turma turma5 = new Turma("DEV-2025-A", 15, curso3);

        turma1.getUnidadesCurriculares().add(uc1);
        turma1.getUnidadesCurriculares().add(uc2);
        turma2.getUnidadesCurriculares().add(uc1);
        turma2.getUnidadesCurriculares().add(uc2);
        turma3.getUnidadesCurriculares().add(uc3);
        turma3.getUnidadesCurriculares().add(uc4);
        turma4.getUnidadesCurriculares().add(uc3);
        turma4.getUnidadesCurriculares().add(uc4);
        turma5.getUnidadesCurriculares().add(uc5);

        turmaRepository.save(turma1);
        turmaRepository.save(turma2);
        turmaRepository.save(turma3);
        turmaRepository.save(turma4);
        turmaRepository.save(turma5);

        // ========== ALUNOS ==========
        Aluno aluno1 = new Aluno("Pedro Costa", "pedro.costa@email.com", "911111111", 22);
        aluno1.setTurma(turma1);
        aluno1.adicionarNota(15.5);
        aluno1.adicionarNota(17.0);

        Aluno aluno2 = new Aluno("Sofia Martins", "sofia.martins@email.com", "922222222", 24);
        aluno2.setTurma(turma1);
        aluno2.adicionarNota(18.0);
        aluno2.adicionarNota(16.5);

        Aluno aluno3 = new Aluno("Miguel Ferreira", "miguel.ferreira@email.com", "933333333", 20);
        aluno3.setTurma(turma2);
        aluno3.adicionarNota(14.0);

        Aluno aluno4 = new Aluno("Inês Rodrigues", "ines.rodrigues@email.com", "944444444", 23);
        aluno4.setTurma(turma3);
        aluno4.adicionarNota(19.0);
        aluno4.adicionarNota(18.5);

        Aluno aluno5 = new Aluno("Tiago Almeida", "tiago.almeida@email.com", "955555555", 21);
        aluno5.setTurma(turma3);
        aluno5.adicionarNota(12.0);
        aluno5.adicionarNota(13.5);

        Aluno aluno6 = new Aluno("Rita Carvalho", "rita.carvalho@email.com", "966666666", 25);
        aluno6.setTurma(turma4);
        aluno6.setEstado(EstadoAluno.CONCLUIDO);

        Aluno aluno7 = new Aluno("André Sousa", "andre.sousa@email.com", "977777777", 22);
        aluno7.setTurma(turma5);
        aluno7.adicionarNota(16.0);

        Aluno aluno8 = new Aluno("Catarina Lopes", "catarina.lopes@email.com", "988888888", 26);
        aluno8.setTurma(turma5);
        aluno8.setEstado(EstadoAluno.SUSPENSO);

        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);
        alunoRepository.save(aluno3);
        alunoRepository.save(aluno4);
        alunoRepository.save(aluno5);
        alunoRepository.save(aluno6);
        alunoRepository.save(aluno7);
        alunoRepository.save(aluno8);

        System.out.println("✓ Dados de teste carregados com sucesso!");
        System.out.println("  - 4 Professores");
        System.out.println("  - 5 Unidades Curriculares");
        System.out.println("  - 3 Cursos");
        System.out.println("  - 5 Turmas");
        System.out.println("  - 8 Alunos");
    }
}
