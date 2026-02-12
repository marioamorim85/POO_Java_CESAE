import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe CesaeDigital - Sistema Central de Gestão Académica
 * 
 * Classe principal que gere todos os dados do sistema e fornece
 * um menu interativo para as operações CRUD.
 * 
 * @author Mário Amorim
 * @version 1.0
 */
public class CesaeDigital {
    // ========== ATRIBUTOS PRIVADOS ==========
    /** Lista de todos os cursos */
    private final ArrayList<Curso> cursos;
    /** Lista de todos os professores */
    private final ArrayList<Professor> professores;
    /** Lista de todos os alunos */
    private final ArrayList<Aluno> alunos;
    /** Lista de todas as UCs */
    private final ArrayList<UnidadeCurricular> unidadesCurriculares;
    /** Lista de todas as turmas */
    private final ArrayList<Turma> turmas;
    /** Scanner para entrada de dados */
    private final Scanner scanner;

    // ========== CONSTRUTOR ==========
    /**
     * Construtor do CesaeDigital.
     * Inicializa as listas e carrega os dados iniciais.
     */
    public CesaeDigital() {
        cursos = new ArrayList<>();
        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        unidadesCurriculares = new ArrayList<>();
        turmas = new ArrayList<>();
        scanner = new Scanner(System.in);
        carregarDadosIniciais();
    }

