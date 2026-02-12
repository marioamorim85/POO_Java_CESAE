import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import model.*;

/**
 * Janela principal da aplicacao
 * Contem o dashboard e a gestao completa de todas as entidades
 * 
 * @author Mario Amorim
 * @version 3.0
 */
public class MainFrame extends JFrame {
    
    // Servico de dados
    private final CesaeDigital cesae;
    
    // Cores do tema
    private static final Color COR_PRIMARIA = new Color(44, 62, 80);
    private static final Color COR_SECUNDARIA = new Color(52, 73, 94);
    private static final Color COR_FUNDO = new Color(236, 240, 241);
    private static final Color COR_SUCESSO = new Color(39, 174, 96);
    private static final Color COR_INFO = new Color(52, 152, 219);
    private static final Color COR_PERIGO = new Color(231, 76, 60);
    private static final Color COR_ROXO = new Color(155, 89, 182);
    private static final Color COR_HOVER = new Color(41, 128, 185);
    private static final Color COR_TEXTO = new Color(44, 62, 80);
    private static final Color COR_TEXTO_CLARO = new Color(127, 140, 141);
    
    // Componentes do dashboard
    private JLabel lblTotalAlunos;
    private JLabel lblTotalProfessores;
    private JLabel lblTotalCursos;
    private JLabel lblTotalTurmas;
    
    // Tabelas
    private JTable tabelaAlunos;
    private DefaultTableModel modeloTabelaAlunos;
    private JTable tabelaProfessores;
    private DefaultTableModel modeloTabelaProfessores;
    private JTable tabelaCursos;
    private DefaultTableModel modeloTabelaCursos;
    private JTable tabelaTurmas;
    private DefaultTableModel modeloTabelaTurmas;
    private JTable tabelaUCs;
    private DefaultTableModel modeloTabelaUCs;
    
    // Navegacao
    private CardLayout cardLayout;
    private JPanel painelConteudoPrincipal;
    private JButton botaoSelecionado;
    
    /**
     * Construtor - Inicializa a janela
     */
    public MainFrame() {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.err.println("Erro ao configurar Look and Feel: " + e.getMessage());
        }
        
        // Obter instancia do servico
        cesae = CesaeDigital.getInstance();
        
        // Configurar janela
        setTitle("CESAE Digital - Sistema de Gestao Academica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        
        // Criar layout
        initComponents();
        
        // Atualizar dados
        atualizarDashboard();
        atualizarTabelaAlunos();
        atualizarTabelaProfessores();
        atualizarTabelaCursos();
        atualizarTabelaTurmas();
        atualizarTabelaUCs();
        
        System.out.println("Aplicacao iniciada com sucesso!");
        System.out.println(cesae.getResumo());
    }
    
    /**
     * Inicializa os componentes da interface
     */
    private void initComponents() {
        // Layout principal
        setLayout(new BorderLayout());
        
        // Painel do menu lateral
        JPanel painelMenu = criarPainelMenu();
        add(painelMenu, BorderLayout.WEST);
        
        // Painel central
        JPanel painelCentral = criarPainelCentral();
        add(painelCentral, BorderLayout.CENTER);
        
        // Barra inferior
        JPanel painelRodape = criarRodape();
        add(painelRodape, BorderLayout.SOUTH);
    }
    
