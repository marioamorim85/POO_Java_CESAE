# Projeto Final - Sistema de Gestão Académica CESAE Digital

Sistema de gestão académica desenvolvido em Java, aplicando conceitos de Programação Orientada a Objetos.

## 📋 Descrição

Aplicação de consola que permite gerir cursos, turmas, unidades curriculares, alunos e professores do CESAE Digital.

## 🚀 Funcionalidades

- **Gestão de Cursos** - CRUD completo
- **Gestão de Turmas** - Criar, listar, adicionar alunos/UCs
- **Gestão de UCs** - Criar, associar professores
- **Gestão de Professores** - Registar, atribuir UCs (máx. 5)
- **Gestão de Alunos** - Inscrever, registar notas, alterar estado
- **Estatísticas** - 8 relatórios diferentes

## 📁 Estrutura

```
ProjetoFinal/
├── src/
│   ├── App.java              # Ponto de entrada
│   ├── CesaeDigital.java     # Menu principal + CRUD
│   ├── Pessoa.java           # Classe abstracta
│   ├── Aluno.java            # extends Pessoa
│   ├── Professor.java        # extends Pessoa
│   ├── UnidadeCurricular.java
│   ├── Turma.java
│   ├── Curso.java
│   ├── EstadoAluno.java      # enum
│   └── TipoCurso.java        # enum
├── bin/                      # Classes compiladas
└── README.md
```

## ⚙️ Compilação e Execução

```bash
# Compilar
javac -d bin -encoding UTF-8 src/*.java

# Executar
java -cp bin App
```

## 🎓 Conceitos POO Aplicados

| Conceito         | Implementação                         |
| ---------------- | ------------------------------------- |
| Classe Abstracta | `Pessoa`                              |
| Herança          | `Aluno`, `Professor` extends `Pessoa` |
| Encapsulamento   | Atributos `private`, getters/setters  |
| ArrayList        | Coleções de entidades                 |
| Sobrecarga       | Construtores múltiplos                |
| Override         | `toString()`, `mostrarDetalhes()`     |
| Enums            | `EstadoAluno`, `TipoCurso`            |
| Static           | Contadores de ID                      |
| Validação        | Dados de entrada                      |

## 📊 Dados de Teste

O sistema carrega automaticamente:

- 4 Professores
- 5 Unidades Curriculares
- 3 Cursos
- 6 Turmas
- 10 Alunos

## 👤 Autor

Mário Amorim - CESAE Digital
