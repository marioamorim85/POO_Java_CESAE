# Classes Abstratas em Java - Exercício Avançado

## Objetivo

Consolidar o conceito de **classes abstratas** e **métodos abstratos** em Java, demonstrando herança, polimorfismo e encapsulamento num cenário realista.

## Conceitos Abordados

- Definição de classes abstratas com `abstract`
- Métodos abstratos (sem implementação)
- Métodos concretos em classes abstratas
- Herança (`extends`)
- Polimorfismo (tratamento uniforme de objetos diferentes)
- Override de métodos (`@Override`)
- Encapsulamento (atributos `private` + getters/setters)
- Coleções (`ArrayList`)
- Variáveis `static` para contadores

---

## Enunciado do Exercício

### Sistema de Gestão de Funcionários

Cria um sistema de gestão de funcionários de uma empresa, onde diferentes tipos de funcionários têm formas diferentes de calcular o salário.

#### Parte 1: Classe Abstrata `Funcionario`

Cria uma classe abstrata `Funcionario` com:

**Atributos:**

- `numeroFuncionario` (int) - gerado automaticamente com contador static
- `nome` (String)
- `email` (String)
- `dataAdmissao` (String) - formato "dd/mm/aaaa"

**Métodos:**

- Construtor que recebe nome, email e data de admissão
- Getters e setters para todos os atributos
- `calcularSalario()` - **método abstrato** (cada tipo de funcionário calcula de forma diferente)
- `mostrarDetalhes()` - **método abstrato**
- `toString()` - método concreto com informação básica

#### Parte 2: Subclasses

Cria três subclasses que estendem `Funcionario`:

**1. FuncionarioHorista**

- Atributos adicionais: `horasTrabalhadas` (int), `valorHora` (double)
- Salário = horasTrabalhadas × valorHora
- Se trabalhar mais de 160 horas, as horas extra pagam 1.5× o valor normal

**2. FuncionarioMensalista**

- Atributos adicionais: `salarioBase` (double), `subsidioAlimentacao` (double)
- Salário = salarioBase + subsidioAlimentacao

**3. FuncionarioComissionado**

- Atributos adicionais: `salarioBase` (double), `vendasMes` (double), `percentagemComissao` (double)
- Salário = salarioBase + (vendasMes × percentagemComissao / 100)

#### Parte 3: Classe `GestorFuncionarios`

Cria uma classe `GestorFuncionarios` com:

- ArrayList de `Funcionario` para guardar todos os funcionários
- Métodos para:
  - `adicionarFuncionario(Funcionario f)`
  - `listarFuncionarios()`
  - `calcularFolhaSalarial()` - soma de todos os salários
  - `encontrarMaiorSalario()` - funcionário com maior salário
  - `listarPorTipo(String tipo)` - lista apenas um tipo específico

#### Parte 4: Classe `Main`

Na classe `Main`, demonstra:

1. Criação de funcionários de cada tipo
2. Uso de polimorfismo (ArrayList de `Funcionario` com diferentes subclasses)
3. Cálculo individual de salários
4. Cálculo da folha salarial total
5. Listagem de todos os funcionários
6. Identificação do funcionário com maior salário

---

## Estrutura de Ficheiros

```
Abstract2/
└── src/
    ├── Funcionario.java          (classe abstrata)
    ├── FuncionarioHorista.java   (subclasse)
    ├── FuncionarioMensalista.java (subclasse)
    ├── FuncionarioComissionado.java (subclasse)
    ├── GestorFuncionarios.java   (gestão da coleção)
    └── Main.java                 (demonstração)
```

---

## Exemplo de Saída Esperada

```
========== SISTEMA DE GESTÃO DE FUNCIONÁRIOS ==========

1. FUNCIONÁRIOS CRIADOS
Funcionário #1001 - João Silva (Horista)
Funcionário #1002 - Maria Santos (Mensalista)
Funcionário #1003 - Pedro Costa (Comissionado)
Funcionário #1004 - Ana Ferreira (Horista)

2. DETALHES E SALÁRIOS
--- Funcionário Horista ---
Número: 1001
Nome: João Silva
Horas Trabalhadas: 180 (160 normais + 20 extra)
Valor/Hora: 8.00€
Salário: 1520.00€ (1280.00€ + 240.00€ extra)

--- Funcionário Mensalista ---
Número: 1002
Nome: Maria Santos
Salário Base: 1200.00€
Subsídio Alimentação: 150.00€
Salário: 1350.00€

--- Funcionário Comissionado ---
Número: 1003
Nome: Pedro Costa
Salário Base: 800.00€
Vendas do Mês: 15000.00€
Comissão: 5%
Salário: 1550.00€ (800.00€ + 750.00€ comissão)

3. FOLHA SALARIAL
Total a pagar: 5420.00€

4. MAIOR SALÁRIO
Pedro Costa - 1550.00€
```

---

## Diagrama de Classes (UML Simplificado)

```
        ┌─────────────────────────┐
        │    <<abstract>>         │
        │     Funcionario         │
        ├─────────────────────────┤
        │ - numeroFuncionario: int│
        │ - nome: String          │
        │ - email: String         │
        │ - dataAdmissao: String  │
        ├─────────────────────────┤
        │ + calcularSalario(): double {abstract}  │
        │ + mostrarDetalhes(): void {abstract}    │
        │ + toString(): String                    │
        └─────────────┬───────────┘
                      │
        ┌─────────────┼─────────────┐
        │             │             │
        ▼             ▼             ▼
┌───────────────┐ ┌───────────────┐ ┌───────────────────┐
│Func.Horista   │ │Func.Mensalista│ │Func.Comissionado  │
├───────────────┤ ├───────────────┤ ├───────────────────┤
│-horasTrab: int│ │-salarioBase   │ │-salarioBase       │
│-valorHora     │ │-subsidioAlim  │ │-vendasMes         │
├───────────────┤ ├───────────────┤ │-percentagemCom    │
│+calcularSal() │ │+calcularSal() │ ├───────────────────┤
│+mostrarDet()  │ │+mostrarDet()  │ │+calcularSalario() │
└───────────────┘ └───────────────┘ │+mostrarDetalhes() │
                                    └───────────────────┘
```

---

## Dicas de Implementação

### Para a Classe Abstrata

```java
public abstract class Funcionario {
    private static int contador = 1000;

    // Método abstrato - SEM corpo
    public abstract double calcularSalario();

    // Método concreto - COM corpo
    public String toString() {
        return "Funcionário #" + numeroFuncionario + " - " + nome;
    }
}
```

### Para as Subclasses

```java
public class FuncionarioHorista extends Funcionario {
    @Override
    public double calcularSalario() {
        // Implementação específica
    }
}
```

### Para o Polimorfismo

```java
ArrayList<Funcionario> funcionarios = new ArrayList<>();
funcionarios.add(new FuncionarioHorista(...));
funcionarios.add(new FuncionarioMensalista(...));

for (Funcionario f : funcionarios) {
    // Chama o método correto de cada subclasse!
    System.out.println(f.calcularSalario());
}
```

---

## Conceito-Chave: Porque usar Classes Abstratas?

| Situação                                 | Usar Classe Abstrata                   |
| ---------------------------------------- | -------------------------------------- |
| Código comum entre subclasses            | ✅ Evita duplicação                    |
| Método com implementações diferentes     | ✅ Método abstrato                     |
| Não faz sentido instanciar a classe pai  | ✅ `new Funcionario()` não faz sentido |
| Garantir que subclasses implementam algo | ✅ Obriga a fazer `@Override`          |

---

## Autor

Mário Amorim - CESAE Digital