    /**
     * Cria o painel do menu lateral
     */
    private JPanel criarPainelMenu() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PRIMARIA);
        painel.setPreferredSize(new Dimension(220, 0));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(20, 15, 20, 15));
        
        // Logo/Titulo
        JPanel painelTitulo = new JPanel();
        painelTitulo.setOpaque(false);
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Icone do logo (chapeu de formatura)
        JLabel iconLabel = new JLabel("\uD83C\uDF93", SwingConstants.CENTER);
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTitulo.add(iconLabel);
        painelTitulo.add(Box.createRigidArea(new Dimension(0, 5)));
        
        JLabel titulo = new JLabel("CESAE Digital");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTitulo.add(titulo);
        
        JLabel subtitulo = new JLabel("Sistema Academico");
        subtitulo.setForeground(COR_TEXTO_CLARO);;
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelTitulo.add(subtitulo);
        
        painel.add(painelTitulo);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(180, 1));
        sep.setForeground(COR_SECUNDARIA);
        painel.add(sep);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Botoes do menu com icones
        String[][] menuItems = {
            {"", "Dashboard"},
            {"", "Alunos"},
            {"", "Professores"},
            {"", "Cursos"},
            {"", "Turmas"},
            {"", "UCs"}
        };
        
        for (String[] item : menuItems) {
            JButton btn = criarBotaoMenu(item[0] + "  " + item[1], item[1]);
            if (item[1].equals("Dashboard")) {
                botaoSelecionado = btn;
                btn.setBackground(COR_HOVER);
            }
            painel.add(btn);
            painel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
        // Espacador
        painel.add(Box.createVerticalGlue());
        
        // Separador inferior
        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(180, 1));
        sep2.setForeground(COR_SECUNDARIA);
        painel.add(sep2);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Info do utilizador
        JPanel painelUser = new JPanel();
        painelUser.setOpaque(false);
        painelUser.setLayout(new BoxLayout(painelUser, BoxLayout.Y_AXIS));
        painelUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel userIcon = new JLabel("Admin", SwingConstants.CENTER);
        userIcon.setForeground(Color.WHITE);
        userIcon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelUser.add(userIcon);
        
        painel.add(painelUser);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Versao
        JLabel versao = new JLabel("v3.0 - Swing Modern");
        versao.setForeground(COR_TEXTO_CLARO);
        versao.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        versao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(versao);
        
        return painel;
    }
    
    /**
     * Cria um botao do menu
     */
    private JButton criarBotaoMenu(String texto, String painelNome) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(190, 40));
        btn.setPreferredSize(new Dimension(190, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(COR_SECUNDARIA);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        
        btn.addActionListener(e -> {
            if (botaoSelecionado != null) {
                botaoSelecionado.setBackground(COR_SECUNDARIA);
            }
            btn.setBackground(COR_HOVER);
            botaoSelecionado = btn;
            mudarPainel(painelNome);
        });
        
        // Efeito hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (btn != botaoSelecionado) {
                    btn.setBackground(new Color(62, 83, 104));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (btn != botaoSelecionado) {
                    btn.setBackground(COR_SECUNDARIA);
                }
            }
        });
        
        return btn;
    }
    
    /**
     * Cria o painel central com dashboard e tabela
     */
    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_FUNDO);
        painel.setLayout(new BorderLayout(0, 20));
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Painel com CardLayout para navegacao
        cardLayout = new CardLayout();
        painelConteudoPrincipal = new JPanel(cardLayout);
        painelConteudoPrincipal.setOpaque(false);
        
        // Adicionar paineis de cada seccao
        painelConteudoPrincipal.add(criarPainelDashboardCompleto(), "Dashboard");
        painelConteudoPrincipal.add(criarPainelAlunos(), "Alunos");
        painelConteudoPrincipal.add(criarPainelProfessores(), "Professores");
        painelConteudoPrincipal.add(criarPainelCursos(), "Cursos");
        painelConteudoPrincipal.add(criarPainelTurmas(), "Turmas");
        painelConteudoPrincipal.add(criarPainelUCs(), "UCs");
        
        painel.add(painelConteudoPrincipal, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Muda para o painel selecionado
     */
    private void mudarPainel(String nome) {
        cardLayout.show(painelConteudoPrincipal, nome);
        System.out.println("Mudou para: " + nome);
    }
    
    /**
     * Cria o painel completo do Dashboard
     */
    private JPanel criarPainelDashboardCompleto() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 25));
        
        // Cabecalho
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel titulo = new JLabel("Dashboard - Visao Geral");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(COR_TEXTO);
        cabecalho.add(titulo, BorderLayout.WEST);
        
        // Data atual
        JLabel dataLabel = new JLabel(java.time.LocalDate.now().format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dataLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dataLabel.setForeground(COR_TEXTO_CLARO);
        cabecalho.add(dataLabel, BorderLayout.EAST);
        
        painel.add(cabecalho, BorderLayout.NORTH);
        
        // Painel central com cards e resumo
        JPanel painelCentro = new JPanel(new BorderLayout(0, 25));
        painelCentro.setOpaque(false);
        
        // Dashboard cards
        JPanel painelDashboard = criarPainelDashboard();
        painelCentro.add(painelDashboard, BorderLayout.NORTH);
        
        // Painel de resumo
        JPanel painelResumo = new JPanel(new GridLayout(1, 2, 25, 0));
        painelResumo.setOpaque(false);
        
        // Resumo de alunos recentes
        JPanel painelAlunosRecentes = criarPainelResumo("Últimos Alunos Adicionados", 
            cesae.getAlunos().stream()
                .limit(5)
                .map(a -> "- " + a.getNome() + " - " + a.getEmail())
                .toArray(String[]::new));
        painelResumo.add(painelAlunosRecentes);
        
        // Resumo de cursos
        JPanel painelCursosResumo = criarPainelResumo("Cursos Disponiveis",
            cesae.getCursos().stream()
                .map(c -> "- " + c.getNome() + " (" + c.getDuracaoMeses() + " meses)")
                .toArray(String[]::new));
        painelResumo.add(painelCursosResumo);
        
        painelCentro.add(painelResumo, BorderLayout.CENTER);
        
        painel.add(painelCentro, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria um painel de resumo para o dashboard
     */
    private JPanel criarPainelResumo(String titulo, String[] itens) {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new CompoundBorder(
            new CompoundBorder(
                new EmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1)),
            new EmptyBorder(20, 20, 20, 20)));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(COR_TEXTO);
        painel.add(lblTitulo, BorderLayout.NORTH);
        
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String item : itens) {
            modelo.addElement(item);
        }
        if (itens.length == 0) {
            modelo.addElement("  Nenhum item encontrado");
        }
        
        JList<String> lista = new JList<>(modelo);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lista.setFixedCellHeight(30);
        lista.setBackground(Color.WHITE);
        lista.setForeground(COR_TEXTO);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        painel.add(scroll, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel de Alunos
     */
    private JPanel criarPainelAlunos() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 20));
        
        // Cabecalho
        JLabel titulo = new JLabel("Gestao de Alunos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(COR_TEXTO);
        painel.add(titulo, BorderLayout.NORTH);
        
        // Tabela de alunos
        JPanel painelTabela = criarPainelTabela();
        painel.add(painelTabela, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel de Professores com tabela e acoes
     */
    private JPanel criarPainelProfessores() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 20));
        
        // Titulo principal
        JLabel tituloPrincipal = new JLabel("Gestao de Professores");
        tituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPrincipal.setForeground(COR_TEXTO);
        painel.add(tituloPrincipal, BorderLayout.NORTH);
        
        // Painel da tabela
        JPanel painelTabela = new JPanel(new BorderLayout(0, 10));
        painelTabela.setOpaque(false);
        
        // Cabecalho com subtitulo e botoes
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel subtitulo = new JLabel("Lista de Professores");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitulo.setForeground(COR_TEXTO);
        cabecalho.add(subtitulo, BorderLayout.WEST);
        
        // Botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnAdicionar = criarBotaoAcao("+ Adicionar", COR_SUCESSO);
        btnAdicionar.addActionListener(e -> adicionarProfessor());
        painelBotoes.add(btnAdicionar);
        
        JButton btnDetalhes = criarBotaoAcao("Ver Detalhes", COR_INFO);
        btnDetalhes.addActionListener(e -> verDetalhesProfessor());
        painelBotoes.add(btnDetalhes);
        
        JButton btnRemover = criarBotaoAcao("Remover", COR_PERIGO);
        btnRemover.addActionListener(e -> removerProfessor());
        painelBotoes.add(btnRemover);
        
        cabecalho.add(painelBotoes, BorderLayout.EAST);
        painelTabela.add(cabecalho, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"Numero", "Nome", "Email", "Especialidade", "Salario"};
        modeloTabelaProfessores = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaProfessores = new JTable(modeloTabelaProfessores);
        configurarTabela(tabelaProfessores);
        
        JScrollPane scrollPane = new JScrollPane(tabelaProfessores);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        painelTabela.add(scrollPane, BorderLayout.CENTER);
        
        painel.add(painelTabela, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel de Cursos com tabela e acoes
     */
    private JPanel criarPainelCursos() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 20));
        
        // Titulo principal
        JLabel tituloPrincipal = new JLabel("Gestao de Cursos");
        tituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPrincipal.setForeground(COR_TEXTO);
        painel.add(tituloPrincipal, BorderLayout.NORTH);
        
        // Painel da tabela
        JPanel painelTabela = new JPanel(new BorderLayout(0, 10));
        painelTabela.setOpaque(false);
        
        // Cabecalho com subtitulo e botoes
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel subtitulo = new JLabel("Lista de Cursos");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitulo.setForeground(COR_TEXTO);
        cabecalho.add(subtitulo, BorderLayout.WEST);
        
        // Botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnAdicionar = criarBotaoAcao("+ Adicionar", COR_SUCESSO);
        btnAdicionar.addActionListener(e -> adicionarCurso());
        painelBotoes.add(btnAdicionar);
        
        JButton btnDetalhes = criarBotaoAcao("Ver Detalhes", COR_INFO);
        btnDetalhes.addActionListener(e -> verDetalhesCurso());
        painelBotoes.add(btnDetalhes);
        
        JButton btnRemover = criarBotaoAcao("Remover", COR_PERIGO);
        btnRemover.addActionListener(e -> removerCurso());
        painelBotoes.add(btnRemover);
        
        cabecalho.add(painelBotoes, BorderLayout.EAST);
        painelTabela.add(cabecalho, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"Nome", "Tipo", "Duracao (meses)", "Turmas", "UCs"};
        modeloTabelaCursos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaCursos = new JTable(modeloTabelaCursos);
        configurarTabela(tabelaCursos);
        
        JScrollPane scrollPane = new JScrollPane(tabelaCursos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        painelTabela.add(scrollPane, BorderLayout.CENTER);
        
        painel.add(painelTabela, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel de Turmas com tabela e acoes
     */
    private JPanel criarPainelTurmas() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 20));
        
        // Titulo principal
        JLabel tituloPrincipal = new JLabel("Gestao de Turmas");
        tituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPrincipal.setForeground(COR_TEXTO);
        painel.add(tituloPrincipal, BorderLayout.NORTH);
        
        // Painel da tabela
        JPanel painelTabela = new JPanel(new BorderLayout(0, 10));
        painelTabela.setOpaque(false);
        
        // Cabecalho com subtitulo e botoes
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel subtitulo = new JLabel("Lista de Turmas");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitulo.setForeground(COR_TEXTO);
        cabecalho.add(subtitulo, BorderLayout.WEST);
        
        // Botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnAdicionar = criarBotaoAcao("+ Adicionar", COR_SUCESSO);
        btnAdicionar.addActionListener(e -> adicionarTurma());
        painelBotoes.add(btnAdicionar);
        
        JButton btnDetalhes = criarBotaoAcao("Ver Detalhes", COR_INFO);
        btnDetalhes.addActionListener(e -> verDetalhesTurma());
        painelBotoes.add(btnDetalhes);
        
        JButton btnRemover = criarBotaoAcao("Remover", COR_PERIGO);
        btnRemover.addActionListener(e -> removerTurma());
        painelBotoes.add(btnRemover);
        
        cabecalho.add(painelBotoes, BorderLayout.EAST);
        painelTabela.add(cabecalho, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"Nome", "Capacidade", "Alunos", "Curso"};
        modeloTabelaTurmas = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaTurmas = new JTable(modeloTabelaTurmas);
        configurarTabela(tabelaTurmas);
        
        JScrollPane scrollPane = new JScrollPane(tabelaTurmas);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        painelTabela.add(scrollPane, BorderLayout.CENTER);
        
        painel.add(painelTabela, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel de UCs com tabela e acoes
     */
    private JPanel criarPainelUCs() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BorderLayout(0, 20));
        
        // Titulo principal
        JLabel tituloPrincipal = new JLabel("Gestao de Unidades Curriculares");
        tituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPrincipal.setForeground(COR_TEXTO);
        painel.add(tituloPrincipal, BorderLayout.NORTH);
        
        // Painel da tabela
        JPanel painelTabela = new JPanel(new BorderLayout(0, 10));
        painelTabela.setOpaque(false);
        
        // Cabecalho com subtitulo e botoes
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel subtitulo = new JLabel("Lista de Unidades Curriculares");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitulo.setForeground(COR_TEXTO);
        cabecalho.add(subtitulo, BorderLayout.WEST);
        
        // Botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnAdicionar = criarBotaoAcao("+ Adicionar", COR_SUCESSO);
        btnAdicionar.addActionListener(e -> adicionarUC());
        painelBotoes.add(btnAdicionar);
        
        JButton btnDetalhes = criarBotaoAcao("Ver Detalhes", COR_INFO);
        btnDetalhes.addActionListener(e -> verDetalhesUC());
        painelBotoes.add(btnDetalhes);
        
        JButton btnRemover = criarBotaoAcao("Remover", COR_PERIGO);
        btnRemover.addActionListener(e -> removerUC());
        painelBotoes.add(btnRemover);
        
        cabecalho.add(painelBotoes, BorderLayout.EAST);
        painelTabela.add(cabecalho, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"Codigo", "Nome", "Carga Horaria", "Professor"};
        modeloTabelaUCs = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaUCs = new JTable(modeloTabelaUCs);
        configurarTabela(tabelaUCs);
        
        JScrollPane scrollPane = new JScrollPane(tabelaUCs);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        painelTabela.add(scrollPane, BorderLayout.CENTER);
        
        painel.add(painelTabela, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Cria o painel do dashboard com cards
     */
    private JPanel criarPainelDashboard() {
        JPanel painel = new JPanel(new GridLayout(1, 4, 25, 0));
        painel.setOpaque(false);
        painel.setPreferredSize(new Dimension(0, 130));
        
        // Card Alunos
        lblTotalAlunos = new JLabel("0", SwingConstants.CENTER);
        painel.add(criarCard("Alunos", lblTotalAlunos, COR_INFO));
        
        // Card Professores
        lblTotalProfessores = new JLabel("0", SwingConstants.CENTER);
        painel.add(criarCard("Professores", lblTotalProfessores, COR_SUCESSO));
        
        // Card Cursos
        lblTotalCursos = new JLabel("0", SwingConstants.CENTER);
        painel.add(criarCard("Cursos", lblTotalCursos, COR_PERIGO));
        
        // Card Turmas
        lblTotalTurmas = new JLabel("0", SwingConstants.CENTER);
        painel.add(criarCard("Turmas", lblTotalTurmas, COR_ROXO));
        
        return painel;
    }
    
    /**
     * Cria um card do dashboard
     */
    private JPanel criarCard(String titulo, JLabel valorLabel, Color cor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(cor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Valor
        valorLabel.setForeground(Color.WHITE);
        valorLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        valorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalGlue());
        card.add(valorLabel);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // Titulo
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(255, 255, 255, 220));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblTitulo);
        card.add(Box.createVerticalGlue());
        
        // Cursor de mao
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return card;
    }
    
    /**
     * Cria o painel da tabela de alunos
     */
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout(0, 15));
        painel.setOpaque(false);
        
        // Cabecalho com titulo e botoes
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        
        JLabel titulo = new JLabel("Lista de Alunos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(COR_TEXTO);
        cabecalho.add(titulo, BorderLayout.WEST);
        
        // Botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);
        
        JButton btnAdicionar = criarBotaoAcao("+ Adicionar", COR_SUCESSO);
        btnAdicionar.addActionListener(e -> adicionarAluno());
        painelBotoes.add(btnAdicionar);
        
        JButton btnDetalhes = criarBotaoAcao("Ver Detalhes", COR_INFO);
        btnDetalhes.addActionListener(e -> verDetalhesAluno());
        painelBotoes.add(btnDetalhes);
        
        JButton btnRemover = criarBotaoAcao("Remover", COR_PERIGO);
        btnRemover.addActionListener(e -> removerAluno());
        painelBotoes.add(btnRemover);
        
        cabecalho.add(painelBotoes, BorderLayout.EAST);
        painel.add(cabecalho, BorderLayout.NORTH);
        
        // Criar tabela
        String[] colunas = {"Numero", "Nome", "Email", "Estado", "Turma"};
        modeloTabelaAlunos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaAlunos = new JTable(modeloTabelaAlunos);
        configurarTabela(tabelaAlunos);
        
        // Configurar larguras das colunas
        tabelaAlunos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabelaAlunos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaAlunos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabelaAlunos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabelaAlunos.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    /**
     * Configura estilos modernos para uma tabela
     */
    private void configurarTabela(JTable tabela) {
        tabela.setRowHeight(35);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setGridColor(new Color(230, 230, 230));
        tabela.setSelectionBackground(new Color(52, 152, 219, 80));
        tabela.setSelectionForeground(COR_TEXTO);
        tabela.setShowVerticalLines(false);
        tabela.setIntercellSpacing(new Dimension(0, 1));
        tabela.setFillsViewportHeight(true);
        
        // Renderer personalizado para o cabecalho
        tabela.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value != null ? value.toString() : "");
                label.setOpaque(true);
                label.setBackground(new Color(52, 73, 94));
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.BOLD, 13));
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 128, 185)),
                    new EmptyBorder(10, 12, 10, 12)));
                return label;
            }
        });
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 45));
        
        // Renderer para linhas alternadas
        tabela.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    label.setBackground(new Color(52, 152, 219, 80));
                    label.setForeground(COR_TEXTO);
                } else {
                    label.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 249, 250));
                    label.setForeground(COR_TEXTO);
                }
                
                label.setBorder(new EmptyBorder(8, 12, 8, 12));
                label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                
                return label;
            }
        });
    }
    
    /**
     * Cria um botao de acao
     */
    private JButton criarBotaoAcao(String texto, Color cor) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(cor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(cor.brighter());
                } else {
                    g2.setColor(cor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(130, 35));
        return btn;
    }
    
    /**
     * Cria o rodape
     */
    private JPanel criarRodape() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_SECUNDARIA);
        painel.setPreferredSize(new Dimension(0, 35));
        
        JLabel texto = new JLabel("CESAE Digital - Sistema de Gestao Academica - 2026");
        texto.setForeground(new Color(189, 195, 199));
        texto.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        painel.add(texto);
        
        return painel;
    }
    
    /**
     * Atualiza os valores do dashboard
     */
    private void atualizarDashboard() {
        lblTotalAlunos.setText(String.valueOf(cesae.getTotalAlunos()));
        lblTotalProfessores.setText(String.valueOf(cesae.getTotalProfessores()));
        lblTotalCursos.setText(String.valueOf(cesae.getTotalCursos()));
        lblTotalTurmas.setText(String.valueOf(cesae.getTotalTurmas()));
    }
    
    /**
     * Atualiza a tabela de alunos
     */
    private void atualizarTabelaAlunos() {
        modeloTabelaAlunos.setRowCount(0);
        for (Aluno aluno : cesae.getAlunos()) {
            Object[] linha = {
                aluno.getNumeroAluno(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getEstado(),
                aluno.getTurma() != null ? aluno.getTurma().getNome() : "Sem turma"
            };
            modeloTabelaAlunos.addRow(linha);
        }
    }
    
    /**
     * Atualiza a tabela de professores
     */
    private void atualizarTabelaProfessores() {
        modeloTabelaProfessores.setRowCount(0);
        for (Professor prof : cesae.getProfessores()) {
            Object[] linha = {
                prof.getNumeroProfessor(),
                prof.getNome(),
                prof.getEmail(),
                prof.getEspecialidade(),
                String.format("%.2f €", prof.getSalario())
            };
            modeloTabelaProfessores.addRow(linha);
        }
    }
    
    /**
     * Atualiza a tabela de cursos
     */
    private void atualizarTabelaCursos() {
        modeloTabelaCursos.setRowCount(0);
        for (Curso curso : cesae.getCursos()) {
            Object[] linha = {
                curso.getNome(),
                curso.getTipo(),
                curso.getDuracaoMeses(),
                curso.getTurmas().size(),
                curso.getUnidadesCurriculares().size()
            };
            modeloTabelaCursos.addRow(linha);
        }
    }
    
    /**
     * Atualiza a tabela de turmas
     */
    private void atualizarTabelaTurmas() {
        modeloTabelaTurmas.setRowCount(0);
        for (Turma turma : cesae.getTurmas()) {
            Object[] linha = {
                turma.getNome(),
                turma.getCapacidadeMaxima(),
                turma.getAlunos().size(),
                turma.getCurso() != null ? turma.getCurso().getNome() : "Sem curso"
            };
            modeloTabelaTurmas.addRow(linha);
        }
    }
    
    /**
     * Atualiza a tabela de UCs
     */
    private void atualizarTabelaUCs() {
        modeloTabelaUCs.setRowCount(0);
        for (UnidadeCurricular uc : cesae.getUnidadesCurriculares()) {
            Object[] linha = {
                uc.getCodigo(),
                uc.getNome(),
                uc.getCargaHoraria() + "h",
                uc.getProfessor() != null ? uc.getProfessor().getNome() : "Sem professor"
            };
            modeloTabelaUCs.addRow(linha);
        }
    }
    
    /**
     * Adiciona um novo aluno
     */
    private void adicionarAluno() {
        // Criar dialogo
        JDialog dialogo = new JDialog(this, "Novo Aluno", true);
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        // Painel de formulario
        JPanel painelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        painelForm.setBorder(new EmptyBorder(20, 20, 10, 20));
        
        painelForm.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        painelForm.add(txtNome);
        
        painelForm.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        painelForm.add(txtEmail);
        
        painelForm.add(new JLabel("Telefone:"));
        JTextField txtTelefone = new JTextField();
        painelForm.add(txtTelefone);
        
        painelForm.add(new JLabel("Idade:"));
        JTextField txtIdade = new JTextField();
        painelForm.add(txtIdade);
        
        painelForm.add(new JLabel("Turma:"));
        JComboBox<Turma> comboTurma = new JComboBox<>();
        comboTurma.addItem(null);
        for (Turma t : cesae.getTurmas()) {
            comboTurma.addItem(t);
        }
        painelForm.add(comboTurma);
        
        dialogo.add(painelForm, BorderLayout.CENTER);
        
        // Botoes
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        painelBotoes.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(39, 174, 96));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String telefone = txtTelefone.getText().trim();
                int idade = Integer.parseInt(txtIdade.getText().trim());
                Turma turma = (Turma) comboTurma.getSelectedItem();
                
                if (nome.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Nome e email sao obrigatorios!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Aluno novoAluno = new Aluno(nome, email, telefone, idade, turma);
                cesae.adicionarAluno(novoAluno);
                
                if (turma != null) {
                    turma.inscreverAluno(novoAluno);
                }
                
                atualizarTabelaAlunos();
                atualizarDashboard();
                dialogo.dispose();
                
                JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Idade deve ser um numero valido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        painelBotoes.add(btnConfirmar);
        
        dialogo.add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Mostra detalhes do aluno selecionado
     */
    private void verDetalhesAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int numero = (int) modeloTabelaAlunos.getValueAt(linha, 0);
        Aluno aluno = cesae.getAlunos().stream()
            .filter(a -> a.getNumeroAluno() == numero)
            .findFirst()
            .orElse(null);
        
        if (aluno != null) {
            String detalhes = String.format(
                """
                Numero: %d
                Nome: %s
                Email: %s
                Telefone: %s
                Idade: %d anos
                Estado: %s
                Turma: %s
                Media: %.2f""",
                aluno.getNumeroAluno(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getIdade(),
                aluno.getEstado(),
                aluno.getTurma() != null ? aluno.getTurma().getNome() : "Sem turma",
                aluno.calcularMedia()
            );
            
            JOptionPane.showMessageDialog(this, detalhes, 
                "Detalhes do Aluno #" + aluno.getNumeroAluno(), 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Remove o aluno selecionado
     */
    private void removerAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int numero = (int) modeloTabelaAlunos.getValueAt(linha, 0);
        String nome = (String) modeloTabelaAlunos.getValueAt(linha, 1);
        
        int confirma = JOptionPane.showConfirmDialog(this, 
            "Tem a certeza que deseja remover o aluno " + nome + "?",
            "Confirmar Remocao", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            Aluno aluno = cesae.getAlunos().stream()
                .filter(a -> a.getNumeroAluno() == numero)
                .findFirst()
                .orElse(null);
            
            if (aluno != null) {
                cesae.removerAluno(aluno);
                atualizarTabelaAlunos();
                atualizarDashboard();
                
                JOptionPane.showMessageDialog(this, "Aluno removido com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // ============== METODOS CRUD PROFESSORES ==============
    
    /**
     * Adiciona um novo professor
     */
    private void adicionarProfessor() {
        JDialog dialogo = new JDialog(this, "Novo Professor", true);
        dialogo.setSize(400, 350);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel painelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        painelForm.setBorder(new EmptyBorder(20, 20, 10, 20));
        
        painelForm.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        painelForm.add(txtNome);
        
        painelForm.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        painelForm.add(txtEmail);
        
        painelForm.add(new JLabel("Telefone:"));
        JTextField txtTelefone = new JTextField();
        painelForm.add(txtTelefone);
        
        painelForm.add(new JLabel("Idade:"));
        JTextField txtIdade = new JTextField();
        painelForm.add(txtIdade);
        
        painelForm.add(new JLabel("Especialidade:"));
        JTextField txtEspecialidade = new JTextField();
        painelForm.add(txtEspecialidade);
        
        painelForm.add(new JLabel("Salario:"));
        JTextField txtSalario = new JTextField();
        painelForm.add(txtSalario);
        
        dialogo.add(painelForm, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        painelBotoes.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(39, 174, 96));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String telefone = txtTelefone.getText().trim();
                int idade = Integer.parseInt(txtIdade.getText().trim());
                String especialidade = txtEspecialidade.getText().trim();
                double salario = Double.parseDouble(txtSalario.getText().trim());
                
                if (nome.isEmpty() || email.isEmpty() || especialidade.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Nome, email e especialidade sao obrigatorios!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Professor novoProfessor = new Professor(nome, email, telefone, idade, especialidade, salario);
                cesae.adicionarProfessor(novoProfessor);
                
                atualizarTabelaProfessores();
                atualizarDashboard();
                dialogo.dispose();
                
                JOptionPane.showMessageDialog(this, "Professor adicionado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Idade e salario devem ser numeros validos!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        painelBotoes.add(btnConfirmar);
        
        dialogo.add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Mostra detalhes do professor selecionado
     */
    private void verDetalhesProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int numero = (int) modeloTabelaProfessores.getValueAt(linha, 0);
        Professor prof = cesae.getProfessores().stream()
            .filter(p -> p.getNumeroProfessor() == numero)
            .findFirst()
            .orElse(null);
        
        if (prof != null) {
            String detalhes = String.format(
                """
                Numero: %d
                Nome: %s
                Email: %s
                Telefone: %s
                Idade: %d anos
                Especialidade: %s
                Salario: %.2f EUR
                UCs que leciona: %d""",
                prof.getNumeroProfessor(),
                prof.getNome(),
                prof.getEmail(),
                prof.getTelefone(),
                prof.getIdade(),
                prof.getEspecialidade(),
                prof.getSalario(),
                prof.getUnidadesCurriculares().size()
            );
            
            JOptionPane.showMessageDialog(this, detalhes, 
                "Detalhes do Professor #" + prof.getNumeroProfessor(), 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Remove o professor selecionado
     */
    private void removerProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int numero = (int) modeloTabelaProfessores.getValueAt(linha, 0);
        String nome = (String) modeloTabelaProfessores.getValueAt(linha, 1);
        
        int confirma = JOptionPane.showConfirmDialog(this, 
            "Tem a certeza que deseja remover o professor " + nome + "?",
            "Confirmar Remocao", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            Professor prof = cesae.getProfessores().stream()
                .filter(p -> p.getNumeroProfessor() == numero)
                .findFirst()
                .orElse(null);
            
            if (prof != null) {
                cesae.removerProfessor(prof);
                atualizarTabelaProfessores();
                atualizarDashboard();
                
                JOptionPane.showMessageDialog(this, "Professor removido com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // ============== METODOS CRUD CURSOS ==============
    
    /**
     * Adiciona um novo curso
     */
    private void adicionarCurso() {
        JDialog dialogo = new JDialog(this, "Novo Curso", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel painelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        painelForm.setBorder(new EmptyBorder(20, 20, 10, 20));
        
        painelForm.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        painelForm.add(txtNome);
        
        painelForm.add(new JLabel("Tipo:"));
        JComboBox<TipoCurso> comboTipo = new JComboBox<>(TipoCurso.values());
        painelForm.add(comboTipo);
        
        painelForm.add(new JLabel("Duracao (meses):"));
        JTextField txtDuracao = new JTextField();
        painelForm.add(txtDuracao);
        
        dialogo.add(painelForm, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        painelBotoes.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(39, 174, 96));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                TipoCurso tipo = (TipoCurso) comboTipo.getSelectedItem();
                int duracao = Integer.parseInt(txtDuracao.getText().trim());
                
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Nome e obrigatorio!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Curso novoCurso = new Curso(nome, tipo, duracao);
                cesae.adicionarCurso(novoCurso);
                
                atualizarTabelaCursos();
                atualizarDashboard();
                dialogo.dispose();
                
                JOptionPane.showMessageDialog(this, "Curso adicionado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Duracao deve ser um numero valido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        painelBotoes.add(btnConfirmar);
        
        dialogo.add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Mostra detalhes do curso selecionado
     */
    private void verDetalhesCurso() {
        int linha = tabelaCursos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nomeCurso = (String) modeloTabelaCursos.getValueAt(linha, 0);
        Curso curso = cesae.getCursos().stream()
            .filter(c -> c.getNome().equals(nomeCurso))
            .findFirst()
            .orElse(null);
        
        if (curso != null) {
            StringBuilder turmasStr = new StringBuilder();
            for (Turma t : curso.getTurmas()) {
                turmasStr.append("- ").append(t.getNome()).append("\n");
            }
            if (turmasStr.length() == 0) turmasStr.append("Nenhuma turma");
            
            String detalhes = String.format(
                """
                Nome: %s
                Tipo: %s
                Duracao: %d meses
                Total de Turmas: %d
                Total de UCs: %d
                
                Turmas:
                %s""",
                curso.getNome(),
                curso.getTipo(),
                curso.getDuracaoMeses(),
                curso.getTurmas().size(),
                curso.getUnidadesCurriculares().size(),
                turmasStr.toString()
            );
            
            JOptionPane.showMessageDialog(this, detalhes, 
                "Detalhes do Curso - " + curso.getNome(), 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Remove o curso selecionado
     */
    private void removerCurso() {
        int linha = tabelaCursos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um curso da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nome = (String) modeloTabelaCursos.getValueAt(linha, 0);
        
        int confirma = JOptionPane.showConfirmDialog(this, 
            "Tem a certeza que deseja remover o curso " + nome + "?",
            "Confirmar Remocao", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            Curso curso = cesae.getCursos().stream()
                .filter(c -> c.getNome().equals(nome))
                .findFirst()
                .orElse(null);
            
            if (curso != null) {
                cesae.removerCurso(curso);
                atualizarTabelaCursos();
                atualizarDashboard();
                
                JOptionPane.showMessageDialog(this, "Curso removido com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // ============== METODOS CRUD TURMAS ==============
    
    /**
     * Adiciona uma nova turma
     */
    private void adicionarTurma() {
        JDialog dialogo = new JDialog(this, "Nova Turma", true);
        dialogo.setSize(400, 250);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel painelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        painelForm.setBorder(new EmptyBorder(20, 20, 10, 20));
        
        painelForm.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        painelForm.add(txtNome);
        
        painelForm.add(new JLabel("Capacidade Maxima:"));
        JTextField txtCapacidade = new JTextField();
        painelForm.add(txtCapacidade);
        
        painelForm.add(new JLabel("Curso:"));
        JComboBox<Curso> comboCurso = new JComboBox<>();
        comboCurso.addItem(null);
        for (Curso c : cesae.getCursos()) {
            comboCurso.addItem(c);
        }
        painelForm.add(comboCurso);
        
        dialogo.add(painelForm, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        painelBotoes.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(39, 174, 96));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                int capacidade = Integer.parseInt(txtCapacidade.getText().trim());
                Curso curso = (Curso) comboCurso.getSelectedItem();
                
                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Nome e obrigatorio!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Turma novaTurma = new Turma(nome, capacidade, curso);
                cesae.adicionarTurma(novaTurma);
                
                if (curso != null) {
                    curso.adicionarTurma(novaTurma);
                }
                
                atualizarTabelaTurmas();
                atualizarDashboard();
                dialogo.dispose();
                
                JOptionPane.showMessageDialog(this, "Turma adicionada com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Capacidade deve ser um numero valido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        painelBotoes.add(btnConfirmar);
        
        dialogo.add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Mostra detalhes da turma selecionada
     */
    private void verDetalhesTurma() {
        int linha = tabelaTurmas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma turma da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nomeTurma = (String) modeloTabelaTurmas.getValueAt(linha, 0);
        Turma turma = cesae.getTurmas().stream()
            .filter(t -> t.getNome().equals(nomeTurma))
            .findFirst()
            .orElse(null);
        
        if (turma != null) {
            StringBuilder alunosStr = new StringBuilder();
            for (Aluno a : turma.getAlunos()) {
                alunosStr.append("- ").append(a.getNome()).append("\n");
            }
            if (alunosStr.length() == 0) alunosStr.append("Nenhum aluno inscrito");
            
            String detalhes = String.format(
                """
                Nome: %s
                Capacidade: %d alunos
                Alunos inscritos: %d
                Curso: %s
                
                Lista de Alunos:
                %s""",
                turma.getNome(),
                turma.getCapacidadeMaxima(),
                turma.getAlunos().size(),
                turma.getCurso() != null ? turma.getCurso().getNome() : "Sem curso",
                alunosStr.toString()
            );
            
            JOptionPane.showMessageDialog(this, detalhes, 
                "Detalhes da Turma - " + turma.getNome(), 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Remove a turma selecionada
     */
    private void removerTurma() {
        int linha = tabelaTurmas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma turma da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nome = (String) modeloTabelaTurmas.getValueAt(linha, 0);
        
        int confirma = JOptionPane.showConfirmDialog(this, 
            "Tem a certeza que deseja remover a turma " + nome + "?",
            "Confirmar Remocao", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            Turma turma = cesae.getTurmas().stream()
                .filter(t -> t.getNome().equals(nome))
                .findFirst()
                .orElse(null);
            
            if (turma != null) {
                cesae.removerTurma(turma);
                atualizarTabelaTurmas();
                atualizarDashboard();
                
                JOptionPane.showMessageDialog(this, "Turma removida com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // ============== METODOS CRUD UCs ==============
    
    /**
     * Adiciona uma nova UC
     */
    private void adicionarUC() {
        JDialog dialogo = new JDialog(this, "Nova Unidade Curricular", true);
        dialogo.setSize(400, 280);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        JPanel painelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        painelForm.setBorder(new EmptyBorder(20, 20, 10, 20));
        
        painelForm.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField();
        painelForm.add(txtNome);
        
        painelForm.add(new JLabel("Codigo:"));
        JTextField txtCodigo = new JTextField();
        painelForm.add(txtCodigo);
        
        painelForm.add(new JLabel("Carga Horaria:"));
        JTextField txtCarga = new JTextField();
        painelForm.add(txtCarga);
        
        painelForm.add(new JLabel("Professor:"));
        JComboBox<Professor> comboProfessor = new JComboBox<>();
        comboProfessor.addItem(null);
        for (Professor p : cesae.getProfessores()) {
            comboProfessor.addItem(p);
        }
        painelForm.add(comboProfessor);
        
        dialogo.add(painelForm, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dialogo.dispose());
        painelBotoes.add(btnCancelar);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(39, 174, 96));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String codigo = txtCodigo.getText().trim();
                int carga = Integer.parseInt(txtCarga.getText().trim());
                Professor professor = (Professor) comboProfessor.getSelectedItem();
                
                if (nome.isEmpty() || codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Nome e codigo sao obrigatorios!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                UnidadeCurricular novaUC = new UnidadeCurricular(nome, codigo, carga, professor);
                cesae.adicionarUnidadeCurricular(novaUC);
                
                atualizarTabelaUCs();
                dialogo.dispose();
                
                JOptionPane.showMessageDialog(this, "Unidade Curricular adicionada com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Carga horaria deve ser um numero valido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        painelBotoes.add(btnConfirmar);
        
        dialogo.add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    /**
     * Mostra detalhes da UC selecionada
     */
    private void verDetalhesUC() {
        int linha = tabelaUCs.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma UC da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String codigoUC = (String) modeloTabelaUCs.getValueAt(linha, 0);
        UnidadeCurricular uc = cesae.getUnidadesCurriculares().stream()
            .filter(u -> u.getCodigo().equals(codigoUC))
            .findFirst()
            .orElse(null);
        
        if (uc != null) {
            String detalhes = String.format(
                """
                Codigo: %s
                Nome: %s
                Carga Horaria: %d horas
                Professor: %s""",
                uc.getCodigo(),
                uc.getNome(),
                uc.getCargaHoraria(),
                uc.getProfessor() != null ? uc.getProfessor().getNome() : "Sem professor atribuido"
            );
            
            JOptionPane.showMessageDialog(this, detalhes, 
                "Detalhes da UC - " + uc.getNome(), 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Remove a UC selecionada
     */
    private void removerUC() {
        int linha = tabelaUCs.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma UC da tabela!", 
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String codigo = (String) modeloTabelaUCs.getValueAt(linha, 0);
        String nome = (String) modeloTabelaUCs.getValueAt(linha, 1);
        
        int confirma = JOptionPane.showConfirmDialog(this, 
            "Tem a certeza que deseja remover a UC " + nome + "?",
            "Confirmar Remocao", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            UnidadeCurricular uc = cesae.getUnidadesCurriculares().stream()
                .filter(u -> u.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
            
            if (uc != null) {
                cesae.removerUnidadeCurricular(uc);
                atualizarTabelaUCs();
                
                JOptionPane.showMessageDialog(this, "Unidade Curricular removida com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