    // ========== DADOS INICIAIS ==========
    /**
     * Carrega dados de exemplo para demonstração do sistema.
     * Cria cursos, turmas, UCs, professores e alunos.
     */
    private void carregarDadosIniciais() {
        System.out.println("A carregar dados iniciais...");

        // ===== CRIAR PROFESSORES (4) =====
        Professor prof1 = new Professor("João Silva", "joao.silva@cesae.pt", "912345678", 45, "Programação", 2500.0);
        Professor prof2 = new Professor("Maria Santos", "maria.santos@cesae.pt", "923456789", 38, "Bases de Dados",
                2400.0);
        Professor prof3 = new Professor("Pedro Costa", "pedro.costa@cesae.pt", "934567890", 42, "Redes", 2300.0);
        Professor prof4 = new Professor("Ana Ferreira", "ana.ferreira@cesae.pt", "945678901", 35, "Matemática", 2200.0);
        professores.add(prof1);
        professores.add(prof2);
        professores.add(prof3);
        professores.add(prof4);

        // ===== CRIAR UNIDADES CURRICULARES (5) =====
        UnidadeCurricular ucPOO = new UnidadeCurricular("Programação Orientada a Objetos", "POO-101", 60, prof1);
        UnidadeCurricular ucBD = new UnidadeCurricular("Bases de Dados", "BD-102", 45, prof2);
        UnidadeCurricular ucRedes = new UnidadeCurricular("Redes de Computadores", "RED-103", 40, prof3);
        UnidadeCurricular ucWeb = new UnidadeCurricular("Desenvolvimento Web", "WEB-104", 50, prof1);
        UnidadeCurricular ucMat = new UnidadeCurricular("Matemática Aplicada", "MAT-105", 30, prof4);
        unidadesCurriculares.add(ucPOO);
        unidadesCurriculares.add(ucBD);
        unidadesCurriculares.add(ucRedes);
        unidadesCurriculares.add(ucWeb);
        unidadesCurriculares.add(ucMat);

        // Associar UCs aos professores
        prof1.adicionarUC(ucPOO);
        prof1.adicionarUC(ucWeb);
        prof2.adicionarUC(ucBD);
        prof3.adicionarUC(ucRedes);
        prof4.adicionarUC(ucMat);

        // ===== CRIAR CURSOS (3) =====
        Curso cursoSD = new Curso("Software Developer", TipoCurso.FORMACAO, 12);
        Curso cursoDS = new Curso("Data Science", TipoCurso.POS_GRADUACAO, 18);
        Curso cursoCS = new Curso("Cibersegurança", TipoCurso.FORMACAO, 9);
        cursos.add(cursoSD);
        cursos.add(cursoDS);
        cursos.add(cursoCS);

        // Adicionar UCs aos cursos
        cursoSD.adicionarUC(ucPOO);
        cursoSD.adicionarUC(ucBD);
        cursoSD.adicionarUC(ucWeb);
        cursoDS.adicionarUC(ucBD);
        cursoDS.adicionarUC(ucMat);
        cursoCS.adicionarUC(ucRedes);
        cursoCS.adicionarUC(ucPOO);

        // ===== CRIAR TURMAS (2 por curso = 6) =====
        Turma turmaSD_A = new Turma("SD-2025-A", 20, cursoSD);
        Turma turmaSD_B = new Turma("SD-2025-B", 20, cursoSD);
        Turma turmaDS_A = new Turma("DS-2025-A", 15, cursoDS);
        Turma turmaDS_B = new Turma("DS-2025-B", 15, cursoDS);
        Turma turmaCS_A = new Turma("CS-2025-A", 18, cursoCS);
        Turma turmaCS_B = new Turma("CS-2025-B", 18, cursoCS);
        turmas.add(turmaSD_A);
        turmas.add(turmaSD_B);
        turmas.add(turmaDS_A);
        turmas.add(turmaDS_B);
        turmas.add(turmaCS_A);
        turmas.add(turmaCS_B);

        // Adicionar turmas aos cursos
        cursoSD.adicionarTurma(turmaSD_A);
        cursoSD.adicionarTurma(turmaSD_B);
        cursoDS.adicionarTurma(turmaDS_A);
        cursoDS.adicionarTurma(turmaDS_B);
        cursoCS.adicionarTurma(turmaCS_A);
        cursoCS.adicionarTurma(turmaCS_B);

        // Adicionar UCs às turmas
        turmaSD_A.adicionarUC(ucPOO);
        turmaSD_A.adicionarUC(ucBD);
        turmaSD_B.adicionarUC(ucPOO);
        turmaSD_B.adicionarUC(ucWeb);

        // ===== CRIAR ALUNOS (10) =====
        Aluno aluno1 = new Aluno("Carlos Oliveira", "carlos@email.com", "911111111", 22);
        Aluno aluno2 = new Aluno("Sofia Rodrigues", "sofia@email.com", "922222222", 24);
        Aluno aluno3 = new Aluno("Miguel Almeida", "miguel@email.com", "933333333", 21);
        Aluno aluno4 = new Aluno("Inês Martins", "ines@email.com", "944444444", 23);
        Aluno aluno5 = new Aluno("Tiago Pereira", "tiago@email.com", "955555555", 25);
        Aluno aluno6 = new Aluno("Beatriz Sousa", "beatriz@email.com", "966666666", 20);
        Aluno aluno7 = new Aluno("Rui Fernandes", "rui@email.com", "977777777", 26);
        Aluno aluno8 = new Aluno("Catarina Lopes", "catarina@email.com", "988888888", 22);
        Aluno aluno9 = new Aluno("André Carvalho", "andre@email.com", "999999999", 23);
        Aluno aluno10 = new Aluno("Mariana Gonçalves", "mariana@email.com", "900000000", 21);

        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        alunos.add(aluno5);
        alunos.add(aluno6);
        alunos.add(aluno7);
        alunos.add(aluno8);
        alunos.add(aluno9);
        alunos.add(aluno10);

        // Inscrever alunos nas turmas
        turmaSD_A.inscreverAluno(aluno1);
        turmaSD_A.inscreverAluno(aluno2);
        turmaSD_A.inscreverAluno(aluno3);
        turmaSD_B.inscreverAluno(aluno4);
        turmaSD_B.inscreverAluno(aluno5);
        turmaDS_A.inscreverAluno(aluno6);
        turmaDS_A.inscreverAluno(aluno7);
        turmaDS_B.inscreverAluno(aluno8);
        turmaCS_A.inscreverAluno(aluno9);
        turmaCS_B.inscreverAluno(aluno10);

        // Adicionar notas aos alunos
        aluno1.adicionarNota(15.5);
        aluno1.adicionarNota(17.0);
        aluno1.adicionarNota(14.0);
        aluno2.adicionarNota(18.0);
        aluno2.adicionarNota(16.5);
        aluno3.adicionarNota(12.0);
        aluno3.adicionarNota(14.5);
        aluno4.adicionarNota(19.0);
        aluno4.adicionarNota(18.5);
        aluno5.adicionarNota(10.0);
        aluno6.adicionarNota(16.0);
        aluno6.adicionarNota(17.5);
        aluno7.adicionarNota(13.0);
        aluno8.adicionarNota(15.0);
        aluno9.adicionarNota(11.5);
        aluno10.adicionarNota(14.0);

        // Alterar estado de alguns alunos
        aluno5.setEstado(EstadoAluno.SUSPENSO);
        aluno7.setEstado(EstadoAluno.CONCLUIDO);

        System.out.println("Dados iniciais carregados com sucesso!\n");
    }

