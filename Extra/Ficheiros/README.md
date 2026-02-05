# Leitura e Escrita de Ficheiros em Java

## Objetivo
Aprender a ler e escrever dados em ficheiros de texto utilizando as classes de I/O do Java, incluindo tratamento adequado de exceções.

## Conceitos Abordados
- Classes `FileWriter` e `FileReader`
- Classes `BufferedWriter` e `BufferedReader`
- Classe `PrintWriter` para escrita formatada
- Classe `Scanner` para leitura de ficheiros
- Try-with-resources para gestão automática de recursos
- Serialização básica de objetos para texto
- Leitura e escrita de ficheiros CSV

## Enunciado do Exercício

### Sistema de Gestão de Alunos com Persistência em Ficheiro

Cria um sistema de gestão de alunos que guarda e carrega dados de/para ficheiros de texto.

#### Parte 1: Classe Aluno
Cria uma classe `Aluno` com:
- **Atributos**: numero, nome, curso, media
- **Métodos**:
  - `toCSV()` - Retorna os dados do aluno em formato CSV (separado por `;`)
  - `fromCSV(String linha)` - Método estático que cria um Aluno a partir de uma linha CSV
  - `toString()` - Representação textual do aluno

#### Parte 2: Classe GestorFicheiros
Cria uma classe `GestorFicheiros` com métodos estáticos para:
- `guardarAlunos(ArrayList<Aluno> alunos, String ficheiro)` - Guarda lista de alunos num ficheiro
- `carregarAlunos(String ficheiro)` - Carrega lista de alunos de um ficheiro
- `adicionarAluno(Aluno aluno, String ficheiro)` - Adiciona um aluno ao final do ficheiro
- `exportarRelatorio(ArrayList<Aluno> alunos, String ficheiro)` - Cria um relatório formatado

#### Parte 3: Classe Main
Na classe `Main`, demonstra:
1. Criação de uma lista de alunos
2. Guardar os alunos num ficheiro CSV
3. Carregar os alunos do ficheiro
4. Adicionar novos alunos ao ficheiro existente
5. Gerar um relatório formatado
6. Tratamento de exceções de I/O

## Estrutura de Ficheiros
```
Ficheiros/
└── src/
    ├── Aluno.java
    ├── GestorFicheiros.java
    ├── Main.java
    └── dados/
        ├── alunos.csv (gerado pelo programa)
        └── relatorio.txt (gerado pelo programa)
```

## Formato do Ficheiro CSV (alunos.csv)
```
numero;nome;curso;media
1001;João Silva;Informática;15.5
1002;Maria Santos;Design;17.0
1003;Pedro Costa;Marketing;14.0
```

## Formato do Relatório (relatorio.txt)
```
╔══════════════════════════════════════════════════════════════╗
║           RELATÓRIO DE ALUNOS - CESAE DIGITAL                ║
╠══════════════════════════════════════════════════════════════╣
║ Número │ Nome                │ Curso          │ Média        ║
╠════════╪═════════════════════╪════════════════╪══════════════╣
║ 1001   │ João Silva          │ Informática    │ 15.50        ║
║ 1002   │ Maria Santos        │ Design         │ 17.00        ║
║ 1003   │ Pedro Costa         │ Marketing      │ 14.00        ║
╠══════════════════════════════════════════════════════════════╣
║ Total de alunos: 3                                           ║
║ Média geral: 15.50                                           ║
╚══════════════════════════════════════════════════════════════╝
```

## Exemplo de Saída Esperada
```
========== GESTÃO DE ALUNOS COM FICHEIROS ==========

1. CRIAR LISTA DE ALUNOS
Aluno criado: 1001 - João Silva
Aluno criado: 1002 - Maria Santos
Aluno criado: 1003 - Pedro Costa

2. GUARDAR ALUNOS NO FICHEIRO
A guardar 3 alunos em 'dados/alunos.csv'...
Ficheiro guardado com sucesso!

3. CARREGAR ALUNOS DO FICHEIRO
A carregar alunos de 'dados/alunos.csv'...
Carregados 3 alunos com sucesso!

4. ADICIONAR NOVO ALUNO
A adicionar Ana Ferreira ao ficheiro...
Aluno adicionado com sucesso!

5. GERAR RELATÓRIO
A gerar relatório em 'dados/relatorio.txt'...
Relatório gerado com sucesso!
```

## Dicas
- Usa sempre `try-with-resources` para garantir que os ficheiros são fechados
- O `BufferedReader`/`BufferedWriter` são mais eficientes para ficheiros grandes
- Verifica sempre se o ficheiro existe antes de tentar ler
- Usa `File.separator` para compatibilidade entre sistemas operativos
- Trata sempre as exceções `IOException` e `FileNotFoundException`

## Classes Java Úteis
```java
// Escrita
FileWriter fw = new FileWriter("ficheiro.txt");
BufferedWriter bw = new BufferedWriter(fw);
PrintWriter pw = new PrintWriter(bw);

// Leitura
FileReader fr = new FileReader("ficheiro.txt");
BufferedReader br = new BufferedReader(fr);
Scanner sc = new Scanner(new File("ficheiro.txt"));
```

## Autor
Mário Amorim - CESAE Digital
