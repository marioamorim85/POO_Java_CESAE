# Projeto Final - Sistema de Gestão Académica CESAE Digital

## Como Começar

```bash
# Compilar
javac -d bin src/*.java

# Executar
java -cp bin App
```

---

## Ficheiros Incluídos

| Ficheiro            | Estado | O que fazer                              |
| ------------------- | ------ | ---------------------------------------- |
| `EstadoAluno.java`  | Pronto | Não mexer                                |
| `TipoCurso.java`    | Pronto | Não mexer                                |
| `Pessoa.java`       | Pronto | Usar como **exemplo** para as subclasses |
| `App.java`          | Pronto | Não mexer                                |
| `CesaeDigital.java` | Básico | Completar após criar as outras classes   |

---

## O Que Tens de Criar

### Fase 1 - Subclasses de Pessoa

- [ ] `Aluno.java` - extends Pessoa
- [ ] `Professor.java` - extends Pessoa

### Fase 2 - Classes de Gestão

- [ ] `UnidadeCurricular.java`
- [ ] `Turma.java`
- [ ] `Curso.java`

### Fase 3 - Completar CesaeDigital

- [ ] Adicionar ArrayLists
- [ ] Implementar submenus CRUD
- [ ] Implementar estatísticas
- [ ] Criar dados iniciais

---

## Checklist de Requisitos

- [ ] Classe abstracta com método abstracto (`Pessoa` - já feito)
- [ ] Herança (`Aluno` e `Professor` extends `Pessoa`)
- [ ] Encapsulamento (`private` + getters/setters)
- [ ] ArrayList para coleções
- [ ] Sobrecarga de construtores
- [ ] Override de `toString()` e `mostrarDetalhes()`
- [ ] Enums (`EstadoAluno`, `TipoCurso` - já feitos)
- [ ] Variáveis `static` para contadores de ID
- [ ] Validação de dados
- [ ] Comentários

---

## Dicas

### Para Herança (Aluno/Professor)

```java
public class Aluno extends Pessoa {
    private static int contadorAlunos = 1000; // 1000 só para parecer mais profissional (depois 1001, 1002, etc)
    private int numeroAluno;

    public Aluno(String nome, String email, String telefone, int idade) {
        super(nome, email, telefone, idade); // Chama construtor de Pessoa
        this.numeroAluno = ++contadorAlunos;
    }

    @Override
    public void mostrarDetalhes() {
        // Implementar...
    }
}
```

### Para o Menu

```java
while (opcao != 0) {
    // mostrar menu
    // ler opção
    // switch case
}
```

---

## Entrega

`TP_POO_[Nome]_[Apelido].zip`