    // ========== MENU PRINCIPAL ==========
    /**
     * Apresenta e gere o menu principal da aplicação.
     * O menu corre em ciclo até o utilizador escolher sair.
     */
    public void menuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║     CESAE DIGITAL - GESTÃO ACADÉMICA         ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  1. Gestão de Cursos                         ║");
            System.out.println("║  2. Gestão de Turmas                         ║");
            System.out.println("║  3. Gestão de Unidades Curriculares          ║");
            System.out.println("║  4. Gestão de Professores                    ║");
            System.out.println("║  5. Gestão de Alunos                         ║");
            System.out.println("║  6. Estatísticas e Relatórios                ║");
            System.out.println("║  0. Sair                                     ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuCursos();
                case 2 -> menuTurmas();
                case 3 -> menuUCs();
                case 4 -> menuProfessores();
                case 5 -> menuAlunos();
                case 6 -> menuEstatisticas();
                case 0 -> {
                    System.out.println("\nObrigado por utilizar o sistema CESAE Digital!");
                    System.out.println("A encerrar...");
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // ========== SUBMENUS ==========

    /**
     * Menu de gestão de cursos.
     */
    private void menuCursos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE CURSOS ===");
            System.out.println("1. Criar novo curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Ver detalhes de curso");
            System.out.println("4. Editar curso");
            System.out.println("5. Remover curso");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarCurso();
                case 2 -> listarCursos();
                case 3 -> verDetalhesCurso();
                case 4 -> editarCurso();
                case 5 -> removerCurso();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Menu de gestão de turmas.
     */
    private void menuTurmas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE TURMAS ===");
            System.out.println("1. Criar nova turma");
            System.out.println("2. Listar turmas");
            System.out.println("3. Ver alunos de uma turma");
            System.out.println("4. Adicionar UC a turma");
            System.out.println("5. Remover UC de turma");
            System.out.println("6. Remover turma");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarTurma();
                case 2 -> listarTurmas();
                case 3 -> verAlunosTurma();
                case 4 -> adicionarUCaTurma();
                case 5 -> removerUCdeTurma();
                case 6 -> removerTurma();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Menu de gestão de UCs.
     */
    private void menuUCs() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE UNIDADES CURRICULARES ===");
            System.out.println("1. Criar nova UC");
            System.out.println("2. Listar UCs");
            System.out.println("3. Associar professor a UC");
            System.out.println("4. Remover UC");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> criarUC();
                case 2 -> listarUCs();
                case 3 -> associarProfessorUC();
                case 4 -> removerUC();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Menu de gestão de professores.
     */
    private void menuProfessores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE PROFESSORES ===");
            System.out.println("1. Registar novo professor");
            System.out.println("2. Listar professores");
            System.out.println("3. Ver detalhes de professor");
            System.out.println("4. Atribuir UC a professor");
            System.out.println("5. Remover UC de professor");
            System.out.println("6. Remover professor");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> registarProfessor();
                case 2 -> listarProfessores();
                case 3 -> verDetalhesProfessor();
                case 4 -> atribuirUCaProfessor();
                case 5 -> removerUCdeProfessor();
                case 6 -> removerProfessor();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Menu de gestão de alunos.
     */
    private void menuAlunos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GESTÃO DE ALUNOS ===");
            System.out.println("1. Inscrever novo aluno");
            System.out.println("2. Listar todos os alunos");
            System.out.println("3. Listar alunos por turma");
            System.out.println("4. Ver detalhes de aluno");
            System.out.println("5. Alterar estado de aluno");
            System.out.println("6. Registar nota");
            System.out.println("7. Inscrever aluno em turma");
            System.out.println("8. Remover aluno");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> inscreverAluno();
                case 2 -> listarAlunos();
                case 3 -> listarAlunosPorTurma();
                case 4 -> verDetalhesAluno();
                case 5 -> alterarEstadoAluno();
                case 6 -> registarNota();
                case 7 -> inscreverAlunoEmTurma();
                case 8 -> removerAluno();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    /**
     * Menu de estatísticas e relatórios.
     */
    private void menuEstatisticas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== ESTATÍSTICAS E RELATÓRIOS ===");
            System.out.println("1. Totais (alunos, professores, cursos, turmas)");
            System.out.println("2. Média de notas de um aluno");
            System.out.println("3. Média de notas de uma turma");
            System.out.println("4. Alunos com média superior a X");
            System.out.println("5. Professor com mais UCs");
            System.out.println("6. Turma com mais e menos alunos");
            System.out.println("7. Alunos por estado");
            System.out.println("8. Taxa de ocupação das turmas");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> mostrarTotais();
                case 2 -> mediaAluno();
                case 3 -> mediaTurma();
                case 4 -> alunosMediaSuperior();
                case 5 -> professorMaisUCs();
                case 6 -> turmasMaisEMenosAlunos();
                case 7 -> alunosPorEstado();
                case 8 -> taxaOcupacaoTurmas();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ========== OPERAÇÕES DE CURSOS ==========

    private void criarCurso() {
        System.out.println("\n--- Criar Novo Curso ---");
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();

        System.out.println("Tipo de curso:");
        System.out.println("1. FORMACAO");
        System.out.println("2. POS_GRADUACAO");
        System.out.println("3. WORKSHOP");
        System.out.print("Escolha: ");
        int tipoOpcao = lerInteiro();
        TipoCurso tipo;
        tipo = switch (tipoOpcao) {
            case 2 -> TipoCurso.POS_GRADUACAO;
            case 3 -> TipoCurso.WORKSHOP;
            default -> TipoCurso.FORMACAO;
        };

        System.out.print("Duração (meses): ");
        int duracao = lerInteiro();

        Curso curso = new Curso(nome, tipo, duracao);
        cursos.add(curso);
        System.out.println("Curso criado com sucesso!");
    }

    private void listarCursos() {
        System.out.println("\n--- Lista de Cursos ---");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso registado.");
        } else {
            for (int i = 0; i < cursos.size(); i++) {
                System.out.println((i + 1) + ". " + cursos.get(i));
            }
        }
    }

    private void verDetalhesCurso() {
        listarCursos();
        if (cursos.isEmpty())
            return;
        System.out.print("Escolha o curso: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < cursos.size()) {
            cursos.get(idx).mostrarDetalhes();
            cursos.get(idx).listarTurmas();
        } else {
            System.out.println("Curso inválido!");
        }
    }

    private void editarCurso() {
        listarCursos();
        if (cursos.isEmpty())
            return;
        System.out.print("Escolha o curso a editar: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < cursos.size()) {
            Curso curso = cursos.get(idx);
            System.out.print("Novo nome (atual: " + curso.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                curso.setNome(nome);
            }
            System.out.print("Nova duração em meses (atual: " + curso.getDuracaoMeses() + "): ");
            String duracaoStr = scanner.nextLine();
            if (!duracaoStr.isEmpty()) {
                try {
                    curso.setDuracaoMeses(Integer.parseInt(duracaoStr));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido, mantida duração anterior.");
                }
            }
            System.out.println("Curso atualizado!");
        } else {
            System.out.println("Curso inválido!");
        }
    }

    private void removerCurso() {
        listarCursos();
        if (cursos.isEmpty())
            return;
        System.out.print("Escolha o curso a remover: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < cursos.size()) {
            Curso curso = cursos.remove(idx);
            System.out.println("Curso '" + curso.getNome() + "' removido!");
        } else {
            System.out.println("Curso inválido!");
        }
    }

    // ========== OPERAÇÕES DE TURMAS ==========

    private void criarTurma() {
        System.out.println("\n--- Criar Nova Turma ---");
        if (cursos.isEmpty()) {
            System.out.println("Erro: Crie primeiro um curso.");
            return;
        }
        System.out.print("Nome da turma (ex: SD-2025-C): ");
        String nome = scanner.nextLine();
        System.out.print("Capacidade máxima: ");
        int capacidade = lerInteiro();

        listarCursos();
        System.out.print("Escolha o curso: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < cursos.size()) {
            Turma turma = new Turma(nome, capacidade, cursos.get(idx));
            turmas.add(turma);
            cursos.get(idx).adicionarTurma(turma);
            System.out.println("Turma criada com sucesso!");
        } else {
            System.out.println("Curso inválido!");
        }
    }

    private void listarTurmas() {
        System.out.println("\n--- Lista de Turmas ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma registada.");
        } else {
            for (int i = 0; i < turmas.size(); i++) {
                System.out.println((i + 1) + ". " + turmas.get(i));
            }
        }
    }

