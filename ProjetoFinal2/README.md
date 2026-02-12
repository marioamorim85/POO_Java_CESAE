# ProjetoFinal2 - CESAE Digital (Swing GUI)

## Descricao

Versao com interface grafica (GUI) do Sistema de Gestao Academica do CESAE Digital.
Esta versao usa Swing (incluido no JDK) para a interface grafica.

## Estrutura do Projeto

```
ProjetoFinal2/
├── src/
│   ├── App.java                 # Classe principal
│   ├── MainFrame.java           # Janela principal Swing
│   └── model/
│       ├── Pessoa.java          # Classe abstrata base
│       ├── Aluno.java           # Classe Aluno
│       ├── Professor.java       # Classe Professor
│       ├── Curso.java           # Classe Curso
│       ├── Turma.java           # Classe Turma
│       ├── UnidadeCurricular.java # Classe UC
│       ├── CesaeDigital.java    # Servico/Singleton
│       ├── EstadoAluno.java     # Enum estados
│       └── TipoCurso.java       # Enum tipos
├── bin/                         # Ficheiros compilados
└── lib/                         # Bibliotecas externas
```

## Requisitos

- **Java 17+** (recomendado Java 21)
- Nao requer dependencias externas (Swing ja vem com o JDK)

## Como Executar

### Opcao 1: Linha de Comandos

```powershell
# Navegar para a pasta do projeto
cd ProjetoFinal2

# Compilar
javac -d bin -sourcepath src src/App.java src/MainFrame.java src/model/*.java

# Executar
java -cp bin App
```

### Opcao 2: VS Code

Basta abrir a pasta ProjetoFinal2 no VS Code e clicar em "Run" no ficheiro App.java.

- [x] Remover aluno
- [x] Ver detalhes do aluno
- [x] Dados de exemplo pre-carregados

### A Implementar

- [ ] Gestao de Professores (CRUD)
- [ ] Gestao de Cursos (CRUD)
- [ ] Gestao de Turmas (CRUD)
- [ ] Gestao de UCs (CRUD)
- [ ] Persistencia em ficheiro/BD
- [ ] Relatorios e exportacao

## Conceitos POO Utilizados

### Heranca

- `Aluno` e `Professor` estendem `Pessoa` (classe abstrata)

### Encapsulamento

- Todos os atributos sao `private` com getters/setters

### Polimorfismo

- Metodo `mostrarDetalhes()` implementado diferente em cada classe

### Abstracao

- Classe `Pessoa` e abstrata (nao pode ser instanciada)

### Composicao e Agregacao

- `Turma` tem lista de `Aluno` (agregacao)
- `Curso` tem lista de `UnidadeCurricular` (agregacao)
- `UnidadeCurricular` referencia `Professor` (associacao)

### Padroes de Design

- **Singleton**: `CesaeDigital` como ponto unico de acesso aos dados

## Interface Grafica

A interface Swing inclui:

- Menu lateral com navegacao
- Dashboard com cards de estatisticas coloridos
- Tabela de alunos com acoes (adicionar, ver, remover)
- Dialogos modais para input de dados
- Look and feel nativo do sistema operativo

## Autor

Mario Amorim - CESAE Digital

## Versao

2.0 - Swing GUI Edition