    private void verAlunosTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < turmas.size()) {
            turmas.get(idx).listarAlunos();
        } else {
            System.out.println("Turma inválida!");
        }
    }

    private void adicionarUCaTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idxT = lerInteiro() - 1;
        if (idxT < 0 || idxT >= turmas.size()) {
            System.out.println("Turma inválida!");
            return;
        }
        listarUCs();
        if (unidadesCurriculares.isEmpty())
            return;
        System.out.print("Escolha a UC: ");
        int idxU = lerInteiro() - 1;
        if (idxU >= 0 && idxU < unidadesCurriculares.size()) {
            turmas.get(idxT).adicionarUC(unidadesCurriculares.get(idxU));
        } else {
            System.out.println("UC inválida!");
        }
    }

    private void removerUCdeTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idxT = lerInteiro() - 1;
        if (idxT < 0 || idxT >= turmas.size()) {
            System.out.println("Turma inválida!");
            return;
        }
        Turma turma = turmas.get(idxT);
        ArrayList<UnidadeCurricular> ucs = turma.getUnidadesCurriculares();
        if (ucs.isEmpty()) {
            System.out.println("Esta turma não tem UCs.");
            return;
        }
        System.out.println("UCs da turma:");
        for (int i = 0; i < ucs.size(); i++) {
            System.out.println((i + 1) + ". " + ucs.get(i));
        }
        System.out.print("Escolha a UC a remover: ");
        int idxU = lerInteiro() - 1;
        if (idxU >= 0 && idxU < ucs.size()) {
            turma.removerUC(ucs.get(idxU));
        } else {
            System.out.println("UC inválida!");
        }
    }

    private void removerTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma a remover: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < turmas.size()) {
            Turma turma = turmas.remove(idx);
            if (turma.getCurso() != null) {
                turma.getCurso().removerTurma(turma);
            }
            System.out.println("Turma removida!");
        } else {
            System.out.println("Turma inválida!");
        }
    }

    // ========== OPERAÇÕES DE UCS ==========

    private void criarUC() {
        System.out.println("\n--- Criar Nova UC ---");
        System.out.print("Nome da UC: ");
        String nome = scanner.nextLine();
        System.out.print("Código (ex: POO-101): ");
        String codigo = scanner.nextLine();
        System.out.print("Carga horária (horas): ");
        int carga = lerInteiro();

        UnidadeCurricular uc = new UnidadeCurricular(nome, codigo, carga);
        unidadesCurriculares.add(uc);
        System.out.println("UC criada com sucesso!");
    }

    private void listarUCs() {
        System.out.println("\n--- Lista de Unidades Curriculares ---");
        if (unidadesCurriculares.isEmpty()) {
            System.out.println("Nenhuma UC registada.");
        } else {
            for (int i = 0; i < unidadesCurriculares.size(); i++) {
                System.out.println((i + 1) + ". " + unidadesCurriculares.get(i));
            }
        }
    }

    private void associarProfessorUC() {
        listarUCs();
        if (unidadesCurriculares.isEmpty())
            return;
        System.out.print("Escolha a UC: ");
        int idxU = lerInteiro() - 1;
        if (idxU < 0 || idxU >= unidadesCurriculares.size()) {
            System.out.println("UC inválida!");
            return;
        }
        listarProfessores();
        if (professores.isEmpty())
            return;
        System.out.print("Escolha o professor: ");
        int idxP = lerInteiro() - 1;
        if (idxP >= 0 && idxP < professores.size()) {
            UnidadeCurricular uc = unidadesCurriculares.get(idxU);
            Professor prof = professores.get(idxP);
            uc.setProfessor(prof);
            prof.adicionarUC(uc);
        } else {
            System.out.println("Professor inválido!");
        }
    }

    private void removerUC() {
        listarUCs();
        if (unidadesCurriculares.isEmpty())
            return;
        System.out.print("Escolha a UC a remover: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < unidadesCurriculares.size()) {
            UnidadeCurricular uc = unidadesCurriculares.remove(idx);
            System.out.println("UC '" + uc.getNome() + "' removida!");
        } else {
            System.out.println("UC inválida!");
        }
    }

    // ========== OPERAÇÕES DE PROFESSORES ==========

    private void registarProfessor() {
        System.out.println("\n--- Registar Novo Professor ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInteiro();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = lerDouble();

        Professor prof = new Professor(nome, email, telefone, idade, especialidade, salario);
        professores.add(prof);
        System.out.println("Professor registado com sucesso! Número: " + prof.getNumeroProfessor());
    }

    private void listarProfessores() {
        System.out.println("\n--- Lista de Professores ---");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor registado.");
        } else {
            for (int i = 0; i < professores.size(); i++) {
                System.out.println((i + 1) + ". " + professores.get(i));
            }
        }
    }

    private void verDetalhesProfessor() {
        listarProfessores();
        if (professores.isEmpty())
            return;
        System.out.print("Escolha o professor: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < professores.size()) {
            professores.get(idx).mostrarDetalhes();
            professores.get(idx).listarUCs();
        } else {
            System.out.println("Professor inválido!");
        }
    }

    private void atribuirUCaProfessor() {
        listarProfessores();
        if (professores.isEmpty())
            return;
        System.out.print("Escolha o professor: ");
        int idxP = lerInteiro() - 1;
        if (idxP < 0 || idxP >= professores.size()) {
            System.out.println("Professor inválido!");
            return;
        }
        listarUCs();
        if (unidadesCurriculares.isEmpty())
            return;
        System.out.print("Escolha a UC: ");
        int idxU = lerInteiro() - 1;
        if (idxU >= 0 && idxU < unidadesCurriculares.size()) {
            Professor prof = professores.get(idxP);
            UnidadeCurricular uc = unidadesCurriculares.get(idxU);
            if (prof.adicionarUC(uc)) {
                uc.setProfessor(prof);
            }
        } else {
            System.out.println("UC inválida!");
        }
    }

    private void removerUCdeProfessor() {
        listarProfessores();
        if (professores.isEmpty())
            return;
        System.out.print("Escolha o professor: ");
        int idxP = lerInteiro() - 1;
        if (idxP < 0 || idxP >= professores.size()) {
            System.out.println("Professor inválido!");
            return;
        }
        Professor prof = professores.get(idxP);
        ArrayList<UnidadeCurricular> ucs = prof.getUnidadesCurriculares();
        if (ucs.isEmpty()) {
            System.out.println("Este professor não tem UCs atribuídas.");
            return;
        }
        prof.listarUCs();
        System.out.print("Escolha a UC a remover: ");
        int idxU = lerInteiro() - 1;
        if (idxU >= 0 && idxU < ucs.size()) {
            UnidadeCurricular uc = ucs.get(idxU);
            prof.removerUC(uc);
            uc.setProfessor(null);
        } else {
            System.out.println("UC inválida!");
        }
    }

    private void removerProfessor() {
        listarProfessores();
        if (professores.isEmpty())
            return;
        System.out.print("Escolha o professor a remover: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < professores.size()) {
            Professor prof = professores.remove(idx);
            System.out.println("Professor '" + prof.getNome() + "' removido!");
        } else {
            System.out.println("Professor inválido!");
        }
    }

    // ========== OPERAÇÕES DE ALUNOS ==========

    private void inscreverAluno() {
        System.out.println("\n--- Inscrever Novo Aluno ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInteiro();

        Aluno aluno = new Aluno(nome, email, telefone, idade);
        alunos.add(aluno);
        System.out.println("Aluno inscrito com sucesso! Número: " + aluno.getNumeroAluno());

        // Perguntar se quer inscrever numa turma
        System.out.print("Deseja inscrever numa turma? (S/N): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("S")) {
            listarTurmas();
            if (!turmas.isEmpty()) {
                System.out.print("Escolha a turma: ");
                int idx = lerInteiro() - 1;
                if (idx >= 0 && idx < turmas.size()) {
                    turmas.get(idx).inscreverAluno(aluno);
                }
            }
        }
    }

    private void listarAlunos() {
        System.out.println("\n--- Lista de Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno registado.");
        } else {
            for (int i = 0; i < alunos.size(); i++) {
                System.out.println((i + 1) + ". " + alunos.get(i));
            }
        }
    }

    private void listarAlunosPorTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < turmas.size()) {
            turmas.get(idx).listarAlunos();
        } else {
            System.out.println("Turma inválida!");
        }
    }

    private void verDetalhesAluno() {
        System.out.print("Número do aluno: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno != null) {
            aluno.mostrarDetalhes();
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private void alterarEstadoAluno() {
        System.out.print("Número do aluno: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }
        System.out.println("Estado atual: " + aluno.getEstado());
        System.out.println("Novo estado:");
        System.out.println("1. ATIVO");
        System.out.println("2. SUSPENSO");
        System.out.println("3. CONCLUIDO");
        System.out.println("4. DESISTENTE");
        System.out.print("Escolha: ");
        int opcao = lerInteiro();
        EstadoAluno novoEstado;
        novoEstado = switch (opcao) {
            case 2 -> EstadoAluno.SUSPENSO;
            case 3 -> EstadoAluno.CONCLUIDO;
            case 4 -> EstadoAluno.DESISTENTE;
            default -> EstadoAluno.ATIVO;
        };
        aluno.setEstado(novoEstado);
        System.out.println("Estado alterado para " + novoEstado);
    }

    private void registarNota() {
        System.out.print("Número do aluno: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }
        System.out.print("Nota (0-20): ");
        double nota = lerDouble();
        aluno.adicionarNota(nota);
    }

    private void inscreverAlunoEmTurma() {
        System.out.print("Número do aluno: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < turmas.size()) {
            turmas.get(idx).inscreverAluno(aluno);
        } else {
            System.out.println("Turma inválida!");
        }
    }

    private void removerAluno() {
        System.out.print("Número do aluno a remover: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno != null) {
            if (aluno.getTurma() != null) {
                aluno.getTurma().removerAluno(aluno);
            }
            alunos.remove(aluno);
            System.out.println("Aluno removido!");
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    // ========== ESTATÍSTICAS ==========

    private void mostrarTotais() {
        System.out.println("\n=== TOTAIS DO SISTEMA ===");
        System.out.println("Total de Alunos: " + alunos.size());
        System.out.println("Total de Professores: " + professores.size());
        System.out.println("Total de Cursos: " + cursos.size());
        System.out.println("Total de Turmas: " + turmas.size());
        System.out.println("Total de UCs: " + unidadesCurriculares.size());
    }

    private void mediaAluno() {
        System.out.print("Número do aluno: ");
        int numero = lerInteiro();
        Aluno aluno = encontrarAlunoPorNumero(numero);
        if (aluno != null) {
            System.out.println("Aluno: " + aluno.getNome());
            System.out.println("Média: " + String.format("%.2f", aluno.calcularMedia()));
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private void mediaTurma() {
        listarTurmas();
        if (turmas.isEmpty())
            return;
        System.out.print("Escolha a turma: ");
        int idx = lerInteiro() - 1;
        if (idx >= 0 && idx < turmas.size()) {
            Turma turma = turmas.get(idx);
            System.out.println("Turma: " + turma.getNome());
            System.out.println("Média da turma: " + String.format("%.2f", turma.calcularMediaTurma()));
        } else {
            System.out.println("Turma inválida!");
        }
    }

    private void alunosMediaSuperior() {
        System.out.print("Mostrar alunos com média superior a: ");
        double valor = lerDouble();
        System.out.println("\nAlunos com média > " + valor + ":");
        int count = 0;
        for (Aluno aluno : alunos) {
            if (aluno.calcularMedia() > valor) {
                System.out
                        .println("- " + aluno.getNome() + " | Média: " + String.format("%.2f", aluno.calcularMedia()));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            System.out.println("Total: " + count + " alunos");
        }
    }

    private void professorMaisUCs() {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor registado.");
            return;
        }
        Professor profMax = professores.get(0);
        for (Professor prof : professores) {
            if (prof.getNumeroUCs() > profMax.getNumeroUCs()) {
                profMax = prof;
            }
        }
        System.out.println("\nProfessor com mais UCs:");
        System.out.println("Nome: " + profMax.getNome());
        System.out.println("Número de UCs: " + profMax.getNumeroUCs());
        profMax.listarUCs();
    }

    private void turmasMaisEMenosAlunos() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma registada.");
            return;
        }
        Turma turmaMais = turmas.get(0);
        Turma turmaMenos = turmas.get(0);
        for (Turma turma : turmas) {
            if (turma.getNumeroAlunos() > turmaMais.getNumeroAlunos()) {
                turmaMais = turma;
            }
            if (turma.getNumeroAlunos() < turmaMenos.getNumeroAlunos()) {
                turmaMenos = turma;
            }
        }
        System.out.println("\nTurma com MAIS alunos:");
        System.out.println("  " + turmaMais.getNome() + " - " + turmaMais.getNumeroAlunos() + " alunos");
        System.out.println("\nTurma com MENOS alunos:");
        System.out.println("  " + turmaMenos.getNome() + " - " + turmaMenos.getNumeroAlunos() + " alunos");
    }

    private void alunosPorEstado() {
        System.out.println("\n=== ALUNOS POR ESTADO ===");
        for (EstadoAluno estado : EstadoAluno.values()) {
            System.out.println("\n" + estado + ":");
            int count = 0;
            for (Aluno aluno : alunos) {
                if (aluno.getEstado() == estado) {
                    System.out.println("  - " + aluno.getNome() + " [" + aluno.getNumeroAluno() + "]");
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("  (nenhum)");
            }
        }
    }

    private void taxaOcupacaoTurmas() {
        System.out.println("\n=== TAXA DE OCUPAÇÃO DAS TURMAS ===");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma registada.");
            return;
        }
        for (Turma turma : turmas) {
            double taxa = turma.getTaxaOcupacao();
            System.out.printf("%s: %d/%d (%.1f%%)\n",
                    turma.getNome(),
                    turma.getNumeroAlunos(),
                    turma.getCapacidadeMaxima(),
                    taxa);
        }
    }

    // ========== MÉTODOS AUXILIARES ==========

    /**
     * Encontra um aluno pelo seu número.
     * 
     * @param numero Número do aluno.
     * @return O aluno ou null se não encontrado.
     */
    private Aluno encontrarAlunoPorNumero(int numero) {
        for (Aluno aluno : alunos) {
            if (aluno.getNumeroAluno() == numero) {
                return aluno;
            }
        }
        return null;
    }

    /**
     * Lê um número inteiro do utilizador com tratamento de erros.
     * 
     * @return O número lido.
     */
    private int lerInteiro() {
        while (true) {
            try {
                String linha = scanner.nextLine();
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Introduza um número: ");
            }
        }
    }

    /**
     * Lê um número decimal do utilizador com tratamento de erros.
     * 
     * @return O número lido.
     */
    private double lerDouble() {
        while (true) {
            try {
                String linha = scanner.nextLine();
                return Double.parseDouble(linha.trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Introduza um número: ");
            }
        }
    }
}
